# Hadoop Map/Reduce
## MAP REDUCE MODEL
Map Reduce is a model associated with parallel processing of big data over distributed systems.
This project uses Apache Hadoop's Map Reduce.

The Mappers job is to obtain a set of key value pairs and produce an intermediate
set of key value pairs. The Mapper outputs are partitioned per Reducer.

The Reducers job is to obtain the intermediate set of key value pairs produced by
the the Mappers and reduce them by grouping values that share a similar key.
The Reducer has 3 focuses: Shuffing the key value pairs obtained from the mapper, 
sorting the inputs by keys, and then reducing the output per key. 

## PREPROCESSING
This project uses Apache Hadoop's Map Reduce to accomplish the task of processing 
of the publicly available DBLP dataset that contains entries for various publications
at many different venues of various computer scientists.

This project wants to map all UIC affiliated professors. We obtained a text file
containing all the UIC professors via the UIC CS Faculty webpage. The text file 
looks like this:

...
Mark Grechanik
Chris Kanich
Robert V. Kenyon
...

The text file is located under src/main/resources/profs.txt.
The program reads from the file and creates a list containing the names of each professor. 

The DBLP dataset is around 2.5 GB, which is not huge, but large enough for this project.
The tags that we care about for this project are the inproceedings and articles.

The InputFormat is the component in Map Reduce that is responsible for creating 
the input splits and dividing them. By default, Hadoop uses the TextInputFormat,
which treats each line of the input file as a seperate record. This causes a problem
with our dataset, since an xml document cascades to multiple lines. Luckily, Apache
mahout created their own XMLInputFormat which was very nice to learn about and understand.

Since data preprocessing is not a part of the map/reduce model, we were allowed to 
choose whatever format is convenient for us. What I noticed is that the dblp.xml file
contained various data that we were not concerned with. Because of this, the fact 
that my computer is limited in RAM, and the fact that the Map Reduce model is simpler 
using the TextInputFormat, I created a script to preprocess the file by extracting
names of the authors for each publication and storing each publication on a line in a 
comma seperated value file while also replacing any foreign characters. 
The script can be found under the script folder. In order to run the script, the dblp.xml
file must be in the script directory. The dblp.xml file was not uploaded to the repository
because of the large size. The scripted csv can be found under /src/main/resources/dblp.csv.

## EXAMPLE OF MY MAP REDUCE IMPLEMENTATION

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
    <title>CS 441 at UIC is amazing!</title>
    <pages>405-431</pages>
    <year>2019</year>
    <volume>46</volume>
</article>
```

The script will create a csv file where the two entries look like this:

* Mark Grechanik,B. M. Mainul Hossain,Ugo Buy
* Mark Grechanik,Shen Wang,Ahmed Khan

The Mapper will then create key value pairs per entry per author that look like this:
* Mark Grechanik: B. M. Mainul Hossain,Ugo Buy
* B. M. Mainul Hossain: Mark Grechanik,Ugo Buy
* Ugo Buy: Mark Grechanik,B. M. Mainul Hossain
* Mark Grechanik: Ugo Buy,Shen Wang
* Ugo Buy: Mark Grechanik,Shen Wang
* Shen Wang: Mark Grechanik,Ugo Buy

The Reducer will then obtain these intermediate pairs and combine similar keys and turn them into this:
* Mark Grechanik: B. M. Mainul Hossain,Ugo Buy,Shen Wang
* B. M. Mainul Hossain: Mark Grechanik,Ugo Buy
* Ugo Buy: Mark Grechanik,B. M. Mainul Hossain,Shen Wang
* Shen Wang: Mark Grechanik,Ugo Buy

This output ensures each key is unique, and each key contains the values of all
co-authors within the entire input file. The size of the value split by commas
shows the weight of each key, ie, how many publications the author has been a part of. 

## MAPPER/REDUCER IMPLEMENTATION
Each mapper will first receive data that looks like this:

* Mark Grechanik,B. M. Mainul Hossain,Ugo Buy

The mapper will first split this string using a comma as the delimiter to look like this:

* Mark Grechanik
* B. M. Mainul Hossain
* Ugo Buy

It will then filter out the list to only contain names that match any name from the 
list of professor names. If the filtered list is empty, the mapper will skip this input.
If not, the mapper will then call the function to create key value pairs that look like this:
* Mark Grechanik: B. M. Mainul Hossain,Ugo Buy
* B. M. Mainul Hossain: Mark Grechanik,Ugo Buy
* Ugo Buy: Mark Grechanik,B. M. Mainul Hossain

and then add them all to the output collector for the reducer.

The reducer will then obtain an iterator of each key's values. So for example, the reducer
would receive this for the key Mark Grechanik:
Mark Grechanik: {
    B. M. Mainul Hossain,Ugo Buy
    Ugo Buy,Shen Wang
    ...
}

The reducer will turn the iterable array of values to a list, and then make one string
value that looks like this:
* B. M. Mainul Hossain,Ugo Buy,Ugo Buy,Shen Wang

Notice that Ugo Buy is duplicated in the list, because Mark Grechanik, for this example,
has co-authored more than one publication with Ugo Buy.

The implementation of the Mapper, Reducer, and Driver can all be found 
under src/main/scala/ahmed_khan_hw2.scala.
The test cases can be found under src/test/scala/TestClass.scala.

## EXECUTION
A jar file can be created via running the command "sbt clean compile assembly" using the
assembly sbt plugin located in the assembly.sbt file under /project/assembly.sbt.
The jar file will be located under target/scala-2.12/ahmed_khan_hw2_2.12-0.1.jar.

In order to run the Map Reduce, Hadoop must be installed. If Hadoop is not installed on
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

In order to execute the Map Reduce job, you must use the command

* `hadoop jar <name of jar file> /<folder name>/<input folder name> /<folder name>/<output folder name>`

After running, in order to run again you must remove the output directory like so:
* `hadoop fs -rm -r /<folder name>/<output folder name>`

The data can now be copied from HDFS to your local machine so that you can
load it into whatever visualizer you prefer.
