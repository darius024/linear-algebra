package algebra

import structures.MAX_NUM_ROWS
import structures.Matrix
import structures.Vector

fun applyGaussianElimination(matrix: Matrix, b: Vector): Pair<Vector, List<Vector>>? {
    assert(matrix.numRows == b.length)

    augmentMatrix(matrix, b)
    val rrefMatrix = computeRREF(matrix, true)

    // Check if a solution exists
    for (i in matrix.numRows - 1 downTo 0) {
        if (rrefMatrix[i].firstIndexNonZero() == rrefMatrix.numColumns - 1) {
            return null
        }
    }

    // Compute pivots
    val pivots = MutableList(matrix.numColumns - 1) { false }
    for (i in 0..<matrix.numRows) {
        val possiblePivot = rrefMatrix[i].firstIndexNonZero()
        if (possiblePivot != MAX_NUM_ROWS) {
            pivots[possiblePivot] = true
        }
    }

    // Find the particular solution
    var count = 0
    val column = MutableList(matrix.numColumns - 1) {
        if (pivots[it]) rrefMatrix[count++][matrix.numColumns - 1]
        else 0.0
    }
    val solution = Vector(column)

    // Find the general solution
    val freeVariables = mutableListOf<Vector>()

    for (freeVar in 0..<matrix.numColumns - 1) {
        if (!pivots[freeVar]) {
            val newColumn = MutableList(matrix.numColumns - 1) { 0.0 }
            count = 0
            for (pos in 0..<matrix.numColumns - 1) {
                if (pos == freeVar) {
                    newColumn[pos] = 1.0
                } else if (pivots[pos]) {
                    newColumn[pos] = -rrefMatrix[count++][freeVar] + 0.0
                }
            }
            freeVariables.add(Vector(newColumn))
        }
    }
    return solution to freeVariables
}
