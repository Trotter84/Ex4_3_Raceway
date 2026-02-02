package edu.neumont.csc150.views;

import edu.neumont.csc150.models.Car;


public class CarHelpers {
	public static Console.TextColor getCarTextColor(Car car) {
		return switch (car.getColor()) {
			case BLACK -> Console.TextColor.BLACK;
			case GREEN -> Console.TextColor.GREEN;
			case YELLOW -> Console.TextColor.YELLOW;
			case BLUE -> Console.TextColor.BLUE;
			case PURPLE -> Console.TextColor.PURPLE;
			case CYAN -> Console.TextColor.CYAN;
			default -> Console.TextColor.WHITE;
		};
	}
}
