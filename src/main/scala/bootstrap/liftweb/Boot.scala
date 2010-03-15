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
import example.travel.model.{Deal,Order,Supplier,Customer,Bid}
// import example.travel.lib.{Helpers}

class Boot extends Loggable {
  def boot {
    LiftRules.addToPackages("example.travel")
    
    // set the JNDI name that we'll be using
    DefaultConnectionIdentifier.jndiName = "jdbc/liftinaction"
    
    // handle JNDI not being avalible
    if (!DB.jndiJdbcConnAvailable_?){
      logger.error("No JNDI configured - using the default in-memory database.") 
      DB.defineConnectionManager(DefaultConnectionIdentifier, Application.database)
      // make sure cyote unloads database connections before shutting down
      LiftRules.unloadHooks.append(() => Application.database.closeAllConnections_!()) 
    }
    
    // automatically create the tables
    Schemifier.schemify(true, Log.infoF _, 
      Destination, Journey, Order, Supplier, User)
    
    // LiftRules.loggedInTest = Full(() => User.loggedIn_?)
    
    // set the application sitemap
    // LiftRules.setSiteMap(SiteMap(Application.sitemap:_*))
    
    // setup the load pattern
    S.addAround(DB.buildLoanWrapper)
    
    // make requests utf-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
  }
}

object Application {
  val sitemap = 
    Menu(Loc("Home", List("index"), "Home", LocGroup("public"))) ::
    Menu(Loc("About", List("about"), "About", LocGroup("public"))) ::
    Menu(Loc("Search", List("search"), "Search", LocGroup("public"))) ::
    Menu(Loc("Contact", List("contact"), "Contact", LocGroup("public"))) ::
    Menu(Loc("Admin", List("admin","index"), "Admin", LocGroup("admin"), User.loginFirst)) ::
    Menu(Loc("Destinations", List("admin", "destinations"), "Destinations", 
      LocGroup("admin"), 
      EarlyResponse(() => Full(RedirectResponse("destination/list"))), 
      User.loginFirst), 
      Destination.menus : _*
    ) ::
    Menu(Loc("Suppliers", List("admin", "suppliers"), "Suppliers", 
      LocGroup("admin"), 
      EarlyResponse(() => Full(RedirectResponse("supplier/list"))), 
      User.loginFirst), 
      Supplier.menus : _*
    ) ::
    Menu(Loc("Users", List("admin", "users"), "Users", 
      LocGroup("admin"), 
      EarlyResponse(() => Full(RedirectResponse("/admin/")))), 
      User.menus : _*
    ) :: Nil
    
  
  val database = DBVendor
  
  object DBVendor extends StandardDBVendor(
    Props.get("db.class").openOr("org.apache.derby.jdbc.EmbeddedDriver"),
    Props.get("db.url").openOr("jdbc:derby:lift_example;create=true"),
    Props.get("db.user"),
    Props.get("db.pass"))
  
}



