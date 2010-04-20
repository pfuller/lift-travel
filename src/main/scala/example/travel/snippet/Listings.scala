package example.travel {
package snippet {
  
  import example.travel.model.Auction
  import scala.xml.{NodeSeq,Text}
  import net.liftweb.util.Helpers._
  import net.liftweb.http.{S,DispatchSnippet}
  import net.liftweb.mapper.{MaxRows,By,OrderBy,Descending,StartAt}
  import net.liftweb.mapper.view.{PaginatedSnippet,MapperPaginator}
  
  class Listings extends PaginatedSnippet[Auction] {
    override def dispatch = {
      case "all" => all _
      case "top" => top _ 
      case "paginate" => paginator.paginate _
    }
    
    lazy val paginator = new MapperPaginator(Auction, Auction.id){
      override def num = 5
    }
    
    def all(xhtml: NodeSeq): NodeSeq = many(paginator.page, xhtml)
    
    def top(xhtml: NodeSeq): NodeSeq = 
      many(Auction.findAll(MaxRows(3), OrderBy(Auction.id, Descending)), xhtml)
    
    // helpers
    private def many(auctions: List[Auction], xhtml: NodeSeq): NodeSeq = 
      auctions.flatMap(a => single(a,xhtml))
    
    private def single(auction: Auction, xhtml: NodeSeq): NodeSeq = bind("a", xhtml,
      "name" -> auction.name,
      "description" -> auction.description,
      "link" -%> <a href={"/auction/" + auction.permanent_link}>details >></a>
    )
    
  }
  
}}