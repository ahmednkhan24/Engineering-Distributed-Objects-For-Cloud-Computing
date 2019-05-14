name := "ahmed_khan_hw2"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.1.1"

libraryDependencies += "org.apache.hadoop" % "hadoop-core" % "1.2.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}