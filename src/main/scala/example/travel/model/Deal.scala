package example.travel {
package model {
  
  import net.liftweb.common.{Full,Box,Empty,Failure}
  import net.liftweb.util.{Log}
  import net.liftweb.mapper._
  
  object Deal 
    extends Deal 
    with LongKeyedMetaMapper[Deal]
    with CRUDify[Long,Deal]

  class Deal extends LongKeyedMapper[Deal] with IdPK {
    def getSingleton = Deal
    // fields
    object name extends MappedString(this, 150)
    object close_date extends MappedDateTime(this)
    object travel_date extends MappedDateTime(this)
    
    // relationships
    object supplier extends LongMappedMapper(this, Supplier)
    object destination extends LongMappedMapper(this, Destination)
    
  }
  
  
}}
