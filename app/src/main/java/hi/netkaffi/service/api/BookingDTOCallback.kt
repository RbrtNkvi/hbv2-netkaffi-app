package hi.netkaffi.service.api

import hi.netkaffi.entities.BookingDTO

fun interface BookingDTOCallback {
    fun onSuccess(response: Array<BookingDTO>)
}