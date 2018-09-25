enablePlugins(JavaAppPackaging)

name := "url-generator"

version := "0.1"

scalaVersion := "2.12.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV       = "2.4.19"
  val scalaTestV  = "3.0.1"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "ch.qos.logback"    % "logback-classic" % "1.2.3",
    "org.scalaj" 	      %% "scalaj-http" % "2.4.1",
    "org.scalatest"     %% "scalatest" % scalaTestV % "test"
  )
}

Revolver.settings