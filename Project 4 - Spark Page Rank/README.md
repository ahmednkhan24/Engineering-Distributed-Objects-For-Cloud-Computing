# Spark Page Rank

## PAGE RANK MODEL
Page Rank is a model associated with websites and ranking their importance through search engines.
The algorithm works by assigning a numerical weight to each element of a set with the purpose
of measuring the importance through an iterative approach.

The algorithm begins by giving each node an initial ranking based on some data, or just assumption.
The program then begins a specified number of iterations as follows:
* Find how many associated neighbors the node has by seeing who is pointing to it, and who it is pointing to
* Readjusting the rank based on the previous rank and the new values calculated in the current iteration

After the specified number of iterations have been completed, we will have a relatively accurate ranking
of the importance of each node.
The number of iterations varies, but usually stays between 10 and 20 iterations.
The more iterations completed leads to a more accurate set of rankings, but it is important to keep in mind
that the rankings eventually converge. This means that as the number of iterations increases, the
difference between iteration x and iteration x + 1 decreases as they converge toward the actual value.

## PREPROCESSING
This project uses Apache Spark to accomplish the task of processing of the publicly available
DBLP dataset that contains entries for various publications at many different venues of various computer scientists.

This project wants to perform Page Range on all professors. We obtained a XML file containing all the professors
via the DBLP Computer Science Bibliography.

The DBLP dataset is around 2.5 GB, which is not huge, but large enough for this project.
The tags that we care about for this project are the inproceedings, proceedings, and articles.

Since data preprocessing is not a part of the PageRank model, we were allowed to
choose whatever format is convenient for us. What I noticed is that the dblp.xml file
contained various data that we were not concerned with. Because of this, the fact
that my computer is limited in RAM, and the fact that the PageRank model is simpler
using single line data, I created a script to preprocess the file by extracting
names of the authors and venues for each publication and storing each publication on a line in a
comma separated value file while also replacing any foreign characters.
The script can be found under the script folder. In order to run the script, the dblp.xml
file must be in the script directory. The dblp.xml file was not uploaded to the repository
because of the large size. The scripted csv can be found under /src/main/resources/dblp.csv.

Two entries in the dblp.xml file looks like this:

```xml
<inproceedings mdate="2017-05-24" key="conf/icst/GrechanikHB13">
    <author>Mark Grechanik</author>
    <author>B. M. Mainul Hossain</author>
    <author>Ugo Buy</author>
    <title>Testing Database-Centric Applications for Causes of Database Deadlocks.</title>
    <pages>174-183</pages>
    <year>2013</year>
    <booktitle>ICST</booktitle>
    <ee>https://doi.org/10.1109/ICST.2013.19</ee>
    <ee>http://doi.ieeecomputersociety.org/10.1109/ICST.2013.19</ee>
    <crossref>conf/icst/2013</crossref>
    <url>db/conf/icst/icst2013.html#GrechanikHB13</url>
</inproceedings>
<article mdate="2017-06-08" key="journals/spe/UIC">
    <author>Mark Grechanik</author>
    <author>Ugo Buy</author>
    <author>Shen Wang</author>
    <article>UIC CS</article>
    <title>CS 441 at UIC is amazing!</title>
    <pages>405-431</pages>
    <year>2019</year>
    <volume>46</volume>
</article>
```

The script will create a csv file where the two entries look like this:

* Mark Grechanik,B. M. Mainul Hossain,Ugo Buy,ICST
* Mark Grechanik,Shen Wang,Ahmed Khan,UIC CS

## PAGE RANK IMPLEMENTATION
The program first obtains the configuration values through the utilized library
and begins to read the dblp.csv file and creating the necessary Spark library variables
in order to run the algorithm in parallel. Previous versions of spark needed a sparkConfiguration object,
a sparkContext object, and a sqlContext object in order to achieve the same goals/functionality
that SparkSession provides. RDD is spark's core abstraction which stands for resilient distributed dataset,
which means it is an immutable distributed collection of objects that can be processed in
parallel on various nodes throughout the cluster.

The program RDD looks as follows, using the same two entries of data mentioned previously:

| KEY                    | VALUES
| ---------------------- | ------------------------------------------------------------- |
| Mark Grechanik         | B. M. Mainul Hossain,Ugo Buy,ICST,Shen Wang,Ahmed Khan,UIC CS |
| B. M. Mainul Hossain   | Mark Grechanik,Ugo Buy,ICST                                   |
| Ugo Buy                | Mark Grechanik,B. M. Mainul Hossain,ICST                      |
| ICST                   | Mark Grechanik,B. M. Mainul Hossain,Ugo Buy                   |
| Shen Wang              | Mark Grechanik,Ahmed Khan,UIC CS                              |
| Ahmed Khan             | Mark Grechanik,Ahmed Khan,UIC CS                              |
| UIC CS                 | Mark Grechanik,Shen Wang,Ahmed Khan                           |

Clearly, Mark Grechanik is the most important here, since he has the most amount of
nodes pointing at him that are also important. This means that Mark Grechanik will have
the highest page rank value.

An illustration of this is available under the root directory at /CS 441 HW 5 Example.png
The algorithm continues by giving each node an initial rank of 1.0 from the configuration file,
and then begins the iterations which is also based from the configuration file.

Each iteration, the new page rank is obtained by using the formula
newRank = 0.15 + 0.85 * currentRank
This formula is derived from an 85% likelihood of choosing a random link,
and a 15% likelihood of jumping to a page chosen at random.
The program is run on Spark computational model, therefore it is run on multiple nodes
within a cluster in parallel.

## EXECUTION
Test cases are located under /src/test/scala/ahmed_khan_hw5tests.scala
Test cases can be ran using the command
sbt clean compile test

The dblp.csv file should be located under /src/main/resources/dblp.csv

The application must be run using configuration files that specify various parameters
through the command line argument.
The file name should contain the .conf extension, the file should be located under
/src/main/resources/configuration.conf
and the file should contain all variables within a config {} object.
Necessary values given include:

* 'master' and 'applicationName' depicting the Spark Session
* 'path' for the relevant path of the resources folder within the project
* 'filename' for the name of the .csv file to run the page rank algorithm on
* 'iterationSmall' representing the smallest number of iterations to be run,
* 'iterationLarge' representing the largest number of iterations to be run,
* 'bytesToMb' representing the amount of bytes in 1 megabyte for file sizing
* 'mbThreshold' representing the size of files that will use the different iteration values,
* 'iterationRank' representing the intial page rank given to all nodes at the start of the algorithm

All dependencies are resolved using SBT via the build.sbt file.
Without a build tool like SBT, every developer that would want to run this program
would have to manually download every dependency and manually add it to the project.
This would be a tedious process, especially considering
projects that depend on dozens of various dependencies. Instead, with SBT,
in a unique file named build.sbt, all of the necessary information to run the project is
included.
This includes meta data, plugins, dependencies, and builds. In turn, when the application
is run, the dependencies will be injected into the project through a central Maven API.

A jar file can be created via running the command
"sbt clean compile assembly"
using the assembly sbt plugin located in the assembly.sbt file under
/project/assembly.sbt

The jar file will be located under
target/scala-2.11/ahmed_khan_hw5_2.11-0.1.jar

In order to run page rank, Apache Spark must be installed. If Spark is not installed on
your machine, you can access Cloudera or HortonWorks via VMware.

In order to copy the input csv file and the jar to the VM, you must run this command:
* `scp -P 2222 <location of file on local machine> <location of folder to put file on VM>`

On the VM you must create a folder that has read/write access in the HDFS like so:
* `hdfs dfs -mkdir /<folder name>`
or
* `hadoop fs -mkdir /<folder name>`

You must also use the same process to create an input folder under the new folder like so:
* `hdfs dfs -mkdir /<folder name>/<input folder name>`
or
* `hadoop fs -mkdir /<folder name>/<input folder name>`

You must then copy the input file to the input folder from the VM to HDFS like so:
* `hdfs dfs -put dblp.csv /<folder name>/<input folder name>`
or
* `hadoop fs -put dblp.csv /<folder name>/<input folder name>`

In order to execute the program, you must change to the directory where all the spark files
are located on Hortonworks Sandbox by using the command:
* `cd /usr/hdp/current/spark2-client`

Finally, you can execute the program on Spark by typing the command
* `./bin/spark-submit --class ahmed_khan_hw5 --master yarn --num-executors 1 --driver-memory 512m --executor-memory 512m --executor-cores 1 <path to jar> <path to input file>`
