package hi.netkaffi.service
 import android.util.Log
 import hi.netkaffi.entities.User
class dummyData {

    object data{
        private val list = ArrayList<String>()
        fun getData(): ArrayList<String> {
            return list
        }

        fun addData(product: String) {
            list.add(product)
        }

        fun updateData(index: Int, updatedBooking: String) {
            if (index in 0 until list.size) {
                list[index] = updatedBooking
            }
        }

        fun removeData(booking: String) {
            list.remove(booking)
        }
    }

    object products {
        private val list = ArrayList<String>()

        fun getProducts(): ArrayList<String> {
            return list
        }

        fun addProduct(product: String) {
            list.add(product)
        }

        fun removeProduct(index: Int){
            list.removeAt(index)
        }
    }
    object Users {
        private val list_users = ArrayList<User>()

        fun getUsers(): ArrayList<User> {
            return list_users
        }
        fun isUsername(user2: User): Boolean{
            for (user in list_users){
                return user.username != user2.username
            }
            return true
        }

        fun isUser(user2: User): Boolean{
            for (user in list_users){
                if (user.username == user2.username)
                    if (user.password == user2.password)
                        return true
            }
            return false
        }
        fun addUsers(user: User) {
            list_users.add(user)
            val temp = list_users.size.toString()
            print(temp)

        }

        fun removeUsers(index: Int) {
            list_users.removeAt(index)
        }
    }
}
