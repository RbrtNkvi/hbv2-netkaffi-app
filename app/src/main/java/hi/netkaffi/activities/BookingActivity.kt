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
import hi.netkaffi.service.DummyData
import java.util.Calendar

class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val index = intent.getIntExtra("selectedItemIndex", -1)
        if (index == -1) {
            throw Error("No product found")
        }
        val product = DummyData.Products.getProducts()[index]

        val textProductName: TextView = findViewById(R.id.productName)
        val textProductPrice: TextView = findViewById(R.id.productPrice)

        textProductName.text = product.name
        textProductPrice.text = product.price.toString()

        val picker: NumberPicker = findViewById(R.id.picker1)
        picker.maxValue = 23
        picker.minValue = 0

        // Set up the booking button click listener
        binding.bookingButton.setOnClickListener {
            val selectedTime = picker.value.toLong()
            val selectedDate = binding.pickDate.text.toString()

            val user = User("example_user_id", "John Doe", false) //TODO: FIX LATER

            val booking = Booking(user, product, selectedTime, selectedDate)
            DummyData.Bookings.addBooking(booking)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.pickDate.setOnClickListener {
            showDatePickerDialog(binding.pickDate)
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

        datePickerDialog.show()
    }
}
