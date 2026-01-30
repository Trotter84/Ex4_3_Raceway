package edu.neumont.csc150.models;

import java.util.ArrayList;
import java.util.Random;


public abstract class Car {
	protected static final Random RANDOM = new Random();
	private String name;
	private String symbol;
	private CarColor color;
	private int speed;
	private int distanceTravelled;
	private CarState carState;
	private ProblemChance problemChance;
	private ArrayList<SpeedState> carTrack = new ArrayList<>();

	public Car(String name, String symbol, int speed, CarColor color, ProblemChance problemChance) {
		setName(name);
		setSymbol(symbol);
		setSpeed(speed);
		setColor(color);
		setProblemChance(problemChance);
		setCarState(CarState.NORMAL);
		setDistanceTravelled(0);
	}

	//region GETTERS//SETTERS

	public String getName() {
		return name;
	}

	private void setName(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Car Name cannot be NULL or EMPTY.");
		}
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	private void setSymbol(String symbol) {
		if (symbol == null || symbol.isBlank()) {
			symbol = String.valueOf((char)338);
		}
		this.symbol = symbol;
	}

	public CarColor getColor() {
		return color;
	}

	private void setColor(CarColor color) {
		if (color == null) {
			throw new IllegalArgumentException("Car color cannot be NULL.");
		}
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	private void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDistanceTravelled() {
		return distanceTravelled;
	}

	private void setDistanceTravelled(int distanceTravelled) {
		if (distanceTravelled < 0) {
			distanceTravelled = 0;
		}
		this.distanceTravelled = distanceTravelled;
	}

	public CarState getCarState() {
		return carState;
	}

	private void setCarState(CarState carState) {
		if (carState == null) {
			carState = CarState.NORMAL;
		}
		this.carState = carState;
	}

	public ProblemChance getProblemChance() {
		return problemChance;
	}

	protected void setProblemChance(ProblemChance problemChance) {
		if (problemChance == null) {
			problemChance = ProblemChance.NORMAL;
		}
		this.problemChance = problemChance;
	}

	public ArrayList<SpeedState> getCarTrack() {
		return new ArrayList<>(carTrack);
	}

	//endregion

	protected void updateDistanceTravelled(int moveAmount) {
		setDistanceTravelled(getDistanceTravelled() + moveAmount);
	}

	protected void addToTrack(SpeedState speedState, int amount) {
		for (int i = 0; i < amount; i++) {
			carTrack.add(speedState);
		}
	}

	protected void removeFromTrack(int amount) {
		if (amount > carTrack.size()) {
			carTrack.clear();
			return;
		}

		for (int i = 0; i < amount; i++) {
			carTrack.removeLast();
		}
	}

	protected void checkForProblems() {
//		TODO: if adding new problems, check them here!
		checkForFlat();
	}

	private void checkForFlat() {
		float chanceForFlat = switch (getProblemChance()) {
			case HIGH -> 0.007f;
			case MEDIUM -> 0.005f;
			case LOW -> 0.002f;
			default -> 0.0035f;
		};

		float flatRandom = RANDOM.nextFloat();
		if (flatRandom <= chanceForFlat) {
			setCarState(CarState.FLAT_TIRE);
		}
	}

	public abstract void moveCar(int raceDistance);

	@Override
	public String toString() {
		return getSymbol() + " " + getName() + "\nDistance Travelled: " + getDistanceTravelled() + "\nFinal car state: " +
				getCarState();
	}
}
