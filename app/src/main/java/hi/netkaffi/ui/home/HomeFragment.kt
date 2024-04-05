package hi.netkaffi.ui.home

import ProductAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hi.netkaffi.activities.BookingActivity
import hi.netkaffi.activities.AdminActivity
import hi.netkaffi.activities.UserActivity
import hi.netkaffi.databinding.FragmentHomeBinding
import hi.netkaffi.service.ProductService
import hi.netkaffi.service.UserService
import hi.netkaffi.service.api.ProductCallback
//import hi.netkaffi.adapters.ProductAdapter

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
        Log.i("Active User", "${UserService.ActiveUser.isAdmin()}")
        val context = if( UserService.ActiveUser.isAdmin() == true ) context as AdminActivity else context as UserActivity
        val productService = ProductService()
        productService.initialize(context)
        productService.fetchProducts(url = "main", callback = ProductCallback {
            val productsDatabase = ((it.filter{ !it.deleted }).map { it.name }).toCollection(ArrayList())
            val arrayAdapter = ProductAdapter(
                context,
                productsDatabase,
                HashSet<String>() // Pass an empty HashSet if favoriteComputers is not available
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
