package hi.netkaffi.activities

import android.content.Intent
import android.os.Bundle
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.R
import hi.netkaffi.databinding.ActivityEditBinding
import hi.netkaffi.service.dummyData.products.getProducts
import hi.netkaffi.service.dummyData.products.removeProduct
import hi.netkaffi.service.dummyData
import hi.netkaffi.service.dummyData.data.removeData

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Retrieve the selected item's data from the intent extras
        val selectedItem = intent.getStringExtra("selectedItem")

        // Set the product name and price
        val productName = intent.extras?.getString("productName")
        binding.productName.text = productName
        binding.productPrice.text = "1500"

        // Set up the number picker
        binding.picker1.maxValue = 23
        binding.picker1.minValue = 0

        // Set OnClickListener for the edit button
        binding.editButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val booking = "${binding.productName.text} ${binding.picker1.value}"
            dummyData.data.addData(booking)
            startActivity(intent)
        }

        // Set OnClickListener for the remove button
        binding.removeButton.setOnClickListener {
            // Find the index of the selected item
            val index = getProducts().indexOf(selectedItem)
            if (index != -1) {
                // Remove the selected item from the list
                removeData(index)
            }
            // Finish the activity after removing the item
            finish()
        }
    }
}
