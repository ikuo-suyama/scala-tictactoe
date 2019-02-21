name := "scala-tictactoe"

version := "0.1"

scalaVersion := "2.12.8"

val scalazVersion = "7.2.27"
val specs2Version = "4.3.4"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.specs2" %% "specs2-core" % specs2Version % "test",
  "org.specs2" %% "specs2-mock" % specs2Version % "test")