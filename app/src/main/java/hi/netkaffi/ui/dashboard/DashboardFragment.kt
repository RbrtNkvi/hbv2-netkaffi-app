package hi.netkaffi.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import hi.netkaffi.activities.EditActivity
import hi.netkaffi.activities.AdminActivity
import hi.netkaffi.activities.UserActivity
import hi.netkaffi.databinding.FragmentDashboardBinding
import hi.netkaffi.service.UserService
import hi.netkaffi.service.dummyData

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.bookedList
        val favoriteListView: ListView = binding.favoriteList
        val context = if(UserService.ActiveUser.isAdmin() == true) context as AdminActivity else context as UserActivity

        // Retrieve the list of bookings and favorite computers from dummyData
        val bookingsList: ArrayList<String> = dummyData.bookings.getBookingsNames()
        val favoriteComputersList: ArrayList<String> = dummyData.bookings.getFavoriteComputersNames()

        // Create an ArrayAdapter for the bookings list
        val bookingsAdapter: ArrayAdapter<String> = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1,
            bookingsList
        )

        // Create an ArrayAdapter for the favorite computers list
        val favoriteComputersAdapter: ArrayAdapter<String> = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1,
            favoriteComputersList
        )

        // Set the adapters to the ListViews
        listView.adapter = bookingsAdapter
        favoriteListView.adapter = favoriteComputersAdapter

        // Set item click listener for the bookings ListView to handle item clicks
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position) as String
                // Assuming you have an EditActivity to edit the selected item
                val intent = Intent(context, EditActivity::class.java)
                // Pass appropriate data to the EditActivity based on the selected item
                startActivity(intent)
            }

        // Set item click listener for the favorite computers ListView
        favoriteListView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position) as String
                // Handle item clicks for the favorite computers list
                // For example, you can open a detailed view or perform other actions
                // based on the selected favorite computer
            }

        return root
    }

    override fun onResume() {
        super.onResume()

        // Reload data from dummyData
        val bookingsList = dummyData.bookings.getBookingsNames()
        val favoriteComputersList = dummyData.bookings.getFavoriteComputersNames()

        // Update the adapters with the new data
        (binding.bookedList.adapter as ArrayAdapter<String>).clear()
        (binding.bookedList.adapter as ArrayAdapter<String>).addAll(bookingsList)
        (binding.favoriteList.adapter as ArrayAdapter<String>).clear()
        (binding.favoriteList.adapter as ArrayAdapter<String>).addAll(favoriteComputersList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
