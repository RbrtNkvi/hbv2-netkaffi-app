package hi.netkaffi.ui.dashboard

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hi.netkaffi.activities.MainActivity
import hi.netkaffi.databinding.FragmentDashboardBinding
import hi.netkaffi.service.dummyData
import android.widget.AdapterView
import hi.netkaffi.activities.EditActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.bookedList
        val context = context as MainActivity

        val listData: ArrayList<String> = dummyData.data.getData()
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            context, // Use requireContext() instead of context as it's safer
            R.layout.simple_list_item_1, listData
        )
        listView.adapter = arrayAdapter

        // Set item click listener to handle item clicks
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position) as String
                // Assuming you have an EditActivity to edit the selected item
                val intent = Intent(context, EditActivity::class.java)
                // Pass the selected item's data to the EditActivity using extras
                intent.putExtra("selectedItem", selectedItem)
                startActivity(intent)
            }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
