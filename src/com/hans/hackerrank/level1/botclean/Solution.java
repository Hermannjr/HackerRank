package com.hans.hackerrank.level1.botclean;
import java.util.Scanner;

public class Solution {

	enum Move {
		RIGHT, LEFT, UP, DOWN, CLEAN
	}

	static int moves = Integer.MAX_VALUE;
	static final char DIRTY = 'd';
	static final char CLEAN = '-';
	static final char BOT = 'b';
	static final char VISITED = 'v';

	/* Head ends here */
	static void next_move(int posx, int posy, String[] board) {
		char[][] cboard = new char[5][5];
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				cboard[i][j] = board[i].charAt(j);

		Move m = Move.CLEAN;

		if (cboard[posx][posy] == DIRTY) {
			System.out.println(m);
			return;
		}

		if (posy != 4) {
			int c = move(posx, posy + 1, Move.RIGHT, cboard, 1);
			if (moves > c) {
				moves = c;
				m = Move.RIGHT;
			}
		}
		if (posy != 0) {
			int c = move(posx, posy - 1, Move.LEFT, cboard, 1);
			if (moves > c) {
				moves = c;
				m = Move.LEFT;
			}
		}
		if (posx != 0) {
			int c = move(posx - 1, posy, Move.UP, cboard, 1);
			if (moves > c) {
				moves = c;
				m = Move.UP;
			}
		}
		if (posx != 4) {
			int c = move(posx + 1, posy, Move.DOWN, cboard, 1);
			if (moves > c) {
				moves = c;
				m = Move.DOWN;
			}
		}
		System.out.println(m);
	}

	static int move(int x, int y, Move m, char[][] board, int count) {
		if (done(board)) { // done
			return count;
		}

		int newCount = count + 1; // Not done, we HAVE to move

		if (board[x][y] == DIRTY) { // we are on a dirty tile, clean it
			board[x][y] = VISITED;
			int c = move(x, y, Move.CLEAN, board, newCount);
			board[x][y] = DIRTY;
			return c;
		}
		int tmpCount = Integer.MAX_VALUE;
		board[x][y] = VISITED;
		if (y != 4 && board[x][y + 1] != VISITED) { // move right
			tmpCount = Math.min(tmpCount, move(x, y + 1, Move.RIGHT, board, newCount));
		}
		if (y != 0 && board[x][y - 1] != VISITED) { // move left
			tmpCount = Math.min(tmpCount, move(x, y - 1, Move.LEFT, board, newCount));
		}
		if (x != 4 && board[x + 1][y] != VISITED) { // move down
			tmpCount = Math.min(tmpCount, move(x + 1, y, Move.DOWN, board, newCount));
		}
		if (x != 0 && board[x - 1][y] != VISITED) { // move up
			tmpCount = Math.min(tmpCount, move(x - 1, y, Move.UP, board, newCount));
		}
		board[x][y] = CLEAN;
		return tmpCount;
	}

	// Check if we are done
	static boolean done(char[][] board) {
		for (char[] s : board)
			for (char c : s)
				if (c == DIRTY)
					return false;
		return true;
	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] pos = new int[2];
		String board[] = new String[5];
		for (int i = 0; i < 2; i++)
			pos[i] = in.nextInt();
		for (int i = 0; i < 5; i++)
			board[i] = in.next();
		next_move(pos[0], pos[1], board);
	}
}
