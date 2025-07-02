package algebra

import structures.AffineSpace
import structures.Matrix
import structures.SubSpace
import structures.Vector

enum class EROType {
    MULTIPLY, INTERCHANGE, MULTIPLY_ADD
}

fun applyERO(
    matrix: Matrix,
    rowTo: Int,
    rowFrom: Int,
    type: EROType,
    coefficient: Double,
): Matrix {
    when (type) {
        EROType.MULTIPLY -> {
            matrix[rowTo] = matrix[rowTo] * (1.0 / coefficient) + Vector(List(matrix.numColumns) { 0.0 })
        }

        EROType.INTERCHANGE -> {
            val lineAux = matrix[rowTo]
            matrix[rowTo] = matrix[rowFrom]
            matrix[rowFrom] = lineAux
        }

        EROType.MULTIPLY_ADD -> {
            matrix[rowTo] += matrix[rowFrom] * coefficient + Vector(List(matrix.numColumns) { 0.0 })
        }
    }
    return matrix
}

fun orderRows(matrix: Matrix): Matrix {
    val pivots = (0..<matrix.numRows)
        .map { matrix[it].firstIndexNonZero() }
        .zip(0..<matrix.numRows)
        .sortedBy { it.first }
    return Matrix(pivots.map { matrix[it.second] })
}

fun augmentMatrix(matrix: Matrix, b: Vector) {
    for (i in 0..<matrix.numRows) {
        matrix[i].append(b[i])
    }
}

fun createMatrixColumns(vectors: List<Vector>): Matrix {
    assert(vectors.isNotEmpty())
    return createMatrixRows(vectors).transpose()
}

fun createMatrixRows(vectors: List<Vector>): Matrix {
    assert(vectors.isNotEmpty())
    return Matrix(vectors)
}

fun identityMatrix(size: Int): Matrix =
    Matrix((0..<size).map { row ->
        Vector(List(size) { if (row == it) 1.0 else 0.0 })
    })

fun printSolution(solution: Pair<Vector, List<Vector>>?) {
    if (solution == null) {
        println(null)
        return
    }
    val constSol = solution.first
    val varSols = solution.second
    val numLines = constSol.length

    for (i in 0..<numLines) {
        if (i == numLines / 2) print("S  =  ")
        else print("      ")
        print("[${constSol[i]}]".padStart(6))
        varSols.forEachIndexed { index, vector ->
            print("  +  ")
            if (i == numLines / 2) print(('a'.code + index).toChar()) else print(" ")
            print(" [${vector[i]}]".padStart(6))
        }
        println()
    }
}

fun printSpace(vectors: SubSpace, name: String) {
   assert(vectors.isNotEmpty())

    val numLines = vectors[0].length

    for (i in 0..<numLines) {
        if (i == numLines / 2) print("$name  =  span  ")
        else print("            ")
        print("{ ")
        vectors.forEach {
            print(" [${it[i]}]".padStart(6))
        }
        print(" }")
        println()
    }
}

fun printAffineSpace(affineSpace: AffineSpace, name: String) {

    val vector = affineSpace.first
    val vectors = affineSpace.second
    val numLines = vector.length

    for (i in 0..<numLines) {
        if (i == numLines / 2) print("$name  =  [${vector[i]}]   +  span   { ")
        else print("      [${vector[i]}]           ")
        vectors.forEach {
            print(" [${it[i]}]".padStart(6))
        }
        print(" }")
        println()
    }
}
