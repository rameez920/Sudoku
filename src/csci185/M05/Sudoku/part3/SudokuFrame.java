package csci185.M05.Sudoku.part3;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class SudokuFrame extends JFrame {
	
	private SudokuPanel sudokuPanel;
	
	
	public SudokuFrame(String title, Puzzle puzzle) {
		super(title);
		sudokuPanel = new SudokuPanel(puzzle);
		this.setContentPane(sudokuPanel);
		
	}
	


}
