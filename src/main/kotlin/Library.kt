open class Library {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val start = LibraryImp()
            start.dataBaseConnect("library","books")
        }
    }
}