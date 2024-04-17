package hi.netkaffi.entities

import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Date
import java.sql.Timestamp

class Booking(
    var user: User,
    var product: Product,
    var starttime: Long,
) {
    fun toStringFormat(): String{
        val timestamp = Timestamp(starttime)
        val date = Date(timestamp.time)
        return "${product.name} $date"
    }
}
