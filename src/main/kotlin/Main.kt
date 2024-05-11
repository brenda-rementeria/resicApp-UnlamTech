import data.Product
import repositories.ProductRepository
import repositories.PurchaseRepository

fun main(args: Array<String>) {
    println("Ingrese su ID de usuario para ver su historial de compras:")
    val userID = readln().toLong()
    imprimirHistorial(userID)
}

private fun imprimirHistorial(userid: Long) {
    val purchaseList = PurchaseRepository.get()
    val productList = ProductRepository

    for (purchase in purchaseList){
        if (purchase.userId == userid) {
            val producto = productList.getById(purchase.productId)
            println("${producto.id}, ${producto.type}, ${producto.name}, ${producto.author}, ${producto.category}, ${producto.clasification}, $${producto.price}, ${producto.releasedDate}, ${producto.stars}")
        }
    }
}