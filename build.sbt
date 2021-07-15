organization := "net.astail"

name := "chatwork-discord-bot"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.14"

resolvers ++= Seq(
  "jcenter.bintray.com" at "https://jcenter.bintray.com"
)

libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "2.28.0",
  "com.typesafe" % "config" % "1.4.1",
  "net.dv8tion" % "JDA" % "4.2.0_247",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.slf4j" % "slf4j-api" % "1.7.31"
)

enablePlugins(JavaAppPackaging)

sourceGenerators in Compile += Def.task {
  import scala.sys.process.Process

  val file = (sourceManaged in Compile).value / "net" / "astail" / "Git.scala"
  val longHash = Process("""git log -1 --format="%H"""").!!
  val shortHash = Process("""git log -1 --format="%h"""").!!
  val log = Process("git show -s").!!
  IO.write(file, s"""package net.astail
                    |
                    |object Git {
                    |  val longHash = $longHash
                    |  val shortHash = $shortHash
                    |  val log = \"\"\"$log\"\"\"
                    |}""".stripMargin)
  Seq(file)
}
