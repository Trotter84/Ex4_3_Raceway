package edu.neumont.csc150.models;

public class F1 extends Car {
	private static final int MAX_HEAT_LEVEL = 9;
	private static final int UPPER_BOUND = 6;
	private float chanceToFloorIt = 0.15f;
	private int overHeatLevel = 0;

	public F1(String name) {
		super(name, "\uD83C\uDFCE\uFE0F", BASE_CAR_SPEED, CarColor.YELLOW, ProblemChance.HIGH);
	}

//TODO: Write documentation

	/**
	 * F1 can put the pedal to the metal!
	 * {@code chanceToFloorIt} for speed boost, which is dependent on a random int between 0 and {@code UPPER_BOUND} exclusive.
	 * However, be careful, every boost fills the {@code overHeatLevel}. If it reaches the {@code MAX_HEAT_LEVEL}...engine goes
	 * kaboom.
	 *
	 * @param raceDistance
	 */
	@Override
	public void moveCar(int raceDistance) {
		checkForProblems();

		if (getCarState() == CarState.NORMAL) {
			SpeedState state = SpeedState.NORMAL;
			int speed = getSpeed();
//	SPECIAL FEATURE
			if (overHeatLevel < MAX_HEAT_LEVEL) {
				float floorIt = RANDOM.nextFloat();
				if (floorIt <= chanceToFloorIt) {
					int floorItDuration = RANDOM.nextInt(UPPER_BOUND);
//					System.out.println("Floor It Duration: " + floorItDuration);
					overHeatLevel += floorItDuration;
//					System.out.println("Heat Level: " + overHeatLevel);
					speed += floorItDuration;
					state = SpeedState.FLOOR_IT;
					setProblemChance(ProblemChance.HIGH);
					chanceToFloorIt -= (float)floorItDuration / 100;
//					System.out.println("Chance To Floor It: " + chanceToFloorIt);
				}
			} else {
				setCarState(CarState.ENGINE_BLOWN);
			}
//	END SPECIAL FEATURE

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
