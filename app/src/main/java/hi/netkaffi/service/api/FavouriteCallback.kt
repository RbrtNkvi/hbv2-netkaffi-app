package hi.netkaffi.service.api

import hi.netkaffi.entities.Booking
import hi.netkaffi.entities.Favourite

fun interface FavouriteCallback {
    fun onSuccess(response: Array<Favourite>)
}