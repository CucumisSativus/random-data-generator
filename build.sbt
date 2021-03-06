import com.typesafe.sbt.SbtGit.GitKeys._

name := "random-data-generator"
version := "2.1-SNAPSHOT"

scalaVersion := "2.12.1"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= {
  if (scalaVersion.value.startsWith("2.10."))
    Seq(compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full))
  else Seq()
}

libraryDependencies ++= {
  val Spec2 = "3.8.6"

  Seq(
    "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.4",
    "org.specs2" %% "specs2-core" % Spec2 % "test",
    "org.specs2" %% "specs2-mock" % Spec2 % "test"
  )
}

lazy val standardSettings = Seq(
  crossScalaVersions := Seq("2.12.1", "2.11.8", "2.10.6"),
  organization := "com.danielasfregola",
  licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("https://github.com/DanielaSfregola/random-data-generator")),
  scmInfo := Some(
    ScmInfo(url("https://github.com/DanielaSfregola/random-data-generator"),
            "scm:git:git@github.com:DanielaSfregola/random-data-generator.git")),
  apiURL := None, // TBD scaladoc needed?
  pomExtra := (
    <developers>
    <developer>
      <id>DanielaSfregola</id>
      <name>Daniela Sfregola</name>
      <url>http://danielasfregola.com/</url>
    </developer>
  </developers>
  ),
  publishMavenStyle := true,
  gitRemoteRepo := "git@github.com:DanielaSfregola/random-data-generator.git",
  scalacOptions ++= Seq("-encoding",
                        "UTF-8",
                        "-Xlint",
                        "-deprecation",
                        "-Xfatal-warnings",
                        "-feature",
                        "-language:postfixOps",
                        "-unchecked"),
  scalacOptions in (Compile, doc) ++= Seq("-sourcepath", baseDirectory.value.getAbsolutePath),
  autoAPIMappings := true,
  apiURL := None,
  scalacOptions in (Compile, doc) ++= {
    val branch = if (version.value.trim.endsWith("SNAPSHOT")) "master" else version.value
    Seq(
      "-doc-source-url",
      "https://github.com/DanielaSfregola/random-data-generator/tree/" + branch + "€{FILE_PATH}.scala"
    )
  }
)

lazy val root = Project(id = "random-data-generator", base = file("."), settings = standardSettings ++ site.settings)
