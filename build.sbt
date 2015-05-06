name := "ScalaNginxAccessLogParser"

organization := "com.diegomagalhaes"

version := "1.3-SNAPSHOT"

scalaVersion := "2.10.4"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

scalacOptions += "-deprecation"

javacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.2.4" % "test"

