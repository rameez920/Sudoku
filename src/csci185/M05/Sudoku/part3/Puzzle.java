package csci185.M05.Sudoku.part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle {
	
	private static int rowSize = 9, colSize = 9;
	private static int[][] initialPuzzle = new int[rowSize][colSize];
	private static int[][] modifiedPuzzle = new int[rowSize][colSize];
	private File puzzleFile;

	
	public Puzzle(String difficulty) throws FileNotFoundException {
		puzzleFile = getRandomFile(difficulty);
		readFromFile(puzzleFile);
		
	}
	
	private static void readFromFile(File file) throws FileNotFoundException {
		
		Scanner stream = new Scanner(file);
		String line = stream.nextLine();
		
		int currentRow = 0;
		int currentNum;
		
		while (stream.hasNextLine()) {
			 line = stream.nextLine();
			 String[] row = line.split(",");
			 
			 for (int col = 0; col < colSize; col++) {
				 currentNum = Integer.parseInt(row[col]);
				 initialPuzzle[currentRow][col] = currentNum;
				 modifiedPuzzle[currentRow][col] = currentNum;
			 }
			 currentRow++;
		}
		stream.close();
	}
	
	private static File getRandomFile(String difficulty) {
		
		File fileList[] = new File("data/Puzzles/" + difficulty.toLowerCase()).listFiles();
		
		//random number between 0 and 9 inclusive
		int randomNum = (int) (Math.random() * (9 - 0 + 1) + 0);
		
		return fileList[randomNum];
	}
	
	
	public int getCell(int row, int column) {
		return modifiedPuzzle[row][column];
		
	}
	
	public int getOriginalCell(int row, int column) {
		return initialPuzzle[row][column];
		
	}
	
	public void modifyCell(int row, int col, int newNum) {
		if (initialPuzzle[row][col] == 0)
			modifiedPuzzle[row][col] = newNum;
	}
	
	public int[][] getModifiedPuzzle() {
		return modifiedPuzzle;
	}
	
	public void printModifiedPuzzle() {
		for (int row = 0; row < rowSize; row++ ) {
			System.out.println();
			for (int col = 0; col < colSize; col++) {
				System.out.print( modifiedPuzzle[row][col] + ", " );
			}
		}
	}
	
	public void resetPuzzle() throws FileNotFoundException {
		Scanner stream = new Scanner(puzzleFile);
		String line = stream.nextLine();
		
		int currentRow = 0;
		int currentNum;
		
		while (stream.hasNextLine()) {
			 line = stream.nextLine();
			 String[] row = line.split(",");
			 
			 for (int col = 0; col < colSize; col++) {
				 currentNum = Integer.parseInt(row[col]);
				 modifiedPuzzle[currentRow][col] = currentNum;
			 }
			 currentRow++;
		}
		stream.close();
	}
}
