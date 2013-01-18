package com.hans.hackerrank.level1.botcleanpartial;

import java.util.Scanner;

public class Solution {

	enum Move {
		RIGHT, LEFT, UP, DOWN, CLEAN
	}

	static int moves = Integer.MAX_VALUE;
	static final char DIRTY = 'd';
	static final char CLEAN = '-';
	static final char HIDDEN = 'o';
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
