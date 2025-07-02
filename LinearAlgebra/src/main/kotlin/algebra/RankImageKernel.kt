package algebra

import structures.Matrix
import structures.Vector

fun computeRank(matrix: Matrix): Int {
    val columns = mutableListOf<Vector>()
    for (i in 0..<matrix.numColumns) {
        columns.add(matrix.getColumn(i))
    }
    return numLinearIndependent(columns)
}

fun computeImage(matrix: Matrix): List<Vector> =
    List(matrix.numColumns) { matrix.getColumn(it) }

fun computeKernel(matrix: Matrix): List<Vector> {
    val zeroVector = Vector(List(matrix.numRows) { 0.0 })
    val solution = applyGaussianElimination(matrix, zeroVector)
    assert(solution != null)

    return if (solution!!.first == Vector(List(matrix.numColumns) { 0.0 })) {
        listOf(solution.first)
    } else {
        solution.second
    }
}
