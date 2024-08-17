interface IBaseNumber {
    val value: Int
    fun printValue()
}

// Clase para representar números primos
class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun printValue() {
        println("Número primo: $value")
    }
}

// Clase para representar números impares
class OddNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = calculateDivisors()

    override fun printValue() {
        println("Número impar: $value, Divisores: $divisors")
    }

    private fun calculateDivisors(): List<Int> {
        return (1..value).filter { value % it == 0 }
    }
}

// Clase para representar números pares
class EvenNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = calculateDivisors()

    override fun printValue() {
        println("Número par: $value, Divisores: $divisors")
    }

    private fun calculateDivisors(): List<Int> {
        return (1..value).filter { value % it == 0 }
    }
}

// Clase para procesar números y clasificarlos
class PrimeNumberProcessor(val range: IntRange) {
    private val primes = mutableListOf<PrimeNumber>()
    private val odds = mutableListOf<OddNumber>()
    private val evens = mutableListOf<EvenNumber>()

    init {
        classifyNumbers()
    }

    private fun classifyNumbers() {
        for (number in range) {
            when {
                isPrime(number) -> primes.add(PrimeNumber(number))
                number % 2 == 0 -> evens.add(EvenNumber(number))
                else -> odds.add(OddNumber(number))
            }
        }
    }

    private fun isPrime(number: Int): Boolean {
        if (number < 2) return false
        for (i in 2..Math.sqrt(number.toDouble()).toInt()) {
            if (number % i == 0) return false
        }
        return true
    }

    fun printResults() {
        primes.forEach { it.printValue() }
        evens.forEach { it.printValue() }
        odds.forEach { it.printValue() }
    }
}

fun main() {
    val processor = PrimeNumberProcessor(1..100)
    processor.printResults()
}
//Josué Moraga