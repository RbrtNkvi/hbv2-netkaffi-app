package hi.netkaffi.service
 import hi.netkaffi.entities.Booking
 import hi.netkaffi.entities.Product
 import hi.netkaffi.entities.User

class dummyData {

    object bookings{
        private val list = ArrayList<Booking>()
        private val favoriteComputers = HashSet<String>() // Set to store favorite computers

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

        fun addBooking(booking: Booking) {
            list.add(booking)
        }

        fun getBookingsByUser(user: User): ArrayList<Booking>{
            val userlist = ArrayList<Booking>()

            for (booking in list) {
                if (booking.user == user) {
                    userlist.add(booking)
                }
            }
            return userlist
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
        // Function to mark a computer as favorite
        fun addToFavorites(computerName: String) {
            favoriteComputers.add(computerName)
        }
        fun removeFromFavorites(computerName: String) {
            favoriteComputers.remove(computerName)
        }

        // Function to check if a computer is marked as favorite
        fun isFavorite(computerName: String): Boolean {
            return favoriteComputers.contains(computerName)
        }

        // Function to get names of favorite computers
        fun getFavoriteComputersNames(): ArrayList<String> {
            return ArrayList(favoriteComputers)
        }
    }

    object products {
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

        fun isUser(user2: User): Boolean{
            for (user in list_users){
                if (user.username == user2.username)
                    if (user.password == user2.password)
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
