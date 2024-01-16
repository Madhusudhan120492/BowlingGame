package com.madhu.bowlinggame;

public class Main {

	public static void main(String args[]) {

		BowlingGame game = new BowlingGame();

		for (int i = 0; i < 12; i++) {
			game.roll(10);
		}

		for (int i = 0; i < game.frames.length; i++) {

			System.out.print("Attempt " + i + " - ");
			System.out.print("[" + game.frames[i].getFirstRoll() + " , ");

			if (game.frames[i].isSpare())
				System.out.print("\\");
			else if (game.frames[i].isStrike())
				System.out.print("X");
			else
				System.out.print(game.frames[i].getSecondRoll());

			System.out.print("] , Temp: " + game.frames[i].getTemp());
			System.out.print(", Points: " + game.frames[i].getPoints());
			System.out.println();
		}
	}
}
