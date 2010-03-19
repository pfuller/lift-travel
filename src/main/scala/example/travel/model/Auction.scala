package example.travel {
package model {
  
  import net.liftweb.common.{Full,Box,Empty,Failure}
  import net.liftweb.mapper._
  
  object Auction 
    extends Auction 
    with LongKeyedMetaMapper[Auction]
    with CRUDify[Long,Auction]

  class Auction extends LongKeyedMapper[Auction] with IdPK {
    def getSingleton = Auction
    // fields
    object name extends MappedString(this, 150)
    object close_date extends MappedDateTime(this)
    object travel_date extends MappedDateTime(this)
    object perma_link extends MappedString(this, 150)
    
    // relationships
    object supplier extends LongMappedMapper(this, Supplier)
    
    // helper: get all the bids 
    def bids = Bid.findAll(By(Bid.deal, this.id), OrderBy(Bid.id, Descending))
  }
  
  
}}
