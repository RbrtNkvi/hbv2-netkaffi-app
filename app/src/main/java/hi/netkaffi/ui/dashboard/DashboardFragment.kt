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
import hi.netkaffi.activities.MainActivity
import hi.netkaffi.databinding.FragmentDashboardBinding
import hi.netkaffi.service.DummyData

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
        val context = requireContext() as MainActivity
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1,
            if (false) { //TODO: FIX FOR ADMINS
                DummyData.Bookings.getBookingsNames()
            }
            else { //TODO: FIX FOR USERS
                DummyData.Bookings.getBookingsNamesByUsername("321")
            }
        )
        listView.adapter = arrayAdapter

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val intent = Intent(context, EditActivity::class.java)
                intent.putExtra("selectedItemIndex", position)
                startActivity(intent)
            }
        return root
    }

    override fun onResume() {
        super.onResume()

        val listData = if (false) { //TODO: FIX FOR ADMINS
            DummyData.Bookings.getBookingsNames()
        }
        else { //TODO: FIX FOR USERS
            DummyData.Bookings.getBookingsNamesByUsername("321")
        }

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
