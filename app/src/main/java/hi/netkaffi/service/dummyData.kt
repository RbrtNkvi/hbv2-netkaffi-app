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
}