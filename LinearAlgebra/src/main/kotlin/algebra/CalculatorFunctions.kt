package algebra

import structures.Matrix
import structures.Vector

private fun inputVector(): Vector {
    println("Input vector:\n")
    val vector = readlnOrNull()
    val numbers = vector!!.split(" ").map { it.toDouble() }
    return Vector(numbers)
}

private fun inputVectors(): List<Vector> {
    val vectors = mutableListOf<Vector>()
    println("Input vectors:\n")
    while (true) {
        val vector = readlnOrNull()
        if (vector.isNullOrBlank()) break
        val numbers = vector.split(" ").map { it.toDouble() }
        vectors.add(Vector(numbers))
    }
    return vectors
}

private fun inputMatrix(): Matrix {
    print("Input numbers of rows: ")
    val numRows = readlnOrNull()!!.toInt()
    print("Input numbers of columns: ")
    val numColumns = readlnOrNull()!!.toInt()

    val vectors = mutableListOf<Vector>()
    println("Input matrix:\n")
    for (i in 0..<numRows) {
        val vector = readlnOrNull()
        val numbers = vector!!.split(" ").map { it.toDouble() }.take(numColumns)
        vectors.add(Vector(numbers))
    }
    return Matrix(vectors)
}

fun basisDetermination() {
    println("-----  Basis Determination  ----")
    val vectors = inputVectors()
    println("The basis of the vectors is:")
    printSpace(determineBasis(vectors), "U")
}

fun basisSimplification() {
    println("-----  Basis Simplification  ----")
    val vectors = inputVectors()
    println("The simplified basis is:")
    printSpace(simplifyBasis(vectors), "U")
}

fun basisChange() {
    println("-----  Basis Change  ----")
    println("Input the current basis.")
    val basisFrom = inputVectors()
    println("Input the new basis.")
    val basisTo = inputVectors()
    println("The change basis matrix is:")
    println(basisChange(basisFrom, basisTo))
}

fun matrixBasisChange() {
    println("-----  Transformation Matrix Basis Change  ----")
    println("Input the transformation matrix.")
    val matrix = inputMatrix()
    println("Input the current basis B.")
    val basisFromB = inputVectors()
    println("Input the new basis B.")
    val basisToB = inputVectors()
    println("Input the current basis D.")
    val basisFromD = inputVectors()
    println("Input the new basis D.")
    val basisToD = inputVectors()
    println("The change basis transformation matrix is:")
    println(transformationMatrixBasisChange(basisFromB, basisToB, basisFromD, basisToD, matrix))
}

fun determinant() {
    println("-----  Determinant  ----")
    val matrix = inputMatrix()
    println("The determinant of the matrix is: ${computeDeterminant(matrix)}")
}

fun eigenValuesAndVectors() {
    println("-----  Eigenvalues and Eigenvectors  ----")
    val matrix = inputMatrix()
    println("Input the eigenvalues.")
    val input = readlnOrNull()
    val eigenvalues = input!!.split(" ").map { it.toDouble() }
    println("The eigenvectors of the matrix are:")
    printSpace(computeEigenvectors(matrix, eigenvalues), "E")
}

fun gaussianElimination() {
    println("-----  Gaussian Elimination  ----")
    val matrix = inputMatrix()
    println("Input the solution (b) vector.")
    val b = inputVector()
    println("The solution to the linear system of equations is:")
    printSolution(applyGaussianElimination(matrix, b))
}

fun affineIntersection() {
    println("-----  Affine Intersection  ----")
    println("Input the first position vector.")
    val vectorU = inputVector()
    println("Input the first subspace.")
    val spaceU = inputVectors()
    println("Input the second position vector.")
    val vectorV = inputVector()
    println("Input the second subspace.")
    val spaceV = inputVectors()
    println(" The intersection of the affine subspaces is:")
    printAffineSpace(affineIntersection(vectorU to spaceU, vectorV to spaceV), "W")
}

fun subspaceIntersection() {
    println("-----  Subspace Intersection  ----")
    println("-----  Affine Intersection  ----")
    println("Input the first subspace.")
    val spaceU = inputVectors()
    println("Input the second subspace.")
    val spaceV = inputVectors()
    println(" The intersection of the affine subspaces is:")
    printSpace(subspaceIntersection(spaceU, spaceV), "W")
}

fun inverse() {
    println("-----  Inverse  ----")
    val matrix = inputMatrix()
    println("The inverse of the matrix is:")
    println(inverseMatrix(matrix))
}

fun linearIndependence() {
    println("-----  Linear Independence  ----")
    val vectors = inputVectors()
    println(if (linearIndependent(vectors))
        "The vectors are linearly independent."
        else  "The vectors are linearly dependent")
}

fun orthogonalProjection() {
    println("-----  Orthogonal Projection  ----")
    val basis = inputVectors()
    println("The projection matrix is:")
    println(projectionMatrix(basis))
}

fun rankImageKernel() {
    println("-----  Rank / Image / Kernel  ----")
    val matrix = inputMatrix()
    println("The rank of the matrix is: ${computeRank(matrix)}")
    println("The image of the matrix is:")
    printSpace(computeImage(matrix), "I")
    println("The kernel of the matrix is:")
    printSpace(computeKernel(matrix), "K")
}

fun rotation() {
    println("-----  Rotation ----")
    print("Introduce the number of dimensions: ")
    val size = readlnOrNull()!!.toInt()
    print("Introduce the first dimension: ")
    val i = readlnOrNull()!!.toInt()
    print("Introduce the second dimension: ")
    val j = readlnOrNull()!!.toInt()
    print("Introduce the angle: ")
    val angle = readlnOrNull()!!.toDouble()
    println("The rotation matrix is:")
    println(rotationMatrix(size, i, j, angle))

}

fun rowEchelonForm() {
    println("-----  (Reduced) Row Echelon Form  ----")
    val matrix = inputMatrix()
    println("The Row Echelon Form of the matrix is:")
    println(computeRREF(matrix, false))
    println("The Reduced Row Echelon Form of the matrix is:")
    println(computeRREF(matrix, true))
}