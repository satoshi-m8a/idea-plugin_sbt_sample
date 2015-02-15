name := """twitter_idea"""

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.twitter4j" % "twitter4j-core" % "[4,)"
)

scalacOptions += "-target:jvm-1.6"
