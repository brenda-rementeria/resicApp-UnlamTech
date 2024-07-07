import data.User
import repositories.InvalidCredentialsException
import repositories.ProductRepository
import repositories.PurchaseRepository
import repositories.UserRepository



fun main() {
        println("Bienvenido!")

        var loggedInUser: User? = null

    try {
        print("Ingrese su nombre de usuario: ")
        val username = readLine()?.trim()
        print("Ingrese su contraseña: ")
        val password = readLine()?.trim()

        if (username.isNullOrEmpty() || password.isNullOrEmpty()) { // Si el usuario o contraseña es nulo se lanza una excepción
            throw InvalidCredentialsException("El campo de usuario o contraseña está vacío.")
        }

        loggedInUser = UserRepository.validateUsers(username, password) // Se llama la función "validateUsers" para validar credenciales

    } catch (e: InvalidCredentialsException) {
        println("Error: ${e.message}") // Se imprime el mensaje de la excepción "InvalidCredentialsException"
        return // Si alguna credencial es inválida se finaliza la ejecución
        }

        //Si "loggedInUser" no es nulo se ejecuta el bloque dentro de let. Llamamos a la funcion "showMainMenu" y pasamos "user" como argumento
        loggedInUser?.let { user -> showMainMenu(user) } ?: run { println("Error: No se pudo iniciar sesión.") }

        println("Gracias por su visita!, vuelva pronto")

}

/* Se define la función "showMainMenu" que muestra el menú principal y ofrece las opciones al usuario.
Dependiendo la elección, se llaman a diferentes funciones del repositorio de compras. */
fun showMainMenu(user: User) {
        println("Bienvenido ${user.nickName}")

        var option: Int
        do {
            println("Menú Principal:\n")
            println("1. Comprar libro")
            println("2. Comprar disco musical")
            println("3. Ver historial de compras")
            println("4. Ver saldo")
            println("5. Ver listado de libros y discos")
            println("6. Salir")
            print("Seleccione una opción: ")
            option = readLine()?.toIntOrNull()?: 0

            when (option) {
                1 -> PurchaseRepository.buyBook(user)
                2 -> PurchaseRepository.buyMusicDisc(user)
                3 -> PurchaseRepository.showPurchaseHistory(user)
                4 -> UserRepository.showBalance(user)
                5 -> println(ProductRepository.get())
                6 -> println("Saliendo del programa...")
                else -> println("Opción inválida. Por favor, seleccione una opción válida.")
            }
        } while (option != 6)
    }




