package hi.netkaffi.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.R
import hi.netkaffi.databinding.ActivityBookingBinding
import hi.netkaffi.service.dummyData
import hi.netkaffi.ui.dashboard.DashboardFragment
import java.util.Calendar

class BookingActivity: AppCompatActivity() {

    private var _binding: ActivityBookingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookingBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        val productName = intent.extras?.getString("productName")
        val textProductName: TextView = findViewById(R.id.productName)
        val textProductPrice: TextView = findViewById(R.id.productPrice)
        textProductName.text = productName
        textProductPrice.text = 1500.toString()
        val picker: NumberPicker = findViewById(R.id.picker1)
        picker.maxValue = 23
        picker.minValue = 0
        binding.bookingButton.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            val booking = (textProductName.text as String?).plus(" ").plus(picker.value.toString())
            dummyData.data.addData(booking)
            startActivity(intent)
        }

        val pickDateButton: Button = findViewById(R.id.pickDate)

        pickDateButton.setOnClickListener {
            // Get the current date
            val currentDate = Calendar.getInstance()
            val year = currentDate.get(Calendar.YEAR)
            val month = currentDate.get(Calendar.MONTH)
            val day = currentDate.get(Calendar.DAY_OF_MONTH)

            // Create a DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Handle the selected date
                    val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    // Update the UI with the selected date
                    pickDateButton.text = selectedDate
                },
                year,
                month,
                day
            )

            // Show the DatePickerDialog
            datePickerDialog.show()
        }
    }
}