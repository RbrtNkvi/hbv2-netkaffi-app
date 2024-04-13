package hi.netkaffi.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import hi.netkaffi.entities.Product
import java.security.Provider
import hi.netkaffi.entities.User
import hi.netkaffi.service.api.UserCallback
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class UserService {

    var context: Context? = null
    var apiRequestQueue: RequestQueue? = null

    object ActiveUser {
        private var user: User? = null

        fun setUser(user: User){
            this.user = user
        }

        fun getUser(): User?{
            return this.user
        }

        fun isAdmin(): Boolean?{
            return user?.isAdmin
        }
    }

    fun initialize(context: Context){
        this.context = context
        this.apiRequestQueue = Volley.newRequestQueue(context)
    }

    fun login(user: User, callback: UserCallback){
        val apiRequestQueue = this.apiRequestQueue ?: return

        var headers: MutableMap<String, String> = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        val request = GsonRequest(
            url = "https://hbv2-netkaffi.onrender.com/login",
            clazz = User::class.java,
            method = Request.Method.POST,
            headers = headers,
            jsonPayload = JSONObject(Gson().toJson(user)),
            listener = {
                if (it != null) {
                    callback.onSuccess(arrayOf(it))
                } else {
                    Toast.makeText(context,"Wrong Username or Password", Toast.LENGTH_LONG).show()
                }
            },
            errorListener = {

            })

        apiRequestQueue.add(request)
    }

    fun signup(user: User, callback: UserCallback){
        val apiRequestQueue = this.apiRequestQueue ?: return

        var headers: MutableMap<String, String> = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        val request = GsonRequest(
            url = "https://hbv2-netkaffi.onrender.com/signup",
            clazz = User::class.java,
            method = Request.Method.POST,
            headers = headers,
            jsonPayload = JSONObject(Gson().toJson(user)),
            listener = {
                Log.i("User Received", "$it")
                if (it != null) {
                    callback.onSuccess(arrayOf(it))
                } else {
                    val errormsg = "Username already exists"
                    Toast.makeText(context, errormsg, Toast.LENGTH_LONG).show()
                }
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