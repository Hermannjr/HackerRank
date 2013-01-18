package com.hans.hackerrank.level2.tictactoe;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {

	static final char EMPTY = '_';
	static final char X = 'X';
	static final char O = 'O';

	static char[][] cells = new char[3][3];
	private static int counter = 0;
	private static char p;
	private static char c;

	/*
	 * Complete the function below to print 2 integers separated by a single
	 * space which will be your next move
	 */
	static void nextMove(String player, String[] board) {
		p = player.charAt(0);
		c = switchPlayer(p);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cells[i][j] = board[i].charAt(j);
			}
		}

		evaluate(cells);

		// findAllRemainingMoves(player.charAt(0), cells);
	}

	static void findAllRemainingMoves(char player, char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == EMPTY) {
					char[][] newBoard = copyBoard(board);
					newBoard[i][j] = player;
					findAllRemainingMoves(switchPlayer(player), newBoard);
					evaluate(newBoard);
				}
			}
		}
	}

	static void evaluate(char[][] board) {
		if (twoInARow(board, p, true) || twoInACol(board, p, true) || twoInDiag(board, p, true))
			return;
		if (twoInARow(board, c, true) || twoInACol(board, c, true) || twoInDiag(board, c, true))
			return;
		if (canFork(board, p))
			return;
		if (canFork(board, c))
			return;
		if (board[1][1] == EMPTY && !boardIsEmpty(board)) {
			printPos(1, 1);
			return;
		}
		if (canMarkOpposite(board))
			return;
		if (canMarkCorner(board))
			return;
		markSide(board);

	}

	private static boolean markSide(char[][] board) {
		if (board[0][1] == EMPTY) {
			printPos(0, 1);
			return true;
		}
		if (board[1][0] == EMPTY) {
			printPos(1, 0);
			return true;
		}
		if (board[1][2] == EMPTY) {
			printPos(1, 2);
			return true;
		}
		if (board[2][1] == EMPTY) {
			printPos(2, 1);
			return true;
		}
		return false;
	}

	private static boolean canMarkCorner(char[][] board) {
		if (board[2][2] == EMPTY) {
			printPos(2, 2);
			return true;
		}
		if (board[2][0] == EMPTY) {
			printPos(2, 0);
			return true;
		}
		if (board[0][0] == EMPTY) {
			printPos(0, 0);
			return true;
		}
		if (board[0][2] == EMPTY) {
			printPos(0, 2);
			return true;
		}
		return false;
	}

	private static boolean canMarkOpposite(char[][] board) {
		if (board[0][0] == c && board[2][2] == EMPTY) {
			printPos(2, 2);
			return true;
		}
		if (board[0][2] == c && board[2][0] == EMPTY) {
			printPos(2, 0);
			return true;
		}
		if (board[2][2] == c && board[0][0] == EMPTY) {
			printPos(0, 0);
			return true;
		}
		if (board[2][0] == c && board[0][2] == EMPTY) {
			printPos(0, 2);
			return true;
		}
		return false;
	}

	private static boolean boardIsEmpty(char[][] board) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] != EMPTY)
					return false;
		return true;
	}

	private static boolean canFork(char[][] board, char m) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == EMPTY) {
					char[][] tmpBoard = copyBoard(board);
					tmpBoard[i][j] = m;
					if (twoInDiag(tmpBoard, m, false) && twoInARow(tmpBoard, m, false)) {
						if (m == c && checkDoubleFork(board)) {
						} else {
							printPos(i, j);
						}
						return true;
					}
					if (twoInDiag(tmpBoard, m, false) && twoInACol(tmpBoard, m, false)) {
						if (m == c && checkDoubleFork(board)) {
						} else {
							printPos(i, j);
						}
						return true;
					}
					if (twoInARow(tmpBoard, m, false) && twoInACol(tmpBoard, m, false)) {
						if (m == c && checkDoubleFork(board)) {
						} else {
							printPos(i, j);
						}
						return true;
					}
				}
			}
		return false;
	}

	private static boolean checkDoubleFork(char[][] board) {
		if ((board[0][0] == c && board[2][2] == c) || (board[0][2] == c && board[2][0] == c)) {
			return markSide(board);
		}
		if ((board[1][1] == c) && (board[0][0] == c) || board[0][2] == c || board[2][0] == c || board[2][2] == c)
			return canMarkCorner(board);
		return false;
	}

	private static boolean twoInDiag(char[][] board, char m, boolean print) {
		// left to right diag
		if (board[0][0] == board[1][1] && board[0][0] == m && board[2][2] == EMPTY) {
			if (print)
				printPos(2, 2);
			return true;
		}
		if (board[0][0] == board[2][2] && board[0][0] == m && board[1][1] == EMPTY) {
			if (print)
				printPos(1, 1);
			return true;
		}
		if (board[1][1] == board[2][2] && board[1][1] == m && board[0][0] == EMPTY) {
			if (print)
				printPos(0, 0);
			return true;
		}
		// right to left diag
		if (board[0][2] == board[1][1] && board[0][2] == m && board[2][0] == EMPTY) {
			if (print)
				printPos(2, 0);
			return true;
		}
		if (board[0][2] == board[2][0] && board[0][2] == m && board[1][1] == EMPTY) {
			if (print)
				printPos(1, 1);
			return true;
		}
		if (board[1][1] == board[2][0] && board[1][1] == m && board[0][2] == EMPTY) {
			if (print)
				printPos(0, 2);
			return true;
		}
		return false;
	}

	private static boolean twoInACol(char[][] board, char m, boolean print) {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == board[1][i] && board[0][i] == m && board[2][i] == EMPTY) { // XX_
				if (print)
					printPos(2, i);
				return true;
			}
			if (board[0][i] == board[2][i] && board[0][i] == m && board[1][i] == EMPTY) { // X_X
				if (print)
					printPos(1, i);
				return true;
			}
			if (board[1][i] == board[2][i] && board[1][i] == m && board[0][i] == EMPTY) {// _XX
				if (print)
					printPos(0, i);
				return true;
			}
		}
		return false;
	}

	/*
	 * Checks if there are two pieces in a row
	 */
	static boolean twoInARow(char[][] board, char m, boolean print) {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][0] == m && board[i][2] == EMPTY) { // XX_
				if (print)
					printPos(i, 2);
				return true;
			}
			if (board[i][0] == board[i][2] && board[i][0] == m && board[i][1] == EMPTY) { // X_X
				if (print)
					printPos(i, 1);
				return true;
			}
			if (board[i][1] == board[i][2] && board[i][1] == m && board[i][0] == EMPTY) {// _XX
				if (print)
					printPos(i, 0);
				return true;
			}
		}
		return false;
	}

	private static void printPos(int r, int c) {
		System.out.println(r + " " + c);
	}

	static char[][] copyBoard(char[][] board) {
		char[][] newBoard = new char[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				newBoard[i][j] = board[i][j];
		return newBoard;
	}

	static char switchPlayer(char player) {
		return player == X ? O : X;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String player;
		String board[] = new String[3];

		// If player is X, I'm the first player.
		// If player is O, I'm the second player.
		player = in.next();

		// Read the board now. The board is a 3x3 array filled with X, O or _.
		for (int i = 0; i < 3; i++) {
			board[i] = in.next();
		}

		nextMove(player, board);

	}
}