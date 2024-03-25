package hi.netkaffi.entities

class Booking(
    var user: User,
    var product: Product,
    var startTime: Long,
    var date: String
) {
    fun getEndTime(): Long {
        //TODO: Implement this method
        return 0L
    }
}
