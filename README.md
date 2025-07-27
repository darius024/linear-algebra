# Linear Algebra Calculator

A comprehensive Kotlin-based library and command-line calculator for performing a wide range of linear algebra operations. This project is designed for students, educators, and professionals who need to compute and analyze matrices, vectors, and subspaces in an interactive and extensible way.

## Features

- **Basis Operations**: Determine, simplify, and change bases for vector spaces.
- **Matrix Operations**: Compute determinants, inverses, rank, image, kernel, and perform Gaussian elimination.
- **Eigenvalues & Eigenvectors**: Calculate eigenvalues and eigenvectors, and diagonalize matrices.
- **Projections & Rotations**: Orthogonal projections and rotation matrices in arbitrary dimensions.
- **Row Echelon Forms**: Compute both Row Echelon Form (REF) and Reduced Row Echelon Form (RREF).
- **Subspace & Affine Space Intersections**: Find intersections of subspaces and affine spaces.
- **Interactive CLI**: Run the calculator interactively from the command line, choosing from a menu of operations.

## Project Structure

```
LinearAlgebra/
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       ├── algebra/        # Linear algebra algorithms and CLI
│   │       └── structures/     # Core data structures: Matrix, Vector, Space
│   └── test/
│       └── kotlin/             # (Optional) Unit tests
├── resources/                  # (Optional) Data/resources
├── LinearAlgebra.iml           # IntelliJ IDEA module file
└── Lecture_Notes.pdf           # (Optional) Reference notes
```

## Getting Started

### Prerequisites
- **Kotlin** (JVM 1.8+)
- **JDK 17** (as per project settings)
- An IDE such as IntelliJ IDEA or VS Code with Kotlin support

### Build & Run

1. **Clone the repository**
   ```bash
   git clone <repo-url>
   cd LinearAlgebra
   ```
2. **Open in IntelliJ IDEA** (recommended)
   - Import as a Gradle or IntelliJ project.
   - Ensure Kotlin and JDK 17 are configured.
3. **Run the Calculator**
   - Locate `Calculator.kt` in `src/main/kotlin/algebra/`
   - Run the `main()` function.
   - Alternatively, use the command line:
     ```bash
     kotlinc src/main/kotlin/structures/*.kt src/main/kotlin/algebra/*.kt -include-runtime -d LinearAlgebra.jar
     java -jar LinearAlgebra.jar
     ```

### Usage
- On running, you'll see a menu of linear algebra operations.
- Input vectors and matrices as prompted (space-separated values).
- Follow instructions for each operation.

## API Overview

### Source Code Overview

#### `src/main/kotlin/structures/`
- **Vector.kt**: Defines the `Vector` class for mathematical vectors, supporting arithmetic operations (addition, scalar multiplication, dot product), indexing, and utility methods for linear algebra (e.g., finding nonzero elements, appending values).
- **Matrix.kt**: Implements the `Matrix` class for 2D matrices, supporting matrix arithmetic (addition, multiplication, transpose), row/column access, and conversion between row/column representations. Includes validation for matrix shape and utility functions.
- **Space.kt**: Provides type aliases for subspaces and affine spaces, used to represent collections of vectors and affine combinations in higher-level algorithms.

#### `src/main/kotlin/algebra/`
- **Basis.kt**: Functions for determining a basis from a set of vectors, simplifying bases, and changing between bases. Also includes transformation matrix calculations for basis changes.
- **Calculator.kt**: The main entry point for the command-line calculator. Presents a menu of linear algebra operations and dispatches user input to the appropriate function.
- **CalculatorFunctions.kt**: Implements the interactive logic for each calculator operation, including user input parsing for vectors and matrices, and calls to core algorithms. Handles printing results in readable formats.
- **Determinant.kt**: Computes the determinant of a matrix using row operations and tracks row swaps for sign correction.
- **EigenValuesVectors.kt**: Functions for computing eigenvectors given eigenvalues, and for diagonalizing matrices.
- **GaussianElimination.kt**: Solves linear systems using Gaussian elimination, returning both particular and general solutions.
- **InverseMatrix.kt**: Calculates the inverse of a matrix using augmented matrices and row reduction.
- **Intersection.kt**: Finds intersections of subspaces and affine spaces, using Gaussian elimination and augmentation.
- **LinearIndependence.kt**: Checks if vectors are linearly independent and counts the number of independent vectors.
- **OrthogonalProjection.kt**: Computes orthogonal projection matrices and projects vectors onto subspaces.
- **RankImageKernel.kt**: Calculates the rank, image, and kernel of a matrix.
- **RREF.kt**: Computes Row Echelon Form (REF) and Reduced Row Echelon Form (RREF) of matrices.
- **Rotation.kt**: Constructs rotation matrices for arbitrary dimensions and applies rotations to vectors.
- **Utilities.kt**: Helper functions for matrix and vector manipulation, printing solutions, and formatting output for spaces and affine spaces. Also includes elementary row operation logic.

#### Other Files
- **LinearAlgebra.iml**: IntelliJ IDEA module configuration file.
- **Lecture_Notes.pdf**: Reference notes for linear algebra theory and examples (optional).
- **resources/**: Directory for additional data or resources (optional).
- **test/kotlin/**: (Optional) Directory for unit tests of algorithms and data structures.

## Testing
- (Optional) Add unit tests in `src/test/kotlin/` for core algorithms and data structures.
