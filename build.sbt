name := "Evo-homework"

version := "0.1"

scalaVersion := "2.13.4"

val scalaTestVersion = "3.1.0.0-RC2"
val catsScalacheckVersion = "0.2.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"
libraryDependencies += "io.chrisdavenport" %% "cats-scalacheck" % catsScalacheckVersion % Test,
libraryDependencies += "org.scalatestplus" %% "scalatestplus-scalacheck" % scalaTestVersion % Test
libraryDependencies += "org.scalatestplus" %% "selenium-2-45" % scalaTestVersion % Test