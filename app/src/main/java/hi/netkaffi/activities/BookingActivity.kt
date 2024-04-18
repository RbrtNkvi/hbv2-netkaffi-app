package hi.netkaffi.activities

import android.Manifest
import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.gson.Gson
import hi.netkaffi.R
import hi.netkaffi.databinding.ActivityBookingBinding
import hi.netkaffi.entities.Booking
import hi.netkaffi.entities.User
import hi.netkaffi.service.BookingService
import hi.netkaffi.service.ProductService
import hi.netkaffi.service.UserService
import hi.netkaffi.service.dummyData
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class BookingActivity : AppCompatActivity() {

    private var _binding: ActivityBookingBinding? = null

    private var Channel_ID = "channelID"
    private var Channel_NAME = "channelName"
    private var Notification_ID = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val productName = intent.extras?.getString("productName")

        val context = this

        val textProductName: TextView = findViewById(R.id.productName)
        val textProductPrice: TextView = findViewById(R.id.productPrice)

        val productService = ProductService()
        productService.initialize(context)

        productService.fetchProduct("book/$productName", callback = {
            if (it.isNotEmpty()) {
                textProductName.text = it[0].name
                textProductPrice.text = it[0].price.toString()
            }

            val product = it[0]

            val picker: NumberPicker = findViewById(R.id.picker1)
            picker.maxValue = 23
            picker.minValue = 0

            // Set up the booking button click listener
            binding.bookingButton.setOnClickListener {
                val selectedTime = picker.value.toLong()*3600
                val selectedDate = binding.pickDate.text.toString()
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(selectedDate)?.time
                val unix = date?.plus(selectedTime)
                Log.i("Date Created", "$date")
                Log.i("Unix timestamp", "$unix")

                val user = UserService.ActiveUser.getUser()

                val booking = if (user != null && unix != null) {
                    Booking(
                        user,
                        product,
                        unix,
                    ) // Store the booking date in the 'date' field
                } else {
                    null
                }
                if (booking != null) {
                    Log.i("Booking Object", booking.toStringFormat())
                    Log.i("Booking Json", "${JSONObject(Gson().toJson(booking))}")
                }
                if (booking != null) {
                    val bookingService = BookingService()
                    bookingService.initialize(context)
                    createNotificationChannel()
                    val notification = NotificationCompat.Builder(this, Channel_ID)
                        .setSmallIcon(R.drawable.logotealnobanner)
                        .setContentTitle("SUCCESS")
                        .setContentText("You've made a booking")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .build()
                    val notificationManager = NotificationManagerCompat.from(this)
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    )
                        else notificationManager.notify(Notification_ID, notification)

                    bookingService.addBooking(booking,
                        callback = {
                            val intent = Intent(this, if(UserService.ActiveUser.isAdmin() == true) AdminActivity::class.java else UserActivity::class.java)
                            startActivity(intent)
                        })
                }
            }
            binding.backFromBookingButton.setOnClickListener(){
                val intent = Intent(this, if(UserService.ActiveUser.isAdmin() == true) AdminActivity::class.java else UserActivity::class.java)
                startActivity(intent)
            }

            // Set up the pickDate button click listener
            binding.pickDate.setOnClickListener {
                showDatePickerDialog(binding.pickDate)
            }
        })
    }
    //val intentBack = Intent(this,  if(UserService.ActiveUser.isAdmin() == false) UserActivity::class.java else AdminActivity::class.java)


    //override fun onBackPressed() {
    //    super.onBackPressed()
    //}

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(Channel_ID, Channel_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun showDatePickerDialog(dateTextView: TextView) {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                dateTextView.text = selectedDate
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000

        datePickerDialog.show()
    }
}
