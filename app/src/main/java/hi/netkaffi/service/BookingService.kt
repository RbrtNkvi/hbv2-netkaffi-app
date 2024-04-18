package hi.netkaffi.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import hi.netkaffi.entities.Booking
import hi.netkaffi.entities.BookingDTO
import hi.netkaffi.service.api.BookingCallback
import hi.netkaffi.service.api.BookingDTOCallback
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class BookingService {
    var context: Context? = null
    private var apiRequestQueue: RequestQueue? = null

    fun initialize(context: Context){
        this.context = context
        this.apiRequestQueue = Volley.newRequestQueue(context)
    }

    fun fetchBookings(user: String, callback: BookingDTOCallback){
        val apiRequestQueue = this.apiRequestQueue ?: return

        val headers: MutableMap<String, String> = HashMap<String, String>()
        val request = GsonRequest(
            url = "https://hbv2-netkaffi.onrender.com/booked/$user",
            clazz = Array<BookingDTO>::class.java,
            method = Request.Method.GET,
            headers = headers,
            jsonPayload = null,
            listener = {
                callback.onSuccess(it)
            },
            errorListener = {
                val response = it.networkResponse
                Log.w("ErrorResponse","$it")
                Log.w("Response", "$response")
                try {
                    val errorJson = String(
                        response.data,
                        Charset.forName(HttpHeaderParser.parseCharset(response.headers))
                    )
                    val errorObj = Gson().fromJson(errorJson, Error::class.java)
                    Log.w("W","Failure")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                } catch (e: JsonSyntaxException) {
                    e.printStackTrace()
                }
            })

        apiRequestQueue.add(request)
    }

    fun addBooking(booking: Booking, callback: BookingCallback){
        val apiRequestQueue = this.apiRequestQueue ?: return
        val headers: MutableMap<String, String> = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        val request = GsonRequest(
            url = "https://hbv2-netkaffi.onrender.com/book",
            clazz = Booking::class.java,
            method = Request.Method.POST,
            headers = headers,
            jsonPayload = JSONObject(Gson().toJson(booking)),
            listener = {
                Log.i("Booking response", "$it")
                callback.onSuccess(arrayOf(it))
            },
            errorListener = {
                val response = it.networkResponse
                Log.w("ErrorResponse","$it")
                Log.w("Response", "$response")
                try {
                    val errorJson = String(
                        response.data,
                        Charset.forName(HttpHeaderParser.parseCharset(response.headers))
                    )
                    val errorObj = Gson().fromJson(errorJson, Error::class.java)
                    Log.w("W","Failure")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                } catch (e: JsonSyntaxException) {
                    e.printStackTrace()
                }
            })

        apiRequestQueue.add(request)
    }

    fun deleteBooking(booking: BookingDTO, callback: BookingDTOCallback){
        val apiRequestQueue = this.apiRequestQueue ?: return
        val headers: MutableMap<String, String> = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        val request = GsonRequest(
            url = "https://hbv2-netkaffi.onrender.com/booked/delete",
            clazz = BookingDTO::class.java,
            method = Request.Method.POST,
            headers = headers,
            jsonPayload = JSONObject(Gson().toJson(booking)),
            listener = {
                Log.i("Booking response", "$it")
                callback.onSuccess(arrayOf(it))
            },
            errorListener = {
                val response = it.networkResponse
                Log.w("ErrorResponse","$it")
                Log.w("Response", "$response")
                try {
                    val errorJson = String(
                        response.data,
                        Charset.forName(HttpHeaderParser.parseCharset(response.headers))
                    )
                    val errorObj = Gson().fromJson(errorJson, Error::class.java)
                    Log.w("W","Failure")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                } catch (e: JsonSyntaxException) {
                    e.printStackTrace()
                }
            })

        apiRequestQueue.add(request)
    }
}