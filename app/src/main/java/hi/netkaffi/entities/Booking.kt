package hi.netkaffi.entities

import java.sql.Date
import java.sql.Timestamp

class Booking(
    var user: User,
    var product: Product,
    var startTime: Long,
) {
    fun toStringFormat(): String{
        val timestamp = Timestamp(startTime)
        val date = Date(timestamp.time)
        return "${product.name} $date"
    }
}
