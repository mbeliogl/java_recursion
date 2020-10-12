package com.maxim;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;

public class Bubbles {
    private static int [][] board;

    private static int startRow;
    private static int startCol;

    private static int numRows;
    private static int numCols;
    

    /**
     * RECURSIVE method to calculate the max amount of money you could collect
	 * The memo board is filled in like this:
	 * 		1st row is filled with the actual values and the rest are filled with - 1
     */

    public static int bubbleMoney(int row, int col) {
		int [][] memo = new int[numRows][numCols];

		if (numCols >= 0) System.arraycopy(board[0], 0, memo[0], 0, numCols);

		for(int r = 1; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
			memo[r][c] = - 1;
		    }
	    }

		return board[row][col] + Math.max(bubbleMoneyHelper(row-1,col-1,memo), bubbleMoneyHelper(row-1,col+1,memo))+1;
    }


    public static int bubbleMoneyHelper(int row, int col, int [][] memo) {

		if(col+1 > numCols || col-1 < 0) { return 0; } //checking if within bounds
		//if(row+1 > numRows || row-1 < 0) { return 0; }


		if(memo[row][col] != - 1) {
			return board[row][col];
	    }

		// Settings memo position equal to the number at the same position of board + the maximum value in the recursive call
		else {
			memo[row][col] = board[row][col] + Math.max(bubbleMoneyHelper(row-1,col-1,memo), bubbleMoneyHelper(row-1,col+1,memo));
			return memo[row][col];
	    }

    }


    /**
     * USAGE: java Bubbles startRow startCol
     *        Ex. java Bubbles  6 3
     *
     * Reads file and stores the dollar amounts
	 * Calls bubbleMoney method with the starting row and col
     */
    public static void main(String [] args) {

		if(args.length > 0) {
			try {
				startRow = Integer.parseInt(args[0]);
				startCol = Integer.parseInt(args[1]);
		    } catch (NumberFormatException e) {
				System.err.println("Argument" + args[0]);
				System.err.println("Argument" + args[1]);
				System.exit(1);
			}
		}

		File inFile = new File("C:\\Users\\MAX\\IdeaProjects\\HW5\\src\\com\\maxim\\board.txt");
		Scanner input = null;
	
		try {
			input = new Scanner(inFile);
	    } catch (FileNotFoundException fnf) {
			System.err.println("Input file not found");
			System.exit(1);
	    }

		numRows = input.nextInt();
		numCols = input.nextInt();
		input.nextLine();

		// Initializing the board array and populating it with values from file
		board = new int [numRows][numCols];
		int row = 0;
		while(input.hasNextLine()) {
			String line = input.nextLine();
			Scanner lineScanner = new Scanner(line);

			for(int i = 0; i < numCols; i++) {
				int num = lineScanner.nextInt();
				board[row][i] = num;
		    }
			row ++;
		}

		// creating a bubble object and calling the bubbleMoney method (memoized)
		Bubbles b = new Bubbles();
		System.out.println("Starting Position: " + "(" + startRow + "," + startCol + ")");
		System.out.println("Starting Number: " + Integer.toString(board[startRow][startCol]) + "\n");

		if(startRow == 0){
			System.out.println("Maximum Amount: " + Integer.toString(board[startRow][startCol]));
		}
		else {
			System.out.println("Maximum Amount: " + Integer.toString(b.bubbleMoney(startRow, startCol)));
		}
    }
}
