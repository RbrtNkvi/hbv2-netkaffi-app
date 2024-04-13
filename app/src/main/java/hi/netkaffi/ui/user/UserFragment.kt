package hi.netkaffi.ui.user

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import hi.netkaffi.activities.AdminActivity
import hi.netkaffi.activities.LoginActivity
import hi.netkaffi.activities.UserActivity
import hi.netkaffi.databinding.FragmentDashboardBinding
import hi.netkaffi.databinding.FragmentUserBinding
import hi.netkaffi.entities.User
import hi.netkaffi.service.FavouriteService
import hi.netkaffi.service.UserService

class UserFragment: Fragment() {

    private var _binding: FragmentUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.favouriteList
        val context = if(UserService.ActiveUser.isAdmin() == true) context as AdminActivity else context as UserActivity

        val favouriteService = FavouriteService()
        favouriteService.initialize(context)
        val user = UserService.ActiveUser.getUser()
        if(user != null) {
            favouriteService.fetchFavourites(user.username, callback = {
                val favourites = it.map{favs->"${favs.username} ${favs.productName}"}.toCollection(ArrayList())
                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    context,
                    R.layout.simple_list_item_1,
                    favourites
                )
                listView.adapter = arrayAdapter
            })
        }

        val signOut = binding.button
        signOut.setOnClickListener{
            val intent = Intent(context,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        return root
    }
}