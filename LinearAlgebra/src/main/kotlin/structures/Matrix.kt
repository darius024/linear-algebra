package structures

data class Matrix(private var rows: List<Vector>) {

    init {
        val allRows: Boolean =
            rows.all { it.length == rows.firstOrNull()?.length }
        if (rows.isEmpty() || !allRows) {
            throw IllegalArgumentException()
        }
    }

    val numRows: Int
        get() = rows.size
    val numColumns: Int
        get() = rows.first().length

    operator fun get(indexI: Int): Vector {
        if (indexI !in 0..<numRows) {
            throw IndexOutOfBoundsException()
        }
        return rows[indexI]
    }

    operator fun get(indexI: Int, indexJ: Int): Double {
        if (indexI !in 0..<numRows || indexJ !in 0..<numColumns) {
            throw IndexOutOfBoundsException()
        }
        return rows[indexI][indexJ]
    }

    fun getRow(indexI: Int): Vector = get(indexI)

    // This can also be done by transposing the matrix.
    fun getColumn(indexJ: Int): Vector =
        Vector((0..<numRows).map { row -> rows[row][indexJ] })

    operator fun plus(other: Matrix): Matrix {
        if (numRows != other.numRows || numColumns != other.numColumns) {
            throw UnsupportedOperationException()
        }
        return Matrix((rows zip other.rows).map { it.first + it.second })
    }

    operator fun times(other: Matrix): Matrix {
        if (numColumns != other.numRows) {
            throw UnsupportedOperationException()
        }
        return Matrix(
            (0..<numRows).map
                { row ->
                    Vector(
                        (0..<other.numColumns).map { column ->
                            getRow(row) dot other.getColumn(column)
                        },
                    )
                },
        )
    }

    operator fun times(scalar: Double) = Matrix(rows.map { it * scalar })

    override fun toString(): String {
        val columnWidths: List<Int> =
            (0..<numColumns).map { column ->
                (0..<numRows).maxOf { row ->
                    getColumn(column)[row].toString().length
                }
            }
        return rows.joinToString(separator = "\n") {
            (0..<numColumns).joinToString(
                prefix = "[ ",
                postfix = " ]",
                separator = " ",
            ) { index ->
                " ".repeat(columnWidths[index] - it[index].toString().length)
                    .plus(it[index].toString())
            }
        }
    }

    // Iterator
    operator fun iterator(): Iterator<Vector> = object : Iterator<Vector> {
        private var currentIndex = 0

        override fun hasNext(): Boolean = currentIndex < numRows

        override fun next(): Vector = rows[currentIndex++]
    }

    // Functions used for extension.
    operator fun set(index: Int, value: Vector) {
        val m = rows.toMutableList()
        m[index] = value
        rows = m
    }

    fun transpose(): Matrix {
        val vectors = mutableListOf<Vector>()
        (0..<numColumns).forEach { col ->
            vectors.add(getColumn(col))
        }
        return Matrix(vectors)
    }
}

operator fun Double.times(matrix: Matrix) = matrix * this
