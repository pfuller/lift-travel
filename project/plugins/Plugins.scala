import sbt._
class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
//  val stax = "eu.getintheloop" % "sbt-stax-plugin" % "1.0"
  val sbtYui = "hoffrocket" % "sbt-yui" % "0.2"
  // repos
//  val staxReleases = "stax-release-repo" at "http://mvn.stax.net/content/repositories/public"
  val sonaTypeReo = "Sonatype Repo" at "http://oss.sonatype.org/content/groups/github/"
}