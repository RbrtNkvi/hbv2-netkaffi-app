package hi.netkaffi.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.R
import hi.netkaffi.databinding.ActivityEditBinding
import hi.netkaffi.service.dummyData
import hi.netkaffi.ui.dashboard.DashboardFragment
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

        // Assuming you have a reference to the pickDate button
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

        // Set OnClickListener for the edit button
        binding.editButton.setOnClickListener {
            val updatedBooking = "$computerName ${binding.picker1.value}"
            val index = intent.getIntExtra("selectedItemIndex", -1)
            if (index != -1) {
                // Update the booking at the specified index
                dummyData.data.updateData(index, updatedBooking)
            }
            finish()
        }

        // Set OnClickListener for the remove button
        binding.removeButton.setOnClickListener {
            if (selectedItem != null) {
                // Remove the booking
                dummyData.data.removeData(selectedItem)
            }

            // Finish the activity after removing the item
            finish()
        }
    }

}
