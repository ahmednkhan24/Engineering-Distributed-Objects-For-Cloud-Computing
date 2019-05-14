import scala.io.Source
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.{Paths, Files}
import scala.collection.immutable.List
import scala.collection.mutable.ListBuffer
import org.apache.spark
import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import java.io.File
import com.typesafe.config.{Config, ConfigFactory}


class Log {

  val logger = LoggerFactory.getLogger(classOf[Log])

  logger.info("Initializing Log class")

  def logInfoMessage(message: String): Unit = {

    logger.info(message)

  }

  def logDebugMessage(message: String): Unit = {

    logger.debug(message)

  }

}

object ahmed_khan_hw5 {

  val logger: Log = new Log


  /*
   * function is given a path for a file and checks if the file exists
   * returns true if the file exists,
   * returns false if the file does not exist
   */
  def checkForFile(path : String): Boolean = {

    logger.logDebugMessage("Checking if a file exists")

    Files.exists(Paths.get(path))

  }


  /*
   * function to get the current working directory and return it
   * ie /Users/ahmedkhan/IdeaProjects/ahmed_khan_hw5
   */
  def getDirectory(): String = {

    logger.logDebugMessage("Obtaining the current working directory")

    return System.getProperty("user.dir")

    //"/Users/ahmedkhan/IdeaProjects/ahmed_khan_hw5" + "/src/main/resources"
  }


  /*
   * function to take a comma seperated string like so: "ahmed khan,CS 441,Mark Grechanik"
   * and return a list containing unique key value pairs for each value in the string
   */
  def createKeyValuePairs(string: String, myList : List[(String, List[String])]): List[(String, List[String])] = {

    logger.logDebugMessage("Creating key value pairs")

    var tempList: List[(String, List[String])] = myList

    val list: Array[String] = string.split(",")

    val map: Array[(String, List[String])] = list.map { keyName: String =>
      keyName -> Predef.refArrayOps(list).filter((name: String) => name != keyName).toList
    }

    map.toList.foreach(x => tempList = tempList :+ x)

    tempList
  }


  /*
   * function to take a scala list version of a RDD and create a list of tuples
   * for each key value pair within the file
   */
  def keyValuePairList(dataFrameList : List[String]): List[(String, List[String])] = {
    var myList : List[(String, List[String])] = List()

    dataFrameList.foreach{ x =>
      myList = createKeyValuePairs(x, myList)
    }

    myList
  }


  /*
   * function to take 2 lists of any type and return the product of merging the two lists
   */
  def mergeLists(bigList : List[Any], smallList : List[Any]): List[Any] = {

    logger.logDebugMessage("Merging two lists")

    val mergedList: List[Any] = bigList ::: smallList

    mergedList
  }


  /*
   * read from the file path given and return a list containing the data from the file
   */
  def readFromFile(file_path: String): List[String] = {

    logger.logDebugMessage("Reading from file")

    val list: List[String] = Source.fromFile(file_path).getLines.toList

    list

  }


  /*
   * function creates, initializes, and returns a spark session object
   * a spark session object is a single point of entry to interact with
   * underlying spark functionality in spark 2.4.1
   *
   * Previous versions of spark needed a sparkConfiguration object,
   * a sparkContext object, and a sqlContext object in order to achieve
   * the same goals/functionality that SparkSession provides
   */
  def createSparkSession(master : String, applicationName : String): SparkSession = {

    logger.logDebugMessage("Creating SparkSession Object")

    val spark: SparkSession = SparkSession
      .builder
      .master(master)
      .appName(applicationName)
      .getOrCreate

    spark

  }


  /*
   * Function creates an rdd encapsulation of the file provided in the file path
   *
   * rdd is spark's core abstraction which stands for resilient distributed dataset,
   * which means it is an immutable distributed collection of objects that can
   * be processed in parralel on various nodes throughout the cluster
   *
   */
  def createRddDataFrame(spark : SparkSession, filePath : String): RDD[String] = {

    logger.logDebugMessage("Creating Resilient Distributed Dataset")

    val rdd: RDD[String] = spark.read.textFile(filePath).rdd

    rdd

  }


  /*
   * Driver main method
   */
  def main(args: Array[String]): Unit = {

    logger.logDebugMessage("Starting Main Method")

    // obtain configuration file name from the command line argument and load it
    val config = ConfigFactory.load("configuration.conf")

    // obtain the working directory for the resources
    val dir: String = getDirectory() + config.getString("config.path")

    // obtain the filename needed to run the page rank algorithm
    //val filename = "/dblp.csv"
    val filename: String = config.getString("config.filename")

    // check if the file provided exists
    if (!checkForFile(dir + filename)) {

      logger.logDebugMessage("Could not find file. Exiting...")

      System.exit(1)

    }

    val bytesToMb = config.getInt("config.bytesToMb")
    val mbThreshold = config.getInt("config.mbThreshold")
    val iterationSmall = config.getInt("config.iterationSmall")
    val iterationLarge = config.getInt("config.iterationLarge")

    // set the number of iterations to execute the page rank algorithm depending on the size of the file
    val numIterations = if (new File(dir + filename).length / bytesToMb > mbThreshold) iterationSmall else iterationLarge

    // create the spark session and load the data frame into an rdd
    val spark: SparkSession = createSparkSession(config.getString("config.master"), config.getString("config.applicationName"))
    val dataFrame: RDD[String] = createRddDataFrame(spark, dir + filename)
    val dataFrameList: List[String] = dataFrame.collect().toList
    val scalaDfList : List[(String, List[String])] = keyValuePairList(dataFrameList)

    // set the initial rankings of all nodes
    val nodeRank = dataFrame.map(_ => config.getDouble("config.initialRank"))

    // begin the page rank algorithm
    for (i <- 1 to numIterations) {

      nodeRank.foreach{ rank =>

        // join urls with their initial ranks
        val contributions = {
          scalaDfList.map(node => rank)
        }

        /*
         * continue the execution for each contribution with its rank
         * formula derived from an 85% likelihood of choosing a random link and a 15% likelihood of jumping to a page chosen at random
         */
        val update = contributions.foreach(x => 0.15 + 0.85 * x)

        update
      }

    }

    // organize the results and free resources
    val results = nodeRank.collect()
    results.foreach(node => logger.logInfoMessage("Execution complete"))

    spark.stop()

  }

}
