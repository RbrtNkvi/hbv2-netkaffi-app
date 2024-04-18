package hi.netkaffi.ui.notifications

//noinspection SuspiciousImport
import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import hi.netkaffi.activities.AdminActivity
import hi.netkaffi.activities.NewProductActivity
import hi.netkaffi.databinding.FragmentNotificationsBinding
import hi.netkaffi.entities.Product
import hi.netkaffi.service.ProductService

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    private val productService = ProductService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val context = context as AdminActivity

        productService.initialize(context)
        productService.fetchProducts(url = "products", callback = { it1 ->
            val products = (it1.map { it.name }).toCollection(ArrayList())
            val arrayAdapter = ArrayAdapter(
                context,
                R.layout.simple_list_item_1 ,products)
            val listView = binding.productList
            listView.adapter = arrayAdapter
            listView.setOnItemClickListener { _, _, i, _ ->
                deleteProduct(products[i], it1[i])
            }
        })

        val button = binding.addProduct
        button.setOnClickListener { _ ->
            val intent = Intent(context, NewProductActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deleteProduct(name: String, product: Product){
        productService.deleteProduct(url = name, product = product, callback = {
            val intent = Intent(context, AdminActivity::class.java)
            startActivity(intent)
        })
    }
}