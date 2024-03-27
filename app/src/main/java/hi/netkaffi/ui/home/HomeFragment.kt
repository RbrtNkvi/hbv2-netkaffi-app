package hi.netkaffi.ui.home

import ProductAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hi.netkaffi.R
import hi.netkaffi.activities.BookingActivity
import hi.netkaffi.activities.MainActivity
import hi.netkaffi.databinding.FragmentHomeBinding
import hi.netkaffi.service.ProductService
import hi.netkaffi.service.api.ProductCallback


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
        val context = context as MainActivity
        val productService = ProductService()
        productService.initialize(context)
        productService.fetchProducts(url = "main", callback = ProductCallback {
            val productsDatabase = ((it.filter{ !it.deleted }).map { it.name }).toCollection(ArrayList())
            val arrayAdapter = ProductAdapter(
                context, productsDatabase
            )
            listView.adapter = arrayAdapter
            //held að þetta sé ekki notað
            listView.setOnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(context, BookingActivity::class.java)
                intent.putExtra("productName", listView.getItemAtPosition(i) as String)
                startActivity(intent)
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}