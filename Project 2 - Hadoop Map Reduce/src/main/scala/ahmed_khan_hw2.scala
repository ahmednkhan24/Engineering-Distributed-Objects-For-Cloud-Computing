import java.io.IOException
import java.util._
import scala.collection.JavaConversions._

import scala.io.Source
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io._
import org.apache.hadoop.mapred._
import java.util
//import org.apache.log4j.Logger

import scala.collection.immutable.List
import scala.collection.mutable.ListBuffer


object ahmed_khan_hw2 {

  private val dir = getDirectory()
  private val profs_list = getProfsList(dir, "/src/main/resources/profs.txt")
  private val word = new Text()
  private val word2 = new Text()
  //private val Logger LOG = Logger.getLogger(this.class)


  /*
   * function to read from the file path given and return a list containing
   * the data from the file
   */
  def getProfsList(dir_path: String, profs_file_path: String): List[String] = {

    //LOG.info("Creating professor list")

    val list = Source.fromFile(dir_path + profs_file_path).getLines.toList
    list

  }


  /*
   * function to get the current working directory and return it
   * ie /Users/ahmedkhan/IdeaProjects/SCALADEMO
   */
  def getDirectory(): String = {

    return System.getProperty("user.dir")

  }


  /*
   * function to convert Scala list to Java list
   */
  def toJavaList[T](l: List[T]): util.List[T] = {

    val a = new util.ArrayList[T]
    l.map(a.add(_))
    a
  }


  /*
   * function to convert Java list to Scala list
   */
  def toScalaList[T](l: util.List[T]): List[T] = {

    var a = ListBuffer[T]()
    for (r <- 0 until l.size) a += l.get(r)
    a.toList

  }


  /*
   * Given a line of authors for a publication, creates a map containing key value pairs of each author and co-author
   *
   * Given: "Ahmed Khan,Joe Hummel,Pat Troy"
   * Creates:
   *    Ahmed Khan: "Joe Hummel,Pat Troy"
   *    Joe Hummel: "Ahmed Khan,Pat Troy"
   *    Pat Troy: "Ahmed Khan,Joe Hummel"
   */
  def createKeyValuePairs(split: Array[String], publication: String): Array[(String, Array[String])] = {

    //LOG.info("Creating pairs")

    // split the authors of this publication by commas,
    // then map to contain a combination of author and co-authors for each author in this publication
    val map = split.map{ keyName =>
      keyName -> split.filter(name => name != keyName)
    }

    map
  }


  /*
   * Reducer class that implements the job of obtaining key value pairs
   * from the mapper and then reducing them based on keys
   */
  class MyReducer extends MapReduceBase with Reducer[Text, Text, Text, Text] {

    //LOG.info("Reducing pairs")

    private val word = new Text()

    @throws[IOException]
    def reduce(key: Text, values: Iterator[Text], output: OutputCollector[Text, Text], reporter: Reporter) {

      // turn the iterable array to a scala list
      val str = values.toList

      // make all values into one string
      word.set(str.mkString(","))

      // complete collection to output
      output.collect(key, word)

    }

  }

  /*
   * Mapper class that implements the job of obtaining all values from the file
   * and mapping them by creating key value pairs
   */
  class MyMapper extends MapReduceBase with Mapper[LongWritable, Text, Text, Text] {

    @throws[IOException]
    def map(key: LongWritable, value: Text, output: OutputCollector[Text, Text], reporter: Reporter) {

      //LOG.info("Mapping pairs")

      // grab the value and turn it to a string
      val line: String = value.toString

      // split the line by using a comma as the delimiter
      val split = line.split(",")

      // filter the names in this list to only contain UIC professor names
      val filtered = split.filter(name => profs_list.contains(name))

      // if filtered is empty, that means this publication entry has no professors from uic, no need to store it
      if (!filtered.isEmpty) {

        // receive a map of each UIC professor mapped to his/her co-authors
        val map = createKeyValuePairs(filtered, line)

        // add each key,value pair to the collection
        map.foreach { tuple =>
          word.set(tuple._1)
          word2.set(tuple._2.mkString(","))

          output.collect(word, word2)
        }

      }

    }

  }

  /*
   * Driver main method
   */
  @throws[Exception]
  def main(args: Array[String]) {

    //LOG.info("Initializing Driver")

    // create the job
    val conf: JobConf = new JobConf(this.getClass)
    conf.setJobName("Ahmed Khan CS 441 HW 2")

    // set the mapper, combiner, and reducer by specifying the implemented classes
    conf.setMapperClass(classOf[MyMapper])
    conf.setCombinerClass(classOf[MyReducer])
    conf.setReducerClass(classOf[MyReducer])

    // specify the format of the input and output based on Hadoop preconfigured classes for serialization
    conf.setInputFormat(classOf[TextInputFormat])
    conf.setOutputFormat(classOf[TextOutputFormat[Text, Text]])

    // specify the type of the outputs
    conf.setOutputKeyClass(classOf[Text])
    conf.setOutputValueClass(classOf[Text])

    // use the command line arguments to set the path for input and output folders on HDFS
    FileInputFormat.setInputPaths(conf, new Path(args(0)))
    FileOutputFormat.setOutputPath(conf, new Path(args(1)))

    //LOG.info("Running Job")

    // run the job
    JobClient.runJob(conf)
  }

}
