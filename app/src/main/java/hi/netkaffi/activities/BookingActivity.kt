package hi.netkaffi.activities

import android.annotation.SuppressLint
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
    }
}