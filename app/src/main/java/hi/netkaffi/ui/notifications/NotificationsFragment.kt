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
import hi.netkaffi.activities.MainActivity
import hi.netkaffi.activities.NewProductActivity
import hi.netkaffi.databinding.FragmentNotificationsBinding
import hi.netkaffi.service.DummyData

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val products = DummyData.Products.getProductsNames()
        val context = context as MainActivity
        val arrayAdapter = ArrayAdapter(
            context,
            R.layout.simple_list_item_1 ,products)
        val listView = binding.productList
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            if (false) { //TODO: FIX FOR ADMINS
                DummyData.Products.removeProduct(position)
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
            else { //TODO: FIX FOR USERS

            }
        }
        if (false) { //TODO: FIX FOR ADMINS
            val button = binding.addProduct
            button.setOnClickListener { _ ->
                val intent = Intent(context, NewProductActivity::class.java)
                startActivity(intent)
            }
        }
        else { //TODO: FIX FOR USERS

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}