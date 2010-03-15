package example.travel {
package model {
  
  import net.liftweb.common.{Full,Box,Empty,Failure}
  import net.liftweb.util.{Log}
  import net.liftweb.util.Helpers._
  import net.liftweb.mapper._
  import net.liftweb.http.{S,SHtml}
  import net.liftweb.sitemap.Loc._
  import scala.xml.{NodeSeq,Node}
  
  object User extends User 
      with KeyedMetaMapper[Long, User]
      with MetaMegaProtoUser[User]{
    
    override def dbTableName = "users"
    
    // proto user
    override val basePath = "admin" :: "users" :: Nil
    override def homePage = "/"
    override def skipEmailValidation = true
    
    override def screenWrap: Box[Node] = 
      Full(<lift:surround with="admin" at="content"><lift:bind /></lift:surround>)
    
    override def fieldOrder = id :: firstName :: lastName :: 
      email :: password :: Nil
   
  }
  class User extends MegaProtoUser[User] {
    def getSingleton = User
  }
  
}}