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
        val context = if(UserService.ActiveUser.isAdmin() == true) context as AdminActivity else context as UserActivity

        val listData: ArrayList<String> = dummyData.bookings.getBookingsNames()
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1,
            listData
        )
        listView.adapter = arrayAdapter

        // Set item click listener to handle item clicks
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position) as String
                // Assuming you have an EditActivity to edit the selected item
                val booking = dummyData.bookings.getBookings()[position]
                val intent = Intent(context, EditActivity::class.java)
                intent.putExtra("selectedItem", selectedItem)
                intent.putExtra("selectedItemIndex", position)
                intent.putExtra("bookingDate", booking.date)
                startActivity(intent)
            }

        return root
    }

    override fun onResume() {
        super.onResume()

        // Reload data from dummyData
        val listData = dummyData.bookings.getBookingsNames()

        // Update the adapter with the new data
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            listData
        )

        // Set the adapter to the ListView
        binding.bookedList.adapter = arrayAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
