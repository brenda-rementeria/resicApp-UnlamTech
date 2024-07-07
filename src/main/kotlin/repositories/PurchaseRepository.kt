package repositories
import data.*
import java.time.LocalDate



object PurchaseRepository {

    private val purchases = mutableListOf<Purchase>()

    init {
        purchases.add(Purchase(1L, 1504L, 1L, 350.50, "2023/01/01"))
        purchases.add(Purchase(2L, 1504L, 4L, 100.75, "2023/01/01"))
        purchases.add(Purchase(3L, 1504L, 7L, 250.50, "2023/01/01"))
        purchases.add(Purchase(4L, 1504L, 10L, 50.00, "2023/01/01"))
        purchases.add(Purchase(5L, 1504L, 3L, 1350.15, "2023/01/01"))
        purchases.add(Purchase(6L, 2802L, 2L, 20.30, "2023/01/01"))
        purchases.add(Purchase(7L, 2802L, 9L, 450.75, "2023/01/01"))
        purchases.add(Purchase(8L, 2802L, 2L, 500.00, "2023/01/01"))
        purchases.add(Purchase(9L, 1510L, 6L, 350.50, "2023/01/01"))
        purchases.add(Purchase(10L, 1510L, 5L, 150.00, "2023/01/01"))
    }


    // Muestra la lista de libros disponibles. Luego de elegir el libro se llama a la función "buyProduct"
    fun buyBook(user: User) {
        println("Listado de libros disponibles:")
        val books = ProductRepository.getProductsByType(ProductType.BOOK)
        books.forEachIndexed { index, book ->
            println("${index + 1}. ${book.name} - Precio: ${book.price}")
        }

        print("Seleccione el número del libro que desea comprar: ")
        val bookNumber = readLine()?.toIntOrNull() ?: -1
        buyProduct(user, books, bookNumber)
    }


    // Muestra la lista de discos de música disponibles. Luego de elegir el disco se llama a la función "buyProduct"
    fun buyMusicDisc(user: User) {
        println("Listado de discos de música disponibles:")
        val discs = ProductRepository.getProductsByType(ProductType.DISC)
        discs.forEachIndexed { index, disc ->
            println("${index + 1}. ${disc.name} - Precio: ${disc.price}")
        }

        print("Seleccione el número del disco que desea comprar: ")
        val discNumber = readLine()?.toIntOrNull() ?: -1
        buyProduct(user, discs, discNumber)
    }


    /* Utiliza una instancia de la interfaz "Commission" para calcular la comisión del producto,
     y luego llama a la función "createPurchase" para crear un registro de la compra.
     Se verifica si el usuario tiene suficiente saldo para comprar */
    fun buyProduct(user: User, products: List<Product>, productNumber: Int) {
        if (productNumber in 1..products.size) {
            val selectedProduct = products[productNumber - 1]
            if (selectedProduct.price <= user.money) {
                val commissionCalculator = when (selectedProduct.clasification) {
                    ProductClasification.GOLD -> GoldCommissionCalculator()
                    ProductClasification.SILVER -> SilverCommissionCalculator()
                    ProductClasification.PLATINUM -> PlatinumCommissionCalculator()
                    ProductClasification.BRONZE -> BronzeCommissionCalculator()
                }
                val totalAmount = calculateTotalAmount(selectedProduct, commissionCalculator)
                createPurchase(user.id, selectedProduct.id, totalAmount)
                println("Compra de ${selectedProduct.name} realizada correctamente.")
            } else {
                println("Lo sentimos, no tienes suficiente saldo para comprar este producto.")
            }
        } else {
            println("Opción inválida.")
        }
    }

    // Función para crear una nueva compra
    fun createPurchase(userId: Long, productId: Long, amount: Double) {
        val user = UserRepository.getUserById(userId)
        val product = ProductRepository.getProductById(productId)

        if (user != null && product != null && user.money >= amount) {
            val purchase = Purchase(
                id = generatePurchaseId(),
                userId = userId,
                productId = productId,
                amount = amount,
                createdDate = LocalDate.now().toString()
            )
            purchases.add(purchase)
            println("Compra realizada con éxito: ${product.name} - Monto: $amount")
            user.money -= amount
        } else {
            println("No se pudo realizar la compra, verifique el saldo disponible.")
        }
    }

    // Función para generar un nuevo id de compra
    fun generatePurchaseId(): Long {
        return purchases.size.toLong() + 1
    }


    /* Se utiliza dentro de la función "buyProduct" para determinar el monto total de la compra.
    Calcula el monto total de la compra sumando el precio del producto y la comisión aplicada al producto
    utilizando la instancia de la interfaz "Commission" */
    fun calculateTotalAmount(product: Product, commission: Commission): Double {
        val commission = commission.calculateCommission(product)
        return product.price + commission // Se suma la comisión al precio del producto
    }


    // Función para obtener todas las compras realizadas por un usuario
    fun getPurchasesByUser(userId: Long): List<Purchase> {
        return purchases.filter { it.userId == userId }
    }


    // Función para mostrar historial de compras de un usuario
    fun showPurchaseHistory(user: User) {
        val purchases = getPurchasesByUser(user.id)
        if (purchases.isNotEmpty()) {
            println("Historial de compras:")
            purchases.forEachIndexed { index, purchase ->
                val product = ProductRepository.getProductById(purchase.productId)
                if (product != null) {
                    println("${index + 1}. ${product.name} - Precio: ${product.price} - Fecha: ${purchase.createdDate}")
                } else {
                    throw NoSuchElementException("El producto con ID ${purchase.productId} no se encontró en el repositorio.")
                }
            }
        }
    }
}




