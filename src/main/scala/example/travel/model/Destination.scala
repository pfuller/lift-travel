package example.travel {
package model {
  
  import net.liftweb.common.{Full,Box,Empty,Failure}
  import net.liftweb.util.{Log}
  import net.liftweb.mapper._
  
  object Destination extends Destination with LongKeyedMetaMapper[Destination]

  class Destination extends LongKeyedMapper[Destination] with IdPK with OneToMany[Long, Destination] {
    def getSingleton = Destination
    // fields
    object name extends MappedString(this, 150)
    object description extends MappedText(this)
    object average_tempreture extends MappedDouble(this)
    object average_rainfall extends MappedDouble(this)
    // geek-tastic!
    object iata_location extends MappedString(this, 6)
    object timezone extends MappedTimeZone(this)
    
    // relationships
    object journeys extends MappedOneToMany(Journey, Journey.destination, 
      OrderBy(Journey.close_date, Descending)) 
        with Owned[Journey] 
        with Cascade[Journey]
  }
  
  
}}
