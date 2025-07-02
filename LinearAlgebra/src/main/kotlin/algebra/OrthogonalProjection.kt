package algebra

import structures.Matrix
import structures.SubSpace
import structures.Vector

fun projectionMatrix(basis: SubSpace): Matrix {
    val matrixB = createMatrixColumns(basis)
    val transpose = matrixB.transpose()
    return matrixB * inverseMatrix(transpose * matrixB)!! * transpose
}

fun projectionPoint(basis: SubSpace, vector: Vector): Vector {
    val projectionMatrix = projectionMatrix(basis)
    return (projectionMatrix * createMatrixColumns(listOf(vector))).getColumn(0)
}
