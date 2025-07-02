package algebra

fun main() {
    println()
    println("Welcome to Darius's Linear Algebra Calculator!\n")

    val options = listOf(
        "Basis Determination",
        "Basis Simplification",
        "Basis Change",
        "Transformation Matrix Basis Change",
        "Determinant",
        "Eigenvalues and Eigenvectors",
        "Gaussian Elimination",
        "Affine Intersection",
        "Subspace Intersection",
        "Inverse",
        "Linear Independence",
        "Orthogonal Projection",
        "Rank / Image / Kernel",
        "Rotation",
        "(Reduced) Row Echelon Form"
    )

    options.forEachIndexed { index, s -> println("${index + 1} - $s") }

    while (true) {
        println()
        println("Choose your calculator from above:")
        val option = readlnOrNull()
        if (option.isNullOrBlank()) break

        when (option.toInt()) {
            1 -> basisDetermination()
            2 -> basisSimplification()
            3 -> basisChange()
            4 -> matrixBasisChange()
            5 -> determinant()
            6 -> eigenValuesAndVectors()
            7 -> gaussianElimination()
            8 -> affineIntersection()
            9 -> subspaceIntersection()
            10 -> inverse()
            11 -> linearIndependence()
            12 -> orthogonalProjection()
            13 -> rankImageKernel()
            14 -> rotation()
            15 -> rowEchelonForm()
            else -> println("Choose one of the options above.")
        }
    }

    println()
    println("Thank you for using the calculator!")
}
