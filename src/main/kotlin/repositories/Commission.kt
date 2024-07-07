package repositories

import data.Product
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime


interface Commission {

    fun calculateCommission(product: Product): Double
}

class GoldCommissionCalculator : Commission {
    override fun calculateCommission(product: Product): Double {
        return product.price * 0.02 // 2% de comisión
    }
}

class SilverCommissionCalculator : Commission {
    override fun calculateCommission(product: Product): Double {
        val currentTime = LocalTime.now()
        val startTime = LocalTime.parse("15:00")
        val endTime = LocalTime.parse("22:30")
        return if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            product.price * 0.01 // 1% de comisión
        } else {
            product.price * 0.03 // 3% de comisión
        }
    }
}

class PlatinumCommissionCalculator : Commission {
    override fun calculateCommission(product: Product): Double {
        return if (LocalDate.now().dayOfWeek in listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)) {
            product.price * 0.03 // 3% de comisión
        } else {
            product.price * 0.0075 // 0.75% de comisión
        }
    }
}

class BronzeCommissionCalculator : Commission {
    override fun calculateCommission(product: Product): Double {
        return 0.0 // Sin comisión
    }
}

