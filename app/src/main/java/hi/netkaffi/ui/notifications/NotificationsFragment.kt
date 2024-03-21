package hi.netkaffi.ui.notifications

//noinspection SuspiciousImport
import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hi.netkaffi.activities.MainActivity
import hi.netkaffi.activities.NewProductActivity
import hi.netkaffi.databinding.FragmentNotificationsBinding
import hi.netkaffi.service.dummyData

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val products = dummyData.products.getProductsNames()
        val context = context as MainActivity
        val arrayAdapter = ArrayAdapter(
            context,
            R.layout.simple_list_item_1 ,products)
        val listView = binding.productList
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            dummyData.products.removeProduct(i)
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        val button = binding.addProduct
        button.setOnClickListener { view ->
            val intent = Intent(context, NewProductActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}