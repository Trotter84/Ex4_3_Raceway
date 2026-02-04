package edu.neumont.csc150.models;

import java.awt.*;


public abstract class AttackerCar extends Car {
	private Car topCar;
	private Car bottomCar;

	public AttackerCar(String name, String symbol, int speed, CarColor carColor, ProblemChance problemChance, Car topCar,
					   Car bottomCar
	) {
		super(name, symbol, speed, carColor, problemChance);

	}

	protected Car getTopCar() {
		return topCar;
	}

	public void setTopCar(Car topCar) {
		this.topCar = topCar;
	}

	protected Car getBottomCar() {
		return bottomCar;
	}

	public void setBottomCar(Car bottomCar) {
		this.bottomCar = bottomCar;
	}

	public abstract void moveCar(int raceDistance);
}
