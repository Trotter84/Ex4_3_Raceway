package edu.neumont.csc150.models;

public class Delorean extends Car {
	private final static float CHANCE_TO_TIME_TRAVEL = 0.05f;
	private final int MAX_TIME_JUMP_AMOUNT = 30;

	public Delorean(String name) {
		super(name, "\uD83D\uDE82", 1, CarColor.WHITE, ProblemChance.NORMAL);
	}

	@Override
	public void moveCar(int raceDistance) {

		checkForProblems();

		if (getCarState() == CarState.NORMAL) {
			SpeedState speedState = SpeedState.NORMAL;
			int speed = getSpeed();
			float timeTravel = RANDOM.nextFloat();
			boolean future = true;

//			SPECIAL FEATURE
			if (timeTravel <= CHANCE_TO_TIME_TRAVEL) {
				future = RANDOM.nextBoolean();
				speed = RANDOM.nextInt(MAX_TIME_JUMP_AMOUNT) + 1;
				speedState = SpeedState.TIME_TRAVEL;
			}
			if (future) {
				addToTrack(speedState, speed);
			} else {
				removeFromTrack(speed);
			}
//			END SPECIAL FEATURE

			updateDistanceTravelled((future) ? speed : -speed);
		}
	}
}
