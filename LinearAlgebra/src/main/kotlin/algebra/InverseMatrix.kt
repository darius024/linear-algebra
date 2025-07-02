package algebra

import structures.Matrix
import structures.Vector

fun inverseMatrix(matrix: Matrix): Matrix? {
    assert(matrix.numRows == matrix.numColumns)
    if (computeDeterminant(matrix) == 0.0) return null


    for (i in 0..<matrix.numRows) {
        val column = Vector(List(matrix.numRows) { if (i == it) 1.0 else 0.0 })
        augmentMatrix(matrix, column)
    }

    val rrefMatrix = computeRREF(matrix, true)

    val vectors = mutableListOf<Vector>()
    for (i in 0..<matrix.numRows) {
        val row = mutableListOf<Double>()
        for (j in 0..<matrix.numRows) {
            row.add(rrefMatrix[i][matrix.numRows + j])
        }
        vectors.add(Vector(row))
    }

    return Matrix(vectors)
}
