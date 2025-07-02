package algebra

import structures.MAX_NUM_ROWS
import structures.Matrix

fun computeRREF(matrix: Matrix, rref: Boolean): Matrix {
    var orderedMatrix = orderRows(if (rref) computeRREF(matrix, false) else matrix)

    // We apply EROs to reach an upper triangular matrix or the equivalent
    for (pivot in 0..<matrix.numRows) {
        orderedMatrix = orderRows(orderedMatrix)
        val pivotCol = orderedMatrix[pivot].firstIndexNonZero()
        if (pivotCol == MAX_NUM_ROWS) continue

        // Simplify the pivot to 1
        if (rref) {
            applyERO(orderedMatrix, pivot, pivot, EROType.MULTIPLY, orderedMatrix[pivot][pivotCol])
        }

        // Reduce all the entries in the column to 0.0 (using the pivot)
        (0..<matrix.numRows).forEach {
            if (!rref && it < pivot) { return@forEach }
            if (it != pivot) {
                applyERO(orderedMatrix, it, pivot, EROType.MULTIPLY_ADD, -orderedMatrix[it][pivotCol] / orderedMatrix[pivot][pivotCol])
            }
        }
    }
    return orderedMatrix
}
