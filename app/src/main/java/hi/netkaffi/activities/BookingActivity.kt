package hi.netkaffi.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.R
import hi.netkaffi.databinding.ActivityBookingBinding
import hi.netkaffi.entities.Booking
import hi.netkaffi.entities.User
import hi.netkaffi.service.ProductService
import hi.netkaffi.service.dummyData
import java.util.Calendar
import android.content.Context

class BookingActivity : AppCompatActivity() {

    private var _binding: ActivityBookingBinding? = null

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
                val selectedTime = picker.value.toLong()
                val selectedDate = binding.pickDate.text.toString()

                val user = User("example_user_id", "John Doe")

                val booking = if (product != null) {
                    Booking(user, product, selectedTime, selectedDate) // Store the booking date in the 'date' field
                } else {
                    null
                }

                if (booking != null) {
                    dummyData.bookings.addBooking(booking)
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            // Set up the pickDate button click listener
            binding.pickDate.setOnClickListener {
                showDatePickerDialog(binding.pickDate)
            }
        })
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

        datePickerDialog.show()
    }
}
