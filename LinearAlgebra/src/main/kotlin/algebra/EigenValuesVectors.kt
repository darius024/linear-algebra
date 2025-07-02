package algebra

import structures.Matrix
import structures.Vector

// Computing eigenvalues is algebraically more complex

fun computeEigenvectors(matrix: Matrix, eigenvalues: List<Double>): List<Vector> =
    eigenvalues.toSet().toList().flatMap { value ->
        computeKernel(matrix + identityMatrix(matrix.numRows) * (-value))
    }

fun computeDiagonalisedMatrix(eigenvalues: List<Double>): Matrix =
    Matrix(eigenvalues.mapIndexed { index, value ->
        Vector(List(eigenvalues.size) { if (index == it) value else 0.0 })
    })
