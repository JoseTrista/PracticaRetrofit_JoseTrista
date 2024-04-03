package trista.josecarlos.practicaretrofit_josetrista.data.model

data class Product(
    val limit: Int,
    val products: List<Products>,
    val skip: Int,
    val total: Int
)