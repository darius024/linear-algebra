package algebra

import structures.MAX_NUM_ROWS
import structures.Vector

fun linearIndependent(vectors: List<Vector>): Boolean =
    numLinearIndependent(vectors) == vectors.size

fun numLinearIndependent(vectors: List<Vector>): Int {
    val matrix = createMatrixColumns(vectors)
    val rrefMatrix = computeRREF(matrix, false)

    // Compute number of pivots
    var numPivots = 0
    for (i in 0..<matrix.numRows) {
        val possiblePivot = rrefMatrix[i].firstIndexNonZero()
        if (possiblePivot != MAX_NUM_ROWS) {
            numPivots++
        }
    }
    return numPivots
}
