import sbt._
import hoffrocket.YuiCompressorPlugin

class LiftTravelProject(info: ProjectInfo) extends DefaultWebProject(info) {
//        with YuiCompressorPlugin with stax.StaxPlugin {
  // lift
  val webkit = "net.liftweb" % "lift-webkit" % "2.0-SNAPSHOT" % "compile"
  val mapper = "net.liftweb" % "lift-mapper" % "2.0-SNAPSHOT" % "compile"

  // stax
//  override def staxApplicationId: String = "lifttravel"
//  override def staxUsername: String = "timperrett"
  
  override def managedStyle = ManagedStyle.Maven
  
  // database
  val mysql = "mysql" % "mysql-connector-java" % "5.1.12" % "compile"
  
  // java web stuff
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.14" % "test"
  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided"
  
  // testing
  val scalatest = "org.scala-tools.testing" % "scalatest" % "0.9.5" % "test->default"
  
  // required because Ivy doesn't pull repositories from poms
  val smackRepo = "m2-repository-smack" at "http://maven.reucon.com/public"
  val scalaSnapshots = "scala-tools.snapshots" at "http://scala-tools.org/repo-snapshots/"
  val sonatype = "oss.sonatype.org" at "http://oss.sonatype.org/content/groups/github/"
}