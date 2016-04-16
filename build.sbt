lazy val baseSettings = Seq(
    version := "0.0.1",
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq("-language:experimental.macros", "-deprecation", "-feature", "-language:higherKinds"),
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )

lazy val day1 = (project in file("day1"))
  .settings(
    baseSettings,
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(
      "jp.t2v"             %% "stackable-controller"         % "0.6.0",
      "org.scalikejdbc"    %% "scalikejdbc"                  % "2.3.5",
      "org.scalikejdbc"    %% "scalikejdbc-config"           % "2.3.5",
      "org.scalikejdbc"    %% "scalikejdbc-play-initializer" % "2.5.0",
      "com.h2database"     %  "h2"                           % "1.4.191"
    ),
    name := "day1"
  )
  .enablePlugins(PlayScala)
