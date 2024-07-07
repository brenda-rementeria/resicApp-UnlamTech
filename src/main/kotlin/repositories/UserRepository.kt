package repositories
import data.User

class InvalidCredentialsException(message: String): Exception(message)

object UserRepository {

    private val users = mutableListOf<User>()

    init {
        users.add(User(1504L, "bbayarri", "abc123", "Brian", "Bayarri", 3500000.50, "2022/12/10"))
        users.add(User(2802L, "AHOZ", "lock_password", "Aylen", "Hoz", 200000.50, "2021/01/11"))
        users.add(User(1510L, "Diegote", "@12345", "Diego", "Gonzalez", 120000.0, "2018/04/15"))
    }

    /* Toma un nombre de usuario y contraseña. Los busca en la lista de usuarios. Si el usuario no se encuentra se lanza una excepción,
    si se encuentra pero la contraseña no coincide, lanza otra excepción en donde se indica que la contraseña es incorrecta.
    Si ambas credenciales son válidas se devuelve el usuario encontrado */
    fun validateUsers(nickname: String, password: String): User? {
        val user = users.find { it.nickName == nickname }
        if (user == null) {
            throw InvalidCredentialsException("Usuario '$nickname' no encontrado")
        } else if (user.password != password) {
            throw InvalidCredentialsException("Contraseña incorrecta para el usuario '$nickname'")
        }
        return user
    }

    // Busca un usuario por su id en la lista de usuarios
    fun getUserById(userId: Long): User? {
        return users.find { it.id == userId }
    }

    // Muestra el saldo disponible del usuario
    fun showBalance(user: User){
        println("Saldo disponible para ${user.nickName}: ${user.money}")
    }


}









