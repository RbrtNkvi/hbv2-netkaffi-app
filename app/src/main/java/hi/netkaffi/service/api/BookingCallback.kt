package hi.netkaffi.service.api

import hi.netkaffi.entities.Booking

fun interface BookingCallback {
    fun onSuccess(response: Array<Booking>)
}