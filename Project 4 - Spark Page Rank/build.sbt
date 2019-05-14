name := "ahmed_khan_hw5"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.1"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3" % Test

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0",
  "com.typesafe" % "config" % "1.3.2"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}