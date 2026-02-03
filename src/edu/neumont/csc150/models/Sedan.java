package edu.neumont.csc150.models;

public class Sedan extends Car {
	private final static ProblemChance DEFAULT_PROBLEM_CHANCE = ProblemChance.MEDIUM;
	private final float CHANCE_TO_DAD_MODE = 0.5f;
	private int dadModeCounter = 3;

	public Sedan(String name) {
		super(name, "\uD83D\uDE97", BASE_CAR_SPEED, CarColor.CYAN, ProblemChance.MEDIUM);
		setProblemChance(DEFAULT_PROBLEM_CHANCE);
	}

	@Override
	public void moveCar(int raceDistance) {

		checkForProblems();
		setProblemChance(DEFAULT_PROBLEM_CHANCE);

		if (getCarState() == CarState.NORMAL) {
			SpeedState state = SpeedState.NORMAL;
			int speed = getSpeed();

//	SPECIAL FUNCTION
			if (dadModeCounter > 0) {
				float dadMode = RANDOM.nextFloat();
				if (dadMode <= CHANCE_TO_DAD_MODE) {
					dadModeCounter--;
					speed++;
					state = SpeedState.DAD_MODE;
					setProblemChance(ProblemChance.HIGH);
				}
			}
//	END SPECIAL FUNCTION
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
