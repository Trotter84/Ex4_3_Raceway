package edu.neumont.csc150.models;

public class Delorean extends Car {
	private final static float CHANCE_TO_TIME_TRAVEL = 0.05f;
	private final int MAX_TIME_JUMP_AMOUNT = 30;

	public Delorean(String name) {
		super(name, "\uD83D\uDE82", BASE_CAR_SPEED, CarColor.WHITE, ProblemChance.NORMAL);
	}

	@Override
	public void moveCar(int raceDistance) {

		checkForProblems();

		if (getCarState() == CarState.NORMAL) {
			SpeedState state = SpeedState.NORMAL;
			int speed = getSpeed();
			float timeTravel = RANDOM.nextFloat();
			boolean future = true;

//			SPECIAL FEATURE
			if (timeTravel <= CHANCE_TO_TIME_TRAVEL) {
				future = RANDOM.nextBoolean();
				speed = RANDOM.nextInt(MAX_TIME_JUMP_AMOUNT) + 1;
				state = SpeedState.TIME_TRAVEL;
			}
			if (future) {
				super.addToTrack(state, speed);
			} else {
				removeFromTrack(speed);
			}
//			END SPECIAL FEATURE

			updateDistanceTravelled((future) ? speed : -speed);

		} else if (getCarState() == CarState.SLOWED) {
			SpeedState state = SpeedState.SLOW;
			setSlowCount(getSlowCount() - 1);
			if (getSlowCount() == 0) {
				state = SpeedState.NORMAL;
				setCarState(CarState.NORMAL);
			}
			int speed = getSpeed();
			super.addToTrack(state, speed / 2);
			updateDistanceTravelled(speed);
		}
	}
}
