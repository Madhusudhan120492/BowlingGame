package com.madhu.bowlinggame;

public class BowlingGame {

	public static final int EMPTY = -1;

	public static final int LAST_FRAME = 10;

	public static final int BONUS_FRAME_1 = 11;

	public static final int BONUS_FRAME_2 = 12;

	public static final int MAX_FRAMES = 12;
	public static final int MAX_PINS_ROLLED = 10;

	public static final int STRIKE_BONUS = 2;

	public static final int SPARE_BONUS = 1;

	public Frame[] frames;

	private int attempt;

	public BowlingGame() {

		this.attempt = 0;
		this.frames = new Frame[MAX_FRAMES];

		for (int i = 0; i < frames.length; i++) {

			this.frames[i] = new Frame();
		}
	}

	public void roll(int pins) {

		if (!isFinished()) {

			if (pins <= MAX_PINS_ROLLED) {

				if (isValidRoll(pins, attempt)) {

					if (attempt <= LAST_FRAME - 1) {

						frames[attempt].addToTemp(pins);
					}

					if (attempt - 2 >= 0 && frames[attempt - 2].getBonus() > 0) {

						frames[attempt - 2].addToTemp(pins);

						frames[attempt - 2].decreaseBonus();

						if (frames[attempt - 2].getBonus() == 0) {

							if (attempt - 3 >= 0) {

								frames[attempt - 2]
										.setPoints(frames[attempt - 3].getPoints() + frames[attempt - 2].getTemp());
							} else {
								frames[attempt - 2].setPoints(frames[attempt - 2].getTemp());
							}
						}
					}

					if (attempt - 1 >= 0 && frames[attempt - 1].getBonus() > 0) {

						frames[attempt - 1].addToTemp(pins);

						frames[attempt - 1].decreaseBonus();

						if (frames[attempt - 1].getBonus() == 0) {

							if (attempt - 2 >= 0) {

								frames[attempt - 1]
										.setPoints(frames[attempt - 2].getPoints() + frames[attempt - 1].getTemp());
							} else {
								frames[attempt - 1].setPoints(frames[attempt - 1].getTemp());
							}
						}
					}

					if (frames[attempt].getFirstRoll() == EMPTY) {

						frames[attempt].setFirstRoll(pins);

						if (isStrike(attempt)) {

							frames[attempt].setBonus(STRIKE_BONUS);
							frames[attempt].setStrike(true);

							attempt++;
							return;
						}

						if (attempt == BONUS_FRAME_1 - 1 && isSpare(LAST_FRAME - 1)) {

							attempt++;
							return;
						}

						if (attempt == BONUS_FRAME_2 - 1 && isStrike(LAST_FRAME - 1) && isStrike(BONUS_FRAME_1 - 1)) {
							attempt++;
						}
					} else {

						frames[attempt].setSecondRoll(pins);

						if (isSpare(attempt)) {

							frames[attempt].setBonus(SPARE_BONUS);
							frames[attempt].setSpare(true);
						} else {
							if (attempt - 1 >= 0) {
								frames[attempt].setPoints(frames[attempt - 1].getPoints() + frames[attempt].getTemp());
							} else {
								frames[attempt].setPoints(frames[attempt].getTemp());
							}
						}
						attempt++;
						return;
					}
				} else {
					System.out.println("INVALID ROLL: The number of pins rolled is invalid for this attempt");
				}
			} else {
				System.out.println("INVALID ROLL: Cannot knock more than 10 pins");
			}
		} else {
			System.out.println("GAME OVER!!");
		}
	}

	public boolean isValidRoll(int pins, int attempt) {

		if (frames[attempt].getFirstRoll() == EMPTY) {

			if (pins <= MAX_PINS_ROLLED)
				return true;
			else
				return false;

		} else {

			if (frames[attempt].getFirstRoll() + pins <= MAX_PINS_ROLLED) {
				return true;
			} else {
				return false;
			}
		}
	}

	public int getScore() {

		int ptr = EMPTY;

		for (int i = frames.length - 1; i >= 0; i--) {

			if (frames[i].getPoints() > 0) {
				ptr = i;
				break;
			}
		}

		if (ptr == EMPTY)
			return 0;
		else
			return frames[ptr].getPoints();
	}

	public boolean isFinished() {

		if (attempt <= LAST_FRAME - 1) {

			return false;
		} else if (attempt == BONUS_FRAME_1 - 1) {

			if (isStrike(LAST_FRAME - 1) || isSpare(LAST_FRAME - 1)) {
				return false;
			} else {
				return true;
			}

		} else if (attempt == BONUS_FRAME_2 - 1) {

			if (isStrike(LAST_FRAME - 1) && frames[LAST_FRAME - 1].getBonus() > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public boolean isStrike(int attempt) {

		return frames[attempt].getFirstRoll() == 10;
	}

	public boolean isSpare(int attempt) {

		return (frames[attempt].getFirstRoll() + frames[attempt].getSecondRoll()) == 10;
	}
}