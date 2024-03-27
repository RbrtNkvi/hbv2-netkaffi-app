import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import hi.netkaffi.R
import hi.netkaffi.activities.BookingActivity

class ProductAdapter(context: Context, private val products: ArrayList<String>, ) :
    ArrayAdapter<String>(context, R.layout.item_product, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.item_product, parent, false)
        }

        val productNameTextView = itemView!!.findViewById<TextView>(R.id.productName)
        val heartButton = itemView.findViewById<Button>(R.id.heartButton)
        heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_border, 0, 0, 0)
        val bookButton = itemView.findViewById<Button>(R.id.bookButton)

        val product = products[position]
        productNameTextView.text = product

        // Set click listener for the heartButton
        heartButton.setOnClickListener {
            // Handle click action for the heartButton
            // For example, you can change its icon or perform other actions
            // based on whether the product is favorited or not
            // For now, let's just toggle the icon
            if (heartButton.isSelected) {
                // Product is already favorited, deselect it
                heartButton.isSelected = false
                heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_border, 0, 0, 0)
            } else {
                // Product is not favorited, select it
                heartButton.isSelected = true
                heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_filled, 0, 0, 0)
            }
        }
        bookButton.setOnClickListener {
            val intent = Intent(context, BookingActivity::class.java)
            intent.putExtra("productName", product)
            context.startActivity(intent)
        }


        return itemView
    }
}
