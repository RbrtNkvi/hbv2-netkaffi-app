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
import hi.netkaffi.service.dummyData

class ProductAdapter(context: Context, private val products: ArrayList<String>, private val favoriteComputers: HashSet<String>) :
    ArrayAdapter<String>(context, R.layout.item_product, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.item_product, parent, false)
        }

        val productNameTextView = itemView!!.findViewById<TextView>(R.id.productName)
        val heartButton = itemView.findViewById<Button>(R.id.heartButton)
        val bookButton = itemView.findViewById<Button>(R.id.bookButton)

        val product = products[position]
        productNameTextView.text = product

        // Set favorite icon based on whether the product is favorited
        if (dummyData.bookings.isFavorite(product)) {
            heartButton.isSelected = true
            heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_filled, 0, 0, 0)
        } else {
            heartButton.isSelected = false
            heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_border, 0, 0, 0)
        }

        // Set click listener for the heartButton
        heartButton.setOnClickListener {
            if (dummyData.bookings.isFavorite(product)) {
                // Product is already favorited, deselect it
                dummyData.bookings.removeFromFavorites(product)
                heartButton.isSelected = false
                heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_border, 0, 0, 0)
            } else {
                // Product is not favorited, select it
                dummyData.bookings.addToFavorites(product)
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
