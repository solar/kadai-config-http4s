name := "kadai-config-http4s"
organization := "org.sazabi"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"
crossScalaVersions := Seq(scalaVersion.value, "2.10.5")

incOptions := incOptions.value.withNameHashing(true)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-language:implicitConversions")

libraryDependencies ++= Seq(
  "io.atlassian" %% "kadai-config" % "3.3.4",
  "org.http4s" %% "http4s-core" % "0.8.4",
  "com.github.scalaprops" %% "scalaprops" % "0.1.12" % "test")

// kind projector compiler plugin
resolvers += "bintray/non" at "http://dl.bintray.com/non/maven"
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.6.3")

testFrameworks += new TestFramework("scalaprops.ScalapropsFramework")
parallelExecution in Test := false

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/solar/kadai-config-http4s</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt"</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:solar/kadai-config-http4s.git</url>
    <connection>scm:git:git@github.com:solar/kadai-config-http4s.git</connection>
  </scm>
  <developers>
    <developer>
      <id>solar</id>
      <name>Shinpei Okamura</name>
      <url>https://github.com/solar</url>
    </developer>
  </developers>)