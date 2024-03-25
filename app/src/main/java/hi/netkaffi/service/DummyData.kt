package hi.netkaffi.service
 import hi.netkaffi.entities.Booking
 import hi.netkaffi.entities.Product
 import hi.netkaffi.entities.User

class DummyData {

    object Bookings{
        private val list = ArrayList<Booking>()
        fun getBookings(): ArrayList<Booking> {
            return list
        }

        fun getBookingsNames(): ArrayList<String> {
            val names = ArrayList<String>()
            for (booking in list) {
                names.add(booking.product.name + " " + booking.startTime)
            }
            return names
        }

        fun getBookingsNamesByUsername(username: String): ArrayList<String> {
            val names = ArrayList<String>()
            for (booking in list) {
                if (booking.user.username == username) {
                    names.add(booking.product.name + " " + booking.startTime)
                }
            }
            return names
        }

        fun addBooking(booking: Booking) {
            list.add(booking)
        }

        fun getBookingsByUsername(username: String): ArrayList<Booking>{
            val userList = ArrayList<Booking>()
            for (booking in list) {
                if (booking.user.username == username) {
                    userList.add(booking)
                }
            }
            return userList
        }

        fun getBooking(user: User, product: Product, date: String, startTime: Long): Booking? {
            for (booking in list) {
                if (booking.user == user && booking.product == product && booking.date == date && booking.startTime == startTime) {
                    return booking
                }
            }
            return null
        }

        fun updateBooking(index: Int, updatedBooking: Booking) {
            if (index in 0 until list.size) {
                list[index] = updatedBooking
            }
        }

        fun removeBooking(booking: Booking) {
            list.remove(booking)
        }
    }

    object Products {
        private val list = ArrayList<Product>()

        fun getProducts(): ArrayList<Product> {
            return list
        }

        fun getProductsNames(): ArrayList<String> {
            val names = ArrayList<String>()
            for (product in list) {
                names.add(product.name)
            }
            return names
        }

        fun getProduct(name: String): Product? {
            for (product in list) {
                if (product.name == name) {
                    return product
                }
            }
            return null
        }

        fun addProduct(product: Product) {
            list.add(product)
        }

        fun removeProduct(index: Int){
            list.removeAt(index)
        }
    }
    object Users {
        private val list_users = ArrayList<User>()

        fun getUsers(): ArrayList<User> {
            return list_users
        }
        fun isUsername(user2: User): Boolean{
            for (user in list_users){
                return user.username != user2.username
            }
            return true
        }

        fun isUser(username: String, password: String): Boolean{
            for (user in list_users){
                if (user.username == username)
                    if (user.password == password)
                        return true
            }
            return false
        }

        fun getUser(username: String): User? {
            for (user in list_users) {
                if (user.username == username) {
                    return user
                }
            }
            return null
        }
        fun addUsers(user: User) {
            list_users.add(user)
            val temp = list_users.size.toString()
            print(temp)

        }

        fun removeUsers(index: Int) {
            list_users.removeAt(index)
        }
    }
}
