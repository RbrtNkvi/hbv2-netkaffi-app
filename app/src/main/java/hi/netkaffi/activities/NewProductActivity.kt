package hi.netkaffi.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.databinding.ActivityNewproductBinding
import hi.netkaffi.entities.Product
import hi.netkaffi.service.ProductService

class NewProductActivity: AppCompatActivity() {
    private var _binding: ActivityNewproductBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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


        button.setOnClickListener { view ->

            val product = Product(productName.text.toString(), "default", productPrice.text.toString().toInt(), false)
            productService.addProduct(product, callback = {
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
            })
        }
    }
}