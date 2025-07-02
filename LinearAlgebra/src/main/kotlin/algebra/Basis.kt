package algebra

import structures.MAX_NUM_ROWS
import structures.Matrix
import structures.SubSpace
import structures.Vector

// Operation on Bases

fun determineBasis(vectors: SubSpace): SubSpace {
    val matrix = createMatrixColumns(vectors)
    val rrefMatrix = computeRREF(matrix, false)

    return (0..<matrix.numRows).map {
        val possiblePivot = rrefMatrix[it].firstIndexNonZero()
        if (rrefMatrix[it].firstIndexNonZero() != MAX_NUM_ROWS) {
            vectors[possiblePivot]
        } else null
    }.filterNotNull()
}

fun simplifyBasis(vectors: SubSpace): SubSpace {
    val matrix = createMatrixRows(vectors)
    val rrefMatrix = computeRREF(matrix, true)
    return (0..<matrix.numRows).mapNotNull {
        if (rrefMatrix[it].firstIndexNonZero() != MAX_NUM_ROWS) rrefMatrix[it] else null
    }
}

// Basis Change
// Ib'b = Ib'e * Ieb
fun basisChange(basisFrom: SubSpace, basisTo: SubSpace): Matrix =
    inverseMatrix(createMatrixColumns(basisTo))!! * createMatrixColumns(basisFrom)

// Od'b' = I d'd * Odb * Ibb'
fun transformationMatrixBasisChange(
    basisFromB: SubSpace,
    basisToB: SubSpace,
    basisFromD: SubSpace,
    basisToD: SubSpace,
    transformationMatrix: Matrix,
): Matrix =
    basisChange(basisFromD, basisToD) *
            transformationMatrix *
            inverseMatrix(basisChange(basisFromB, basisToB))!!
