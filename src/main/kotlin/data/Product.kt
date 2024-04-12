package data

data class Product(
    val id: Long,
    val name: String,
    val type: ProductType,
    val clasification: ProductClasification,
    val releasedDate: String,
    val category: String,
    val stars: Double,
    val price: Double,
    val logo: String,
    val author: String
)