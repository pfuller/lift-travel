package example.travel {
package model {
  
  import net.liftweb.common.{Full,Box,Empty,Failure}
  import net.liftweb.util.{Log}
  import net.liftweb.mapper._
  
  object Supplier extends Supplier with LongKeyedMetaMapper[Supplier]

  class Supplier extends LongKeyedMapper[Supplier] with IdPK with OneToMany[Long, Supplier] {
    def getSingleton = Supplier
    // fields
    object name extends MappedString(this, 150)
    object telephone extends MappedString(this, 30)
    object email extends MappedEmail(this, 200)
    object address extends MappedText(this)
    object opening_hours extends MappedText(this)
    
    // relationships
    object journeys extends MappedOneToMany(Journey, Journey.supplier, 
      OrderBy(Journey.close_date, Descending)) 
        with Owned[Journey] 
        with Cascade[Journey] 
  }
  
  
}}
