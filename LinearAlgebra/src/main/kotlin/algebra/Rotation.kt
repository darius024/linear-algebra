package algebra

import structures.Matrix
import structures.Vector
import kotlin.math.cos
import kotlin.math.sin

fun rotationMatrix(size: Int, i: Int, j: Int, angle: Double): Matrix {
    assert(i < size && j < size)

    val identity = identityMatrix(size)
    val vectorI = Vector(List(size) {
        when (it) {
            i -> cos(angle)
            j -> sin(angle)
            else -> 0.0
        }
    })
    val vectorJ = Vector(List(size) {
        when (it) {
            j -> cos(angle)
            i -> sin(angle)
            else -> 0.0
        }
    })
    identity[i] = vectorI
    identity[j] = vectorJ

    return identity
}

fun rotateVector(vector: Vector, i: Int, j: Int, angle: Double): Vector {
    val rotationMatrix = rotationMatrix(vector.length, i, j, angle)
    return (rotationMatrix * createMatrixColumns(listOf(vector))).getColumn(0)
}
