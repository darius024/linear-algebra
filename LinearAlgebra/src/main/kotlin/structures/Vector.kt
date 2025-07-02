package structures

const val MAX_NUM_ROWS = 10000

data class Vector(private var vector: List<Double>) {

    init {
        if (vector.isEmpty()) {
            throw IllegalArgumentException()
        }
    }

    val length: Int
        get() = vector.size

    operator fun get(index: Int): Double {
        if (index !in 0..<length) {
            throw IndexOutOfBoundsException()
        }
        return vector[index]
    }

    operator fun plus(other: Vector): Vector {
        if (length != other.length) {
            throw UnsupportedOperationException()
        }
        return Vector((vector zip other.vector).map { it.first + it.second })
    }

    operator fun times(scalar: Double) = Vector(vector.map { it * scalar })

    infix fun dot(other: Vector): Double {
        if (length != other.length) {
            throw UnsupportedOperationException()
        }
        return (vector zip other.vector).sumOf { it.first * it.second }
    }

    override fun toString(): String = vector.joinToString(
        prefix = "(",
        postfix = ")",
    )

    // Iterator
    operator fun iterator(): Iterator<Double> = object : Iterator<Double> {
        private var currentIndex = 0

        override fun hasNext(): Boolean = currentIndex < length

        override fun next(): Double = vector[currentIndex++]
    }

    // Functions used for extension.
    fun firstNonZero(): Double = vector.firstOrNull { it != 0.0 } ?: 1.0

    fun firstIndexNonZero(): Int =
        if (vector.indexOfFirst { it != 0.0 } != -1) vector.indexOfFirst { it != 0.0 }
        else MAX_NUM_ROWS

    fun append(element: Double) {
        val newVector = vector.toMutableList()
        newVector.add(element)
        vector = newVector
    }
}

operator fun Double.times(vector: Vector) = vector * this
