package csci185.M05.Sudoku.part4;

import csci185.M05.Sudoku.part3.Puzzle;

public class SudokuData {
	public static final int PUZZLE_MAX_SIZE = 9;
	public static final int REGION_MAX_SIZE = 3;
	
	public int[][] data;
	
	
	
	public SudokuData(Puzzle puzzle) {
		data = puzzle.getModifiedPuzzle();
	}
	
	public SudokuData(int[][] myData) {
		data = myData;
	}
	
	public boolean isSudokuSolved() {
		boolean result = false;
		if (isFilled()) {
			
			for (int i = 0; i < PUZZLE_MAX_SIZE; i++) {
				
				if (!(checkBox(i) && checkRow(i) && checkColumn(i))) 
					result = false;
				else
					result = true;
			}
		}
		return result;
	}
	
	public boolean checkBox(int box) {
		int[][] boxArray = generateRegionBox(box);
		int[] oneDimensionalArray = new int[PUZZLE_MAX_SIZE];
		
		int index = 0;
		for (int row = 0; row < REGION_MAX_SIZE; row++) {
			
			for (int col = 0; col < REGION_MAX_SIZE; col++) {
				oneDimensionalArray[index] = boxArray[row][col];
				index++;
			}
		}
		
		if (!containsDuplicates(oneDimensionalArray) && isValidSum(oneDimensionalArray))
			return true;
		else 
			return false;
		
	}
	
	private int[][] generateRegionBox(int box) {
		int[][] regionArray = new int[REGION_MAX_SIZE][REGION_MAX_SIZE];
		
		if (box == 0) {
			fillRegionBox(0, 2, 0, 2, regionArray);
		
		} else if (box == 1) {
			fillRegionBox(0, 2, 3, 5, regionArray);
		
		} else if (box == 2) {
			fillRegionBox(0, 2, 6, 8, regionArray);
		
		} else if (box == 3) {
			fillRegionBox(3, 5, 0, 2, regionArray);
		
		} else if (box == 4) {
			fillRegionBox(3, 5, 3, 5, regionArray);
		
		} else if (box == 5) {
			fillRegionBox(3, 5, 6, 8, regionArray);
		
		} else if (box == 6) {
			fillRegionBox(6, 8, 0, 2, regionArray);
		
		} else if (box == 7) {
			fillRegionBox(6, 8, 3, 5, regionArray);
		
		} else if (box == 8) {
			fillRegionBox(6, 8, 6, 8, regionArray);
		
		}
	
		
		return regionArray;
	}
	
	private void fillRegionBox(int minRow, int maxRow, int minCol, int maxCol, int[][] regionArray) {
		int row = 0, col = 0;
		
		for (int i = minRow; i <= maxRow; i++) {
			col = 0;
			for (int j = minCol; j <= maxCol; j++) {
				regionArray[row][col] = data[i][j];
				col++;
			}
			row++;
		}
	}
	
	
	public boolean checkRow(int row) {
		int[] nums = data[row];
		
		if (!containsDuplicates(nums) && isValidSum(nums))
			return true;
		else 
			return false;
	}
	
	public boolean checkColumn(int column) {
		int nums[] = new int[PUZZLE_MAX_SIZE];
		
		for (int i = 0; i < nums.length; i++) {
			nums[i] = data[i][column];
		}
		
		if (!containsDuplicates(nums) && isValidSum(nums))
			return true;
		else 
			return false;
	}
	
	private boolean containsDuplicates(int[] nums) {
		for (int i= 0;i < nums.length; i++) {
			  
			for (int j=i+1; j < nums.length; j++) {
			    
				if (i!=j && nums[i] == nums[j])
					return true;
			}
		}
		return false;
	}
	
	//if the row or column being analyzed contains all the 
	//valid numbers (1-9) the sum must equal 45
	private boolean isValidSum(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum+= nums[i];
		}
		
		if (sum == 45)
			return true;
		else
			return false;
	}
	
	public boolean isFilled() {
		for (int row = 0; row < PUZZLE_MAX_SIZE; row++) {
			
			for (int col = 0; col < PUZZLE_MAX_SIZE; col++) {
				
				if (data[row][col] == 0)
					return false;
			
			}
		}
		return true;
	}
	
}