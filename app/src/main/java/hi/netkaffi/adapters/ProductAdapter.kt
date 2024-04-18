package hi.netkaffi.adapters

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
import hi.netkaffi.entities.Favourite
import hi.netkaffi.service.FavouriteService
import hi.netkaffi.service.UserService

class ProductAdapter(context: Context, private val products: ArrayList<String>, private val favouriteComputers: ArrayList<String>) :
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

        if (favouriteComputers.contains(product)) {
            heartButton.isSelected = true
            heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_filled, 0, 0, 0)
        } else {
            heartButton.isSelected = false
            heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_border, 0, 0, 0)
        }

        val favouriteService = FavouriteService()
        favouriteService.initialize(context)
        val user = UserService.ActiveUser.getUser()

        heartButton.setOnClickListener {
            if (favouriteComputers.contains(product)) {

                if(user != null) {
                    favouriteService.deleteFavourite(Favourite(user.username,product), callback = {
                        heartButton.isSelected = false
                        heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_border, 0, 0, 0)
                    })
                }
            } else {

                if(user != null) {
                    favouriteService.addFavourite(user.username,product,callback={
                        heartButton.isSelected = true
                        heartButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_filled, 0, 0, 0)
                    })
                }
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
