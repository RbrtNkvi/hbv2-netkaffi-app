package hi.netkaffi.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.databinding.ActivityEditBinding
import hi.netkaffi.entities.Booking
import hi.netkaffi.service.dummyData
import java.util.Calendar

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Retrieve the selected item's data from the intent extras
        val selectedItem = intent.getStringExtra("selectedItem")
        val selectedDate = intent.getStringExtra("bookingDate")

        // Extract computer name and booking time from the selected item
        val parts = selectedItem?.split(" ")
        val computerName = parts?.dropLast(1)?.joinToString(" ")
        val bookingTime = parts?.last()

        // Set the computer name and booking time
        binding.productName.text = computerName
        binding.productPrice.text = "1500" // Assuming you have a fixed price

        // Set up the number picker
        binding.picker1.maxValue = 23
        binding.picker1.minValue = 0
        binding.picker1.value = bookingTime?.toIntOrNull() ?: 0

        binding.pickDate.text = selectedDate

        // Set OnClickListener for the pickDate button
        binding.pickDate.setOnClickListener {
            showDatePickerDialog(binding.pickDate)
        }

        // Set OnClickListener for the edit button
        binding.editButton.setOnClickListener {
            val updatedBooking = "$computerName ${binding.picker1.value}"
            val index = intent.getIntExtra("selectedItemIndex", -1)
            if (index != -1) {
                val booking = dummyData.bookings.getBookings()[index]
                val updatedBookingObject = Booking(
                    booking.user,
                    booking.product,
                    binding.picker1.value.toLong(),
                )
                dummyData.bookings.updateBooking(index, updatedBookingObject)
            }
            finish()
        }

        // Set OnClickListener for the remove button
        binding.removeButton.setOnClickListener {
            if (selectedItem != null) {
                val index = intent.getIntExtra("selectedItemIndex", -1)
                val booking = dummyData.bookings.getBookings()[index]
                dummyData.bookings.removeBooking(booking)
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
