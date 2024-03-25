package hi.netkaffi.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.databinding.ActivityEditBinding
import hi.netkaffi.entities.Booking
import hi.netkaffi.service.DummyData
import java.util.Calendar

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val index = intent.getIntExtra("selectedItemIndex", -1)
        val bookings = DummyData.Bookings.getBookings()
        if (index == -1) {
            throw Error("No booking found")
        }
        val booking = bookings[index]

        binding.productName.text = booking.product.name
        binding.productPrice.text = booking.product.price.toString()

        binding.picker1.maxValue = 23
        binding.picker1.minValue = 0
        binding.picker1.value = booking.startTime.toInt()

        binding.pickDate.text = booking.date

        binding.pickDate.setOnClickListener {
            showDatePickerDialog(binding.pickDate)
        }

        binding.editButton.setOnClickListener {
            val updatedBooking = Booking(
                booking.user,
                booking.product,
                binding.picker1.value.toLong(),
                binding.pickDate.text.toString()
            )
            DummyData.Bookings.updateBooking(index, updatedBooking)
            finish()
        }

        binding.removeButton.setOnClickListener {
            DummyData.Bookings.removeBooking(booking)
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
