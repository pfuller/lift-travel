package example.travel {
package snippet {
  
  import example.travel.model.Auction
  import scala.xml.{NodeSeq,Text}
  import net.liftweb.util.Helpers._
  import net.liftweb.http.S
  import net.liftweb.mapper.{MaxRows,By,OrderBy,Descending,StartAt}
  
  class Listings {
    
    def all(xhtml: NodeSeq): NodeSeq = {
      val offset = S.param("offset").map(_.toLong) openOr 0L
      many(Auction.findAll(StartAt(offset), MaxRows(20)), xhtml)
    }
    
    def top(xhtml: NodeSeq): NodeSeq = 
      many(Auction.findAll(MaxRows(3), OrderBy(Auction.id, Descending)), xhtml)
    
    private def many(auctions: List[Auction], xhtml: NodeSeq): NodeSeq = 
      auctions.flatMap(a => single(a,xhtml))
    
    private def single(auction: Auction, xhtml: NodeSeq): NodeSeq = bind("a", xhtml,
      "name" -> auction.name,
      "description" -> auction.description
    )
    
  }
  
}}