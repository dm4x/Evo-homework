name := "Evo-homework"

version := "0.1"

scalaVersion := "2.13.4"

val scalaTestVersion = "3.1.0.0-RC2"
val catsScalacheckVersion = "0.2.0"
val catsVersion = "2.2.0"
val catsEffectVersion = "2.2.0"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.2" % "test",
  "io.chrisdavenport" %% "cats-scalacheck" % catsScalacheckVersion % Test,
  "org.scalatestplus" %% "scalatestplus-scalacheck" % scalaTestVersion % Test,
  "org.scalatestplus" %% "selenium-2-45" % scalaTestVersion % Test,
  "org.typelevel" %% "cats-core" % catsVersion,
  "org.typelevel" %% "cats-effect" % catsEffectVersion

)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.1" cross CrossVersion.full)