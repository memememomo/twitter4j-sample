name := "scala-twitter4j"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.twitter4j" % "twitter4j-core" % "4.0.4",
  "com.typesafe" % "config" % "1.3.0"
)

enablePlugins(JavaAppPackaging)

