package example.travel {
package model {
  
  import net.liftweb.common.{Full,Box,Empty,Failure}
  import net.liftweb.util.{Log}
  import net.liftweb.mapper._
  
  object Journey extends Journey with LongKeyedMetaMapper[Journey]

  class Journey extends LongKeyedMapper[Journey] with IdPK {
    def getSingleton = Journey
    // fields
    object name extends MappedString(this, 150)
    object close_date extends MappedDateTime(this)
    object travel_date extends MappedDateTime(this)
    
    // relationships
    object supplier extends LongMappedMapper(this, Supplier)
    object destination extends LongMappedMapper(this, Destination)
    
  }
  
  
}}
