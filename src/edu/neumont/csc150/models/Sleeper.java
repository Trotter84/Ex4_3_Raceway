package edu.neumont.csc150.models;

public class Sleeper extends Car {
	private final float CHANCE_TO_GO_FASTER;

	public Sleeper(String name) {
		this(name, 0.5f);
	}

	public Sleeper(String name, float chanceToGoFaster) {
		super(name, "\uD83D\uDECC", BASE_CAR_SPEED, CarColor.PURPLE, ProblemChance.HIGH);
		this.CHANCE_TO_GO_FASTER = chanceToGoFaster;
	}

	@Override
	public void moveCar(int raceDistance) {
		checkForProblems();
		if (getCarState() == CarState.NORMAL) {
			SpeedState state = SpeedState.NORMAL;
			int speed = getSpeed();

//			SPECIAL FEATURE
			if (getDistanceTravelled() >= (raceDistance / 2)) {
				float moveFaster = RANDOM.nextFloat();
				if (moveFaster <= CHANCE_TO_GO_FASTER) {
					speed += 1;
					state = SpeedState.ACCELERATED;
				}
			}
//			END SPECIAL FEATURE

			super.addToTrack(state, speed);
			updateDistanceTravelled(speed);

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
