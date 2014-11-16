import com.typesafe.sbt.SbtScalariform.ScalariformKeys

import scalariform.formatter.preferences._

import sbtassembly.Plugin.AssemblyKeys._

name := """akka-http-scala"""

version := "1.1"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core-experimental"  % "0.11",
  "com.typesafe.akka" %% "akka-stream-experimental"     % "0.11",
  "com.typesafe.akka" %% "akka-http-experimental"       % "0.11",
  "io.spray"          %% "spray-json"                   % "1.3.1",
  "org.reactivemongo" %% "reactivemongo"                % "0.10.5.0.akka23"
)

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(PreserveDanglingCloseParenthesis, true)


assemblySettings