package hi.netkaffi.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hi.netkaffi.activities.BookingActivity
import hi.netkaffi.activities.MainActivity
import hi.netkaffi.databinding.FragmentHomeBinding
import hi.netkaffi.entities.Product
import hi.netkaffi.service.dummyData


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.products
        val arrayAdapter: ArrayAdapter<*>
        if(dummyData.products.getProducts().isEmpty()){
            dummyData.products.addProduct(Product("Computer 1", "default", 1500, false))
            dummyData.products.addProduct(Product("Computer 2", "default", 1500, false))
            dummyData.products.addProduct(Product("Laptop 1", "default", 1500, false))
        }

        val products = dummyData.products.getProductsNames()
        val context = context as MainActivity
        arrayAdapter = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1 ,products)
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(context, BookingActivity::class.java)
            intent.putExtra("productName",listView.getItemAtPosition(i) as String)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}