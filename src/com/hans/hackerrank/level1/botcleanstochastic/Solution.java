package com.hans.hackerrank.level1.botcleanstochastic;

import java.util.Scanner;

/**
 * Solution to <a
 * href="https://www.hackerrank.com/challenges/botcleanr">https://
 * www.hackerrank.com/challenges/botcleanr</a>.
 * <p>
 * Simply identifies the single dirty tile on the board and then moves towards
 * it. Since the board is open, there's no pathfinding required in any way and
 * the most direct way is as straightforward as moving along the x axis until
 * linged up and then moving along the y axis until lined up with the dirty
 * tile.
 * </p>
 * 
 * @author Hermann Hans
 * 
 */
public class Solution {

	enum Move {
		RIGHT, LEFT, UP, DOWN, CLEAN
	}

	static final char DIRTY = 'd';

	/* Head ends here */
	static void nextMove(int posx, int posy, String[] board) {

		if (board[posx].charAt(posy) == DIRTY) {
			System.out.println(Move.CLEAN);
			return;
		}

		int d_x = 0;
		int d_y = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i].charAt(j) == DIRTY) {
					d_x = i;
					d_y = j;
				}
			}
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
		for (int i = 0; i < 2; i++) {
			pos[i] = in.nextInt();
		}
		for (int i = 0; i < 5; i++) {
			board[i] = in.next();
		}
		nextMove(pos[0], pos[1], board);
	}
}
