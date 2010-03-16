package example.travel {
package model {
  
  import net.liftweb.common.{Full,Box,Empty,Failure}
  import net.liftweb.mapper._
  
  object Bid 
    extends Bid 
    with LongKeyedMetaMapper[Bid]

  class Bid extends LongKeyedMapper[Bid] with IdPK {
    def getSingleton = Bid
    // fields
    object name extends MappedString(this, 150)
    object close_date extends MappedDateTime(this)
    object travel_date extends MappedDateTime(this)
    
    // relationship
    object customer extends LongMappedMapper(this, Customer)
    object deal extends LongMappedMapper(this, Deal)
  }
  
  
}}
