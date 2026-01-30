package edu.neumont.csc150.models;

public class Sleeper extends Car {
	private final float CHANCE_TO_GO_FASTER;

	public Sleeper(String name) {
		this(name, 0.5f);
	}

	public Sleeper(String name, float chanceToGoFaster) {
		super(name, "\uD83D\uDECC", 1, CarColor.PURPLE, ProblemChance.HIGH);
		this.CHANCE_TO_GO_FASTER = chanceToGoFaster;
	}

	@Override
	public void moveCar(int raceDistance) {
		checkForProblems();
		if (getCarState() == CarState.NORMAL) {
			SpeedState speedState = SpeedState.NORMAL;
			int speed = getSpeed();

//			SPECIAL FEATURE
			if (getDistanceTravelled() >= (raceDistance / 2)) {
				float moveFaster = RANDOM.nextFloat();
				if (moveFaster <= CHANCE_TO_GO_FASTER) {
					speed += 1;
					speedState = SpeedState.ACCELERATED;
				}
			}
//			END SPECIAL FEATURE

			addToTrack(speedState, speed);
			updateDistanceTravelled(speed);
		}
	}
}
