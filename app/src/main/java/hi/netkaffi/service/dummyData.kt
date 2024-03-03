package hi.netkaffi.service

class dummyData {
    object data{
        private val list = ArrayList<String>()
        fun getData(): ArrayList<String> {
            return list
        }

        fun addData(product: String) {
            list.add(product)
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
}