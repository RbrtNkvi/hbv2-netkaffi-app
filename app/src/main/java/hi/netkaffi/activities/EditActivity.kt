package hi.netkaffi.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.databinding.ActivityEditBinding
import hi.netkaffi.entities.Booking
import hi.netkaffi.service.DummyData
import java.util.Calendar


// OBSOLETE: This class became obsolete.
class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedItem = intent.getStringExtra("selectedItem")
        val selectedDate = intent.getStringExtra("bookingDate")

        val parts = selectedItem?.split(" ")
        val computerName = parts?.dropLast(1)?.joinToString(" ")
        val bookingTime = parts?.last()

        binding.productName.text = computerName
        binding.productPrice.text = "1500"

        binding.picker1.maxValue = 23
        binding.picker1.minValue = 0
        binding.picker1.value = bookingTime?.toIntOrNull() ?: 0

        binding.pickDate.text = selectedDate

        binding.pickDate.setOnClickListener {
            showDatePickerDialog(binding.pickDate)
        }

        binding.editButton.setOnClickListener {
            val index = intent.getIntExtra("selectedItemIndex", -1)
            if (index != -1) {
                val booking = DummyData.Bookings.getBookings()[index]
                val updatedBookingObject = Booking(
                    booking.user,
                    booking.product,
                    binding.picker1.value.toLong(),
                )
                DummyData.Bookings.updateBooking(index, updatedBookingObject)
            }
            finish()
        }

        binding.removeButton.setOnClickListener {
            if (selectedItem != null) {
                val index = intent.getIntExtra("selectedItemIndex", -1)
                val booking = DummyData.Bookings.getBookings()[index]
                DummyData.Bookings.removeBooking(booking)
            }
            finish()
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
