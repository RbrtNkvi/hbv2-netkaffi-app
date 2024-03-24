package hi.netkaffi.service.api

import hi.netkaffi.entities.User

fun interface UserCallback {
    fun onSuccess(response: Array<User>)
}