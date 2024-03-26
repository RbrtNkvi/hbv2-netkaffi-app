package hi.netkaffi.entities

class Product(
    var name: String,
    var type: String,
    var price: Int,
    var deleted: Boolean,
    var bookings: Array<Booking>,
    var users: Array<User>,
)
