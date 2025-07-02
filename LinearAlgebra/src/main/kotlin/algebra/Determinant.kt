package algebra

import structures.MAX_NUM_ROWS
import structures.Matrix
import structures.Vector
import kotlin.math.abs

// Rewrite the REF process to keep count of the number of interchanged rows
fun computeDeterminant(matrix: Matrix): Double {
    assert(matrix.numRows == matrix.numColumns)

    var sum = 0
    var pivots = (0..<matrix.numRows)
        .map { matrix[it].firstIndexNonZero() }
        .zip(0..<matrix.numRows)
        .sortedBy { it.first }
    var orderedMatrix = Matrix(pivots.map { matrix[it.second] })
    pivots.map { it.second }.forEachIndexed { index, i -> sum += abs(i - index) }

    for (pivot in 0..<matrix.numRows) {
        pivots = (0..<matrix.numRows)
            .map { orderedMatrix[it].firstIndexNonZero() }
            .zip(0..<matrix.numRows)
            .sortedBy { it.first }
        orderedMatrix = Matrix(pivots.map { orderedMatrix[it.second] })
        pivots.map { it.second }.forEachIndexed { index, i -> sum += abs(i - index) }

        val pivotCol = orderedMatrix[pivot].firstIndexNonZero()
        if (pivotCol == MAX_NUM_ROWS) continue

        // Reduce all the entries in the column to 0.0 (using the pivot)
        (0..<matrix.numRows).forEach {
            if (it < pivot) {
                return@forEach
            }
            if (it != pivot) {
                applyERO(
                    orderedMatrix,
                    it,
                    pivot,
                    EROType.MULTIPLY_ADD,
                    -orderedMatrix[it][pivotCol] / orderedMatrix[pivot][pivotCol]
                )
            }
        }
    }

    var determinant = 1.0
    for (i in 0..<matrix.numRows) {
        determinant *= orderedMatrix[i][i]
    }

    return if (sum / 2 % 2 == 0) determinant else -determinant
}
