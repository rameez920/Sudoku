package csci185.M05.Sudoku.part3;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class SudokuMain {
	private static SudokuPanel sudokuPanel;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		 String[] selectionValues = { "beginner", "intermediate", "expert" };
		    String initialSelection = "beginner";
		    Object selection = JOptionPane.showInputDialog(null, "Select Difficulty level",
		        "Sudoku", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
		
		
		String difficulty = (String) selection;
		Puzzle puzzle = new Puzzle(difficulty);
		SudokuFrame frame = new SudokuFrame("Sudoku Game", puzzle);
		//set size of window
		frame.setSize(600, 400);
		
		//display window on center of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
	}
}
