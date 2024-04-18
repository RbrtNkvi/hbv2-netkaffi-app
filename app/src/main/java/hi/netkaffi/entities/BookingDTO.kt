package hi.netkaffi.entities

import java.sql.Date
import java.sql.Timestamp

class BookingDTO (
    private var userName: String,
    var productName: String,
    private var startTime: Long
) {
    fun toStringFormat(): String{
        val timestamp = Timestamp(startTime)
        val date = Date(timestamp.time)
        val hour = (startTime % 86400)/3600
        return "$userName $productName $date $hour"
    }
}

