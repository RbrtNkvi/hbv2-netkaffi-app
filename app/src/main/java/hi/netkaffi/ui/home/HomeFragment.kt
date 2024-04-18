package hi.netkaffi.ui.home

import hi.netkaffi.adapters.ProductAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import hi.netkaffi.activities.BookingActivity
import hi.netkaffi.activities.AdminActivity
import hi.netkaffi.activities.UserActivity
import hi.netkaffi.databinding.FragmentHomeBinding
import hi.netkaffi.service.FavouriteService
import hi.netkaffi.service.ProductService
import hi.netkaffi.service.UserService

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

        val listView: ListView = binding.products
        val user = UserService.ActiveUser.getUser()
        if(user != null) {
            val context =
                if (user.isAdmin == true) context as AdminActivity else context as UserActivity
            val productService = ProductService()
            productService.initialize(context)
            val favouriteService = FavouriteService()
            favouriteService.initialize(context)
            favouriteService.fetchFavourites(user.username, callback = {favourites ->
                val favourites1 = favourites.map{ f -> f.productName}.toCollection(ArrayList())
                productService.fetchProducts(url = "main", callback = { it ->
                    val productsDatabase =
                        ((it.filter { !it.deleted }).map { it.name }).toCollection(ArrayList())
                    val arrayAdapter = ProductAdapter(
                        context,
                        productsDatabase,
                        favourites1
                    )
                    listView.adapter = arrayAdapter
                    listView.setOnItemClickListener { _, _, i, _ ->
                        val intent = Intent(context, BookingActivity::class.java)
                        intent.putExtra("productName", listView.getItemAtPosition(i) as String)
                        startActivity(intent)
                    }
                })
            })
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
