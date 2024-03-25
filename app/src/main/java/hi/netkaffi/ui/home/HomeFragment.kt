package hi.netkaffi.ui.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import hi.netkaffi.activities.BookingActivity
import hi.netkaffi.activities.MainActivity
import hi.netkaffi.databinding.FragmentHomeBinding
import hi.netkaffi.entities.Product
import hi.netkaffi.service.DummyData


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(DummyData.Products.getProducts().isEmpty()){
            DummyData.Products.addProduct(Product("Computer 1", "default", 1500, false))
            DummyData.Products.addProduct(Product("Computer 2", "default", 1500, false))
            DummyData.Products.addProduct(Product("Laptop 1", "default", 1500, false))
        }

        val listView: ListView = binding.products
        if (false) { //TODO: FIX FOR ADMINS
            listView.visibility = View.GONE
        } else { //TODO: FIX FOR USERS
            val arrayAdapter: ArrayAdapter<*>

            val products = DummyData.Products.getProductsNames()
            val context = context as MainActivity
            arrayAdapter = ArrayAdapter(
                context,
                R.layout.simple_list_item_1 ,products)
            listView.adapter = arrayAdapter
            listView.setOnItemClickListener { _, _, position, _ ->
                val intent = Intent(context, BookingActivity::class.java)
                intent.putExtra("selectedItemIndex", position)
                startActivity(intent)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}