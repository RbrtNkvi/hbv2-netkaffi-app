package hi.netkaffi.service.api

import hi.netkaffi.entities.Product

fun interface ProductCallback {
    fun onSuccess(response: Array<Product>)
}