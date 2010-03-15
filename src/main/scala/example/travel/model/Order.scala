package example.travel {
package model {
  
  import net.liftweb.common.{Full,Box,Empty,Failure}
  import net.liftweb.util.{Log}
  import net.liftweb.mapper._
  
  object Order extends Order with LongKeyedMetaMapper[Order]{
    override def dbTableName = "orders"
  }

  class Order extends LongKeyedMapper[Order] with IdPK {
    def getSingleton = Order
    // fields
    object name extends MappedString(this, 150)
  }
  
  
}}
