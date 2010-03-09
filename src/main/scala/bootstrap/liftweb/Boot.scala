package bootstrap.liftweb

// framework imports
import net.liftweb.common._
import net.liftweb.util._
import net.liftweb.util.Helpers._
import net.liftweb.http._
import net.liftweb.sitemap._
import net.liftweb.sitemap.Loc._
import net.liftweb.mapper.{DB,Schemifier,DefaultConnectionIdentifier,StandardDBVendor}

// app imports
import example.travel.model.{Destination}

class Boot {
  def boot {
    LiftRules.addToPackages("example.travel")
    
    // set the JNDI name that we'll be using
    DefaultConnectionIdentifier.jndiName = "jdbc/liftinaction"
    
    // handle JNDI not being avalible
    if (!DB.jndiJdbcConnAvailable_?) {
      Log.error("No JNDI configured - using the default in-memory database.") 
      DB.defineConnectionManager(DefaultConnectionIdentifier, Application.database)
    }
    
    // automatically create the tables
    Schemifier.schemify(true, Log.infoF _, Destination)
    
    // make sure cyote unloads database connections before shutting down
    LiftRules.unloadHooks.append(() => Application.database.closeAllConnections_!()) 
    
    // set the application sitemap
    LiftRules.setSiteMap(SiteMap(Application.sitemap:_*))
  }
}

object Application {
  val sitemap = 
    Menu(Loc("Home", List("index"), "Home")) ::
    Menu(Loc("About", List("about"), "About")) ::
    Menu(Loc("Search", List("search"), "Search")) ::
    Menu(Loc("Contact", List("contact"), "Contact")) :: Nil
  
  val database = DBVendor
  
  object DBVendor extends StandardDBVendor(
    Props.get("db.class").openOr("org.apache.derby.jdbc.EmbeddedDriver"),
    Props.get("db.url").openOr("jdbc:derby:lift_example;create=true"),
    Props.get("db.user"),
    Props.get("db.pass"))
  
}



