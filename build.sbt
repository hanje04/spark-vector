lazy val commonSettings = Seq(
    organization := "com.actian",
    version := "0.1",
    scalaVersion := "2.10.4",
    libraryDependencies ++= commonDeps,
    fork in Test := true,
    test in assembly := {},
    scalacOptions ++= Seq( "-unchecked", "-deprecation" , "-feature"),
    EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource,
    // no scala version suffix on published artifact
    crossPaths := false
)

lazy val commonDeps = Seq(
    "org.apache.spark" %% "spark-core" % "1.5.1" % "provided",
    "org.apache.spark" %% "spark-sql" % "1.5.1"  % "provided",
    "org.apache.spark" %% "spark-hive" % "1.5.1" % "provided",
    "org.scalatest" %% "scalatest" % "2.2.3" % "test",
    "org.scalacheck" %% "scalacheck" % "1.12.2" % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.2.1" % "test"
)

lazy val connectorDeps = Seq(
    "com.jsuereth" %% "scala-arm" % "1.3"
)

lazy val connectorSettings = Seq(
    compileOrder := CompileOrder.JavaThenScala,
    javacOptions ++= Seq("-source", "1.7", "-target", "1.7")
)

lazy val loaderDeps = Seq(
    "com.github.scopt" %% "scopt" % "3.3.0",
    "com.typesafe" % "config" % "1.2.1",
    "com.databricks" % "spark-csv_2.10" % "1.2.0"
)

lazy val root = (project in file("."))
    .settings(commonSettings:_*)
    .settings(connectorSettings:_*)
    .settings(
        name := "spark_vector",
        libraryDependencies ++= connectorDeps
    )

lazy val loader = project
    .settings(commonSettings:_*)
    .settings(
        name := "spark_vector_loader",
        libraryDependencies ++= loaderDeps
    ).dependsOn(root)
