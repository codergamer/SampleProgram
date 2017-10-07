package SampleProgram;

public class Sudoku {

	public static void main(String[] args) {

		int[][] puzzle = { { 0, 0, 4, 0, 3, 0, 0, 0, 0 }, { 2, 9, 7, 0, 0, 0, 0, 4, 0 }, { 5, 0, 0, 9, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 3, 0, 0, 2, 0, 0 }, { 0, 0, 0, 6, 9, 1, 0, 0, 0 }, { 0, 0, 9, 0, 0, 4, 0, 0, 0 },
				{ 0, 0, 5, 0, 0, 2, 0, 0, 7 }, { 0, 8, 0, 0, 0, 0, 4, 9, 5 }, { 0, 0, 0, 0, 6, 0, 8, 2, 0 } };

		System.out.println(isSudokuValid(puzzle));

		int[][] solvedPuzzle = solveSudoku(puzzle);
		for (int i = 0; i < 9; i++) {
			System.out.println();
			for (int j = 0; j < 9; j++) {
				System.out.print(solvedPuzzle[i][j] + " ");
			}
		}
	}

	int[][] solveSudokuUtil(int[][] puzzle, int currentRow, int currentCol) {
		if (currentRow > 8) {
			if (isSudokuValid(puzzle)) {
				return puzzle;
			}
		}
		
		if(puzzle[currentRow][currentCol] != 0){
			if(currentCol == 8){
				currentRow++;
				currentCol = 0;
			}
			return solveSudokuUtil(puzzle, currentRow, currentCol);
		}
		return 0;
		
	}

	static boolean isSudokuValid(int[][] sudoku) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudoku[i][j] == 0) {
					System.out.println(
							"Sudoku invalid because of value at cell: " + i + ", " + j + " is:" + sudoku[i][j]);
					return false;
				}

				if (!isCellValid(i, j, sudoku)) {
					System.out.println(
							"Sudoku invalid because of value at cell: " + i + ", " + j + " is:" + sudoku[i][j]);

					return false;
				}
			}
		}
		return true;
	}

	static boolean isCellValid(int row, int col, int[][] sudoku) {
		int num = sudoku[row][col];
		// check in the 3*3 cell
		int startRow = 3 * (row / 3);
		int startCol = 3 * (col / 3);
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (i == row && j == col) {
					continue;
				}
				if (sudoku[i][j] == num) {
					return false;
				}
			}
		}
		// check the row
		for (int i = 0; i < 9; i++) {
			if (i == col) {
				continue;
			}
			if (sudoku[row][i] == num)
				return false;
		}

		// check the col
		for (int i = 0; i < 9; i++) {
			if (i == row) {
				continue;
			}
			if (sudoku[i][col] == num)
				return false;
		}

		return true;
	}

	static boolean canInsertInCell(int num, int[][] sudoku, int row, int col) {

		if (sudoku[row][col] != 0)
			return false;

		// check in the 3*3 cell
		int startRow = 3 * (row / 3);
		int startCol = 3 * (col / 3);
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (sudoku[i][j] != 0 && sudoku[i][j] == num) {
					return false;
				}
			}
		}
		// check the row
		for (int i = 0; i < 9; i++) {
			if (sudoku[row][i] != 0 && sudoku[row][i] == num)
				return false;
		}

		// check the col
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][col] != 0 && sudoku[i][col] == num)
				return false;
		}

		return true;
	}

}
