package hi.netkaffi.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import hi.netkaffi.entities.BookingDTO
import hi.netkaffi.entities.Favourite
import hi.netkaffi.service.api.BookingDTOCallback
import hi.netkaffi.service.api.FavouriteCallback
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class FavouriteService {

    var context: Context? = null
    var apiRequestQueue: RequestQueue? = null

    fun initialize(context: Context){
        this.context = context
        this.apiRequestQueue = Volley.newRequestQueue(context)
    }

    fun fetchFavourites(user: String, callback: FavouriteCallback){
        val apiRequestQueue = this.apiRequestQueue ?: return

        var headers: MutableMap<String, String> = HashMap<String, String>()
        val request = GsonRequest(
            url = "https://hbv2-netkaffi.onrender.com/favourite/$user",
            clazz = Array<Favourite>::class.java,
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

    fun addFavourite(user: String, favourite: String, callback: FavouriteCallback){
        val apiRequestQueue = this.apiRequestQueue ?: return

        var headers: MutableMap<String, String> = HashMap<String, String>()
        val request = GsonRequest(
            url = "https://hbv2-netkaffi.onrender.com/favourite/$user/$favourite",
            clazz = Favourite::class.java,
            method = Request.Method.POST,
            headers = headers,
            jsonPayload = null,
            listener = {
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

    fun deleteFavourite(favourite: Favourite, callback: FavouriteCallback){
        val apiRequestQueue = this.apiRequestQueue ?: return

        var headers: MutableMap<String, String> = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        val request = GsonRequest(
            url = "https://hbv2-netkaffi.onrender.com/favourite",
            clazz = Favourite::class.java,
            method = Request.Method.POST,
            headers = headers,
            jsonPayload = JSONObject(Gson().toJson(favourite)),
            listener = {
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