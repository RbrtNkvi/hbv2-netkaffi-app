package hi.netkaffi.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.databinding.ActivityNewproductBinding
import hi.netkaffi.entities.Product
import hi.netkaffi.service.DummyData

class NewProductActivity: AppCompatActivity() {

    override fun onCreate( savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityNewproductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val button = binding.productAdd
        val text = binding.editText

        button.setOnClickListener { _ ->
            DummyData.Products.addProduct(Product(text.text.toString(), "default", 1500, false))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}