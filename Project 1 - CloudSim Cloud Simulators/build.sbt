name := "src"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0",
  "com.typesafe" % "config"               % "1.3.2",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
)

