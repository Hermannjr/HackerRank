package com.hans.hackerrank.level1.botcleanstochastic;
import java.util.Scanner;

public class Solution {

	enum Move {
		RIGHT, LEFT, UP, DOWN, CLEAN
	}

	static int moves = Integer.MAX_VALUE;
	static final char DIRTY = 'd';
	static final char CLEAN = '-';
	static final char BOT = 'b';

	/* Head ends here */
	static void nextMove(int posx, int posy, String[] board) {
		int d_x = 0;
		int d_y = 0;
		char[][] cboard = new char[5][5];

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				cboard[i][j] = board[i].charAt(j);
				if (cboard[i][j] == DIRTY) {
					d_x = i;
					d_y = j;
				}
			}
		if (cboard[posx][posy] == DIRTY) {
			System.out.println(Move.CLEAN);
			return;
		}

		if (posx < d_x)
			System.out.println(Move.DOWN);
		else if (posx > d_x)
			System.out.println(Move.UP);
		else if (posy < d_y)
			System.out.println(Move.RIGHT);
		else if (posy > d_y)
			System.out.println(Move.LEFT);
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
		nextMove(pos[0], pos[1], board);
	}
}
