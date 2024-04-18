package hi.netkaffi.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import hi.netkaffi.activities.AdminActivity
import hi.netkaffi.activities.UserActivity
import hi.netkaffi.databinding.FragmentDashboardBinding
import hi.netkaffi.service.BookingService
import hi.netkaffi.service.UserService
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
        val context = if(UserService.ActiveUser.isAdmin() == true) context as AdminActivity else context as UserActivity

        val bookingService = BookingService()
        bookingService.initialize(context)

        val user = UserService.ActiveUser.getUser()
        if(user != null) {
            bookingService.fetchBookings(user.username,
                callback = {
                    val bookings = it.map{booking->booking.toStringFormat()}.toCollection(ArrayList())
                    val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                        context,
                        android.R.layout.simple_list_item_1,
                        bookings
                    )
                    listView.adapter = arrayAdapter

                    listView.onItemClickListener =
                        AdapterView.OnItemClickListener { _, _, position, _ ->
                            val booking = it[position]
                            bookingService.deleteBooking(booking, callback = {
                                arrayAdapter.remove(arrayAdapter.getItem(position))
                                arrayAdapter.notifyDataSetChanged()
                            })
                        }
                })
        }

        return root
    }

    override fun onResume() {
        super.onResume()

        val listData = DummyData.Bookings.getBookingsNames()

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            listData
        )

        binding.bookedList.adapter = arrayAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
