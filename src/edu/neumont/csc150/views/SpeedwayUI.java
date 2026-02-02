package edu.neumont.csc150.views;

import edu.neumont.csc150.models.*;

import java.util.List;


public class SpeedwayUI {
	public static void showCars(List<Car> cars, int raceDistance, String raceDuration) {
		Console.writeln(raceDuration);
		showTrackWall(raceDistance);
		showCars(cars);
		showTrackWall(raceDistance);
	}

	private static void showTrackWall(int distance) {
		for (int i = 0; i < distance; i++) {
			Console.write(String.valueOf((char)187));
		}
		Console.writeln("");
	}

	private static void showCars(List<Car> cars) {
		for (Car car : cars) {
			showCarHistory(car);
		}
	}

	private static void showCarHistory(Car car) {
		List<SpeedState> carHistory = car.getCarTrack();
		for (int i = 0; i < carHistory.size(); i++) {
			switch (carHistory.get(i)) {
				case ACCELERATED:
					Console.write("A", Console.TextColor.GREEN);
					break;
				case SLOW:
					Console.write("S", Console.TextColor.YELLOW);
					break;
				case DAD_MODE:
					Console.write("D", Console.TextColor.CYAN);
					break;
				case TIME_TRAVEL:
					Console.write("T", Console.TextColor.PURPLE);
					break;
				default: // NORMAL:
					Console.write("-", Console.TextColor.WHITE);
					break;
			}
		}
		Console.TextColor carColor = CarHelpers.getCarTextColor(car);
		switch (car.getCarState()) {
			case FLAT_TIRE -> Console.writeln(String.valueOf((char)892), Console.TextColor.RED);
			default -> Console.writeln(car.getSymbol(), carColor);
		}
	}

	public static void showRaceOver(String raceDuration, Car winner, List<Car> cars) {
		Console.writeRainbow("********** RACE OVER **********");
		Console.writeln("");
		Console.writeln("WINNER AFTER " + raceDuration + " IS ", Console.TextColor.GREEN);
		if (winner != null) {
			Console.writeln(winner.toString(), CarHelpers.getCarTextColor(winner));
		} else {
			Console.writeln("No one!", Console.TextColor.PURPLE);
		}

		Console.writeln("");
		Console.writeln("The rest of the cars: ");
		boolean foundNormalCars = false;
		for (Car car : cars) {
			if (car != winner && car.getCarState() == CarState.NORMAL) {
				Console.writeln(car.toString(), CarHelpers.getCarTextColor(car));
				foundNormalCars = true;
			}
		}
		if (!foundNormalCars) {
			Console.writeln("No cars were still in the race.", Console.TextColor.YELLOW);
		}
		Console.writeln("");
		Console.writeln("The following cars had problems: ", Console.TextColor.YELLOW);
		boolean foundCarProblems = false;
		for (Car car : cars) {
			if (car != winner && car.getCarState() != CarState.NORMAL) {
				Console.writeln(car.toString(), Console.TextColor.RED);
				foundCarProblems = true;
			}
		}
		if (!foundCarProblems) {
			Console.writeln("No cars had issues! YAY :)", Console.TextColor.CYAN);
		}
		Console.writeRainbow("********** RACE OVER **********");
	}
}
