package hi.netkaffi.entities

import java.util.Date

class Booking(
    var uName: List<User>,
    var pName: List<Product>,
    var startTime: Long,
    var date: Date
) {
    fun getEndTime(): Long {
        // TODO: Implement this method
        return 0L // Placeholder return value
    }
}
