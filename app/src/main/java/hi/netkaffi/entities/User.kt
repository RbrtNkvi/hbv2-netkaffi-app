package hi.netkaffi.entities

class User(
    var username: String,
    var password: String,
    var bookings: Array<Booking>? = arrayOf(),
    var isAdmin: Boolean? = false,
)
