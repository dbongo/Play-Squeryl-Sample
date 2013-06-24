import sbt._
import Keys._
import play.Project._
import com.tuplejump.sbt.yeoman.Yeoman

object ApplicationBuild extends Build {

  val appName         = "squeryl2"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "org.squeryl" %% "squeryl" % "0.9.5-6"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
    Yeoman.yeomanSettings : _*
  )

}
