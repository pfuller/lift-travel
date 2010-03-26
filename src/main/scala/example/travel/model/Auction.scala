package example.travel {
package model {
  
  import net.liftweb.common.{Full,Box,Empty,Failure}
  import net.liftweb.mapper._
  
  object Auction 
    extends Auction 
    with LongKeyedMetaMapper[Auction]
    with CRUDify[Long,Auction]{
      override def dbTableName = "auctions"
    }

  class Auction extends LongKeyedMapper[Auction] with IdPK {
    def getSingleton = Auction
    // fields
    object name extends MappedString(this, 150)
    object description extends MappedText(this)
    object ends_at extends MappedDateTime(this)
    object outbound_on extends MappedDateTime(this)
    object inbound_on extends MappedDateTime(this)
    object flying_from extends MappedString(this, 100)
    object permanent_link extends MappedString(this, 150)
    object is_closed extends MappedBoolean(this)
    
    // relationships
    object supplier extends LongMappedMapper(this, Supplier)
    
    // helper: get all the bids for this auction
    def bids = Bid.findAll(By(Bid.auction, this.id), OrderBy(Bid.id, Descending))
  }
  
  
}}
