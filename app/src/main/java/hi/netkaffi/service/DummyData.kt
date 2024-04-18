package hi.netkaffi.service
 import hi.netkaffi.entities.Booking

//OBSOLETE: This class became obsolete.
class DummyData {

    object Bookings{
        private val list = ArrayList<Booking>()

        fun getBookings(): ArrayList<Booking> {
            return list
        }

        fun getBookingsNames(): ArrayList<String> {
            val names = ArrayList<String>()
            for (booking in list) {
                names.add(booking.product.name + " " + booking.starttime)
            }
            return names
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
}
