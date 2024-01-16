package com.madhu.bowlinggame;

public class Frame {
	private int firstRoll;

	private int secondRoll;

	private int temp;

	private int bonus;

	private int points;

	private boolean isStrike;

	private boolean isSpare;

	public Frame() {

		this.firstRoll = BowlingGame.EMPTY;
		this.secondRoll = BowlingGame.EMPTY;
		this.temp = 0;
		this.bonus = 0;
		this.points = 0;
		this.isSpare = false;
		this.isStrike = false;
	}

	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}

	public boolean isSpare() {
		return isSpare;
	}

	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}

	public int getTemp() {
		return temp;
	}

	public void addToTemp(int val) {
		this.temp += val;
	}

	public int getFirstRoll() {
		return firstRoll;
	}

	public void setFirstRoll(int firstRoll) {
		this.firstRoll = firstRoll;
	}

	public int getSecondRoll() {
		return secondRoll;
	}

	public void setSecondRoll(int secondRoll) {
		this.secondRoll = secondRoll;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public void decreaseBonus() {
		this.bonus--;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}