package hi.netkaffi.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.databinding.ActivityNewproductBinding
import hi.netkaffi.entities.Product
import hi.netkaffi.service.ProductService

class NewProductActivity: AppCompatActivity() {

    override fun onCreate( savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityNewproductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val button = binding.productAdd
        val productName = binding.editText
        val productPrice = binding.editPrice

        val productService = ProductService()
        productService.initialize(this)


        button.setOnClickListener { _ ->

            val product = Product(productName.text.toString(), productPrice.text.toString().toInt(), false)
            productService.addProduct(product, callback = {
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
            })
        }
    }
}