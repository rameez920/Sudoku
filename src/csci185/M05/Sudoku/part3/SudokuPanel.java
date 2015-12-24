package csci185.M05.Sudoku.part3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import csci185.M05.Sudoku.part4.SudokuData;



public class SudokuPanel extends JPanel implements KeyListener, MouseListener {

	private Rectangle[][] boxArray = new Rectangle[9][9];
	
	private int boxWidth = 30;
	Color color = Color.white;
	Puzzle puzzle;
	
	
	public SudokuPanel(Puzzle myPuzzle) {
		puzzle = myPuzzle;
		this.addMouseListener(this);
		this.addKeyListener(this);
		this.setFocusable(true);
		
		this.setLayout(null);
		addButtons();
	}
	
	private void addButtons() {
		addCheckInputButton();
		addResetButton();
	}
	
	
	//TODO: make buttons work, pop up dialog windows, implement data checker
	private void addCheckInputButton() {
		JButton checkInputButton = new JButton("Check Input");
		checkInputButton.setBounds(375, 75, 125, 50);
		add(checkInputButton);
		checkInputButton.setFocusable(false);
		
		SudokuData dataChecker = new SudokuData(puzzle);
		
		checkInputButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (!dataChecker.isFilled()) {
					JOptionPane.showMessageDialog(null, "Please fill out the entire puzzle");
				} else if (dataChecker.isSudokuSolved()) {
					JOptionPane.showMessageDialog(null, "Congratulations! you solved the puzzle");
				} else {
					JOptionPane.showMessageDialog(null, "Sorry, puzzle is not correctly solved");
				}
				
			}
		});
	}
	
	private void addResetButton() {
		JButton resetButton = new JButton("Reset Puzzle");
		resetButton.setBounds(375, 140, 125, 50);
		add(resetButton);
		resetButton.setFocusable(false);
		
		resetButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					puzzle.resetPuzzle();
					repaint();
				} catch (FileNotFoundException e) {
				
					e.printStackTrace();
				}
				
			}
		});
	}
	
	
	
	@Override
	public void paintComponent(Graphics canvas) {
		super.paintComponent(canvas);
		canvas.setColor(color);
		createBoxes(canvas);
		canvas.setColor(Color.black);
		drawLines(canvas);
		drawPuzzle(canvas);
		canvas.setColor(Color.cyan);
		
	}
	
	
	private void createBoxes(Graphics canvas) {
		int x = 75, y = 75;
		
		Rectangle box;
		
		for (int row = 0; row < 9; row++) {
			x = 75;
		
			for (int col = 0; col < 9; col++) {
				
				box = new Rectangle(x, y, boxWidth, boxWidth);
				
				if (puzzle.getOriginalCell(row, col) == 0)
					canvas.setColor(Color.CYAN);
				else
					canvas.setColor(Color.white);
				
				paintBox(canvas, box);
				boxArray[row][col] = box;
				x+= 30;
			}
			y+= 30;
		}
	}
	
	private boolean isMouseInsideBox(Point p, Rectangle rect) {
		if (rect.contains(p))
			return true;
		else 
			return false;
	}

	
	private void drawLines(Graphics canvas) {
		Graphics2D canvas2D = (Graphics2D) canvas;
		int min = 75, max = 345;
		
		for(int i = min; i <= max; i+=boxWidth) {
			
			if (i == min || i == 165 || i == 255 || i == max) 
				 canvas2D.setStroke(new BasicStroke(5));
			else 
				canvas2D.setStroke(new BasicStroke(0));
			
			 
			 canvas2D.drawLine(i, min, i, max);
			 canvas2D.drawLine(min, i, max, i);
		}
		
	}
	
	private void drawPuzzle(Graphics canvas) {
		int x = 90, y = 95;
		
		for (int row = 0; row < 9; row++) {
			x = 90;
			
			for (int col = 0; col < 9; col++) {
				
				if (puzzle.getCell(row, col) != 0)
					drawCell(canvas, row, col, x, y);
				
				x+= 30;
			}
			y+= 30;
		}
	}
	
	//use canvas to paint rectangle at the coordinates found from rect
	private void paintBox(Graphics canvas, Rectangle rect) {
		
		int rectX = (int) rect.getX();
		int rectY = (int) rect.getY();
		
		canvas.fillRect(rectX, rectY, boxWidth, boxWidth);
	}
	
	private void drawCell(Graphics canvas, int row, int column, int x, int y) {
		String number = Integer.toString(puzzle.getCell(row, column));
		canvas.drawString(number, x, y);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		responseToKeyInput(arg0);
		
	}
	
	public void responseToKeyInput(KeyEvent e) {
		char input = e.getKeyChar();
		int num = Character.getNumericValue(input);
		puzzle.modifyCell(getCurrentRow(), getCurrentCol(), num);
		System.out.println();
		//puzzle.printModifiedPuzzle();
		color = Color.white;
		repaint();
		revalidate();
	}


	private int currentRow, currentCol;
	//private Rectangle currentBox;
	
	private int getCurrentRow() {
		return currentRow;
	}
	
	private int getCurrentCol() {
		return currentCol;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		Rectangle currentBox;
		int rectX, rectY;
		
		for (int row = 0; row < 9; row++) {
			
			for (int col = 0; col < 9; col++) {
				
				if (isMouseInsideBox(p, boxArray[row][col])) {
					 currentBox = boxArray[row][col];
					 rectX = (int) currentBox.getX();
					 rectY = (int) currentBox.getY();
					 //System.out.println("cell " + row + "," + col);
					  currentRow = row;
					  currentCol = col;
				}
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	


}
