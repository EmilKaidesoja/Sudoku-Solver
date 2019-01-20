package com.Sudoku;

//This algorithm solves the given Sudoku puzzle with the brute force method and backtracking

public class SudokuSover {

	public static void main(String[] args) {
		int[][] Sudoku = {	{0, 0, 0, 8, 0, 2, 0, 0, 0},
							{0, 9, 0, 0, 0, 0, 0, 0, 3},
							{4, 8, 0, 0, 0, 0, 5, 6, 0},
							{9, 0, 0, 3, 2, 0, 0, 8, 5},
							{0, 0, 0, 0, 0, 0, 0, 0, 0},
							{6, 3, 0, 0, 8, 4, 0, 0, 2},
							{0, 7, 1, 0, 0, 0, 0, 5, 9},
							{8, 0, 0, 0, 0, 0, 0, 7, 0},
							{0, 0, 0, 4, 0, 5, 0, 0, 0}};
		SolveSudoku(Sudoku);
	}

	private static void SolveSudoku(int[][] Sudoku) {
		int i = 0;
		int j = 0;
		
		//Check if the board is complete
		//if not, solve the Sudoku
		if(CheckIfComplete(Sudoku)) {
			System.out.println("Sudoku Solved!\n");
			PrintSudoku(Sudoku);
		}else {
			
			//Find the first cell with 0
			for(int x = 0; x < Sudoku.length; x++) {
				for(int y = 0; y < Sudoku.length; y++) {
					if(Sudoku[x][y] == 0) {
						i = x;
						j = y;
						break;
					}
				}
			}
			
			//Find possible numbers for the cell at (i,j)
			int[] PossibleNumbers = GetValidNumbers(Sudoku, i, j);
			
			/*	Fill the cell with the possible number and call the
				method again until the Sudoku is solved 
				or the PossibleNumbers array consists only of zeros */
			
			for(int x = 1; x < 10; x++) {
				if(PossibleNumbers[x] != 0) {
					Sudoku[i][j] = PossibleNumbers[x];
					SolveSudoku(Sudoku);
				}
			}
			//Backtrack by initializing the cell back to zero
			Sudoku[i][j] = 0;
		}
	}
	
	//Checks if the Sudoku is complete
	//if any of the cells are zero return false 
	private static boolean CheckIfComplete(int[][] Sudoku) {
		for(int i = 0; i < Sudoku.length; i++) {
			for(int j = 0; j < Sudoku.length; j++) {
				if(Sudoku[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static int[] GetValidNumbers(int[][] Sudoku, int i, int j) {
		int[] PossibleNumbers = {0,0,0,0,0,0,0,0,0,0};
	
		/*Checks horizontal numbers
		 if a number already exists in the Sudoku board, initialize the position as 1 in the PossibleNumbers Array
		 After the two loops the Array consists of 1s and 0s.
		*/
		for(int y = 0; y < Sudoku.length; y++) {
			if(Sudoku[i][y] != 0) {
				PossibleNumbers[Sudoku[i][y]] = 1;
			}
		}
		// Checks vertical numbers
		for(int x = 0; x < Sudoku.length; x++) {
			if(Sudoku[x][j] != 0) {
				PossibleNumbers[Sudoku[x][j]] = 1;
			}
		}
		
		//Logic for the 3x3 squares
		int k;
		int l;
		
		if( i >= 0 && i <= 2) {
			k = 0;
		} else if(i >= 3 && i <= 5) {
			k = 3;
		}else {
			k = 6;
		}
		if( j >= 0 && j <= 2) {
			l = 0;
		} else if(j >= 3 && j <= 5) {
			l = 3;
		}else {
			l = 6;
		}
		
		for(int x = k; x < k + 3; x++) {
			for(int y = l; y < l + 3; y++) {
				if(Sudoku[x][y] != 0) {
					PossibleNumbers[Sudoku[x][y]] = 1;
				}
			}
		}
		
		//Check all zeros in the Array and replace them by their index number,
		//which is a possible entry for the cell in the Sudoku
		//Replace the number ones with 0 because they are already in the Sudoku
		for(int u = 0; u < PossibleNumbers.length; u++) {
			if(PossibleNumbers[u] == 0) {
				PossibleNumbers[u] = u;
			}else {
				PossibleNumbers[u] = 0;
			}
		}
		return PossibleNumbers;
	}
	
	private static void PrintSudoku(int[][]  SolvedSudoku) {
		int PrintLine = 0;
		int PrintBottomLine = 0;
		for(int i = 0; i < SolvedSudoku.length; i++) {
			for(int j = 0; j < SolvedSudoku.length; j++) {
				System.out.print(SolvedSudoku[i][j] + " ");
				PrintLine++;
				if(PrintLine == 3) {
					System.out.print("| ");
					PrintLine = 0;
				}
			}
			PrintBottomLine++;
			if(PrintBottomLine == 3) {
				System.out.println("\n-----------------------");
				PrintBottomLine = 0;
			}else {
				System.out.println("\n");
			}
		}
	}
}
