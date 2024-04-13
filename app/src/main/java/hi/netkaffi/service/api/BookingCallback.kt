package hi.netkaffi.service.api

import hi.netkaffi.entities.Booking
import hi.netkaffi.entities.BookingDTO

fun interface BookingCallback {
    fun onSuccess(response: Array<Booking>)
}