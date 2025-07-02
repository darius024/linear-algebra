package algebra

import structures.AffineSpace
import structures.SubSpace
import structures.Vector

fun subspaceIntersection(spaceU: SubSpace, spaceV: SubSpace): SubSpace {
    val zeroVector = Vector(List(spaceU[0].length) { 0.0 })
    return affineIntersection(zeroVector to spaceU, zeroVector to spaceV).second
}

fun affineIntersection(spaceU: AffineSpace, spaceV: AffineSpace): AffineSpace {
    val matrix = createMatrixColumns(spaceU.second)
    spaceV.second.forEach { augmentMatrix(matrix, it) }
    val b = spaceV.first + (spaceU.first * (-1.0))

    val (constSol, varSol) = applyGaussianElimination(matrix, b)
        ?: return Vector(List(spaceU.first.length) { 0.0 }) to listOf()

    var constVector = spaceU.first
    spaceU.second.forEachIndexed { index, vector ->
        constVector += vector * constSol[index]
    }

    val varVector =
        varSol.map { vector ->
            spaceU.second.reduceIndexed { index, acc, column ->
                acc + column * vector[index]
            }
        }

    return constVector to varVector
}