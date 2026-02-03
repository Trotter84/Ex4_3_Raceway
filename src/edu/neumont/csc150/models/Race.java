package edu.neumont.csc150.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Race {
	public final int MIN_DISTANCE = 5;
	private List<Car> cars = new ArrayList<>();
	private int raceDistance;
	private Car winner;
	private long startTime, endTime;
	private boolean raceIsOn;

	public Race(int raceDistance) {
		setRaceDistance(raceDistance);
	}

	public int getRaceDistance() {
		return raceDistance;
	}

	private void setRaceDistance(int raceDistance) {
		if (raceDistance < MIN_DISTANCE) {
			throw new IllegalArgumentException("Distance must be greater than " + MIN_DISTANCE);
		}
		this.raceDistance = raceDistance;
	}

	public List<Car> getCars() {
		return cars;
	}

	public Car getWinner() {
		return winner;
	}

	private void setWinner(Car winner) {
		this.winner = winner;
	}

	public void startRace() {
		startTime = System.currentTimeMillis();
		raceIsOn = true;
	}

	public void endRace() {
		endTime = System.currentTimeMillis();
		raceIsOn = false;
	}

	public boolean getRaceIsOn() {
		return raceIsOn;
	}

	private String calculateElapsedTime(long start, long end) {
		long elapsedTime = end - start;
		long minutes = (elapsedTime / 1000) / 60;
		long seconds = (elapsedTime / 1000) % 60;
		long milliseconds = elapsedTime % 1000;

		return String.format("Elapsed time: %d min, %d seconds, %d milliseconds", minutes, seconds, milliseconds);
	}

	public String getLiveRaceDuration() {
		return calculateElapsedTime(startTime, System.currentTimeMillis());
	}

	public String getEndRaceDuration() {
		return calculateElapsedTime(startTime, endTime);
	}

	public void addCarToRace(Car car) {
		if (car != null) {
			cars.add(car);
		}
	}

	public void removeCarFromRace(Car car) {
		if (car != null) {
			cars.remove(car);
		}
	}

	public void moveAllCars() {
		for (Car car : cars) {
			car.moveCar(getRaceDistance());
		}
	}

	public boolean isOver() {
		for (Car car : cars) {
			if (car.getDistanceTravelled() >= getRaceDistance()) {
				setWinner(car);
				return true;
			}
		}

		boolean allHadProblems = true;
		for (Car car : cars) {
			if (car.getCarState() == CarState.NORMAL) {
				allHadProblems = false;
				break;
			}
		}
		return allHadProblems;
	}
}
