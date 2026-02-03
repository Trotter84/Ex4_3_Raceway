package edu.neumont.csc150.models;

public class MarioBro extends AttackerCar {
	private float CHANCE_FOR_BANANA = 0.075f;

	public MarioBro(String name, Car topCar, Car bottomCar) {
		super(name, "\uD83C\uDF44", BASE_CAR_SPEED, CarColor.CYAN, ProblemChance.LOW, topCar, bottomCar);
		setTopCar(topCar);
		setBottomCar(bottomCar);
	}

	@Override
	public void moveCar(int raceDistance) {
		checkForProblems();
		if (getCarState() == CarState.NORMAL) {
			SpeedState speedState = SpeedState.NORMAL;
			int speed = getSpeed();

			float bananaAttack = RANDOM.nextFloat();
			if (bananaAttack <= CHANCE_FOR_BANANA) {
				Car attackThis = null;
				if (getBottomCar() != null && getTopCar() != null) {
					boolean attackTop = RANDOM.nextBoolean();
					attackThis = attackTop ? getTopCar() : getBottomCar();
				} else if (getBottomCar() == null) {
					attackThis = getTopCar();
				} else { // top car null
					attackThis = getBottomCar();
				}
				// apply slow
				if (attackThis.getDistanceTravelled() <= raceDistance) {
					attackThis.slow();
				}
			}
			addToTrack(speedState, speed);
			updateDistanceTravelled(speed);

		}
	}
}
