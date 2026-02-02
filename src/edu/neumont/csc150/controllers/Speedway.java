package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Race;
import edu.neumont.csc150.models.*;
import edu.neumont.csc150.views.SpeedwayUI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Speedway {
	private Race race1;

	public Speedway() {
		race1 = new Race(60);
		addCars();
	}

	public void race() {
		try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)) {
			scheduler.scheduleAtFixedRate(() -> {
											  race1.moveAllCars();
											  SpeedwayUI.showCars(race1.getCars(), race1.getRaceDistance(), race1.getLiveRaceDuration());
											  if (race1.isOver()) {
												  race1.endRace();
												  scheduler.shutdown();
//												  SpeedwayUI.showRaceOver();
											  }
										  }, 1L, 1L, TimeUnit.SECONDS
			);
			race1.startRace();
			while (race1.getRaceIsOn()) {

			}
		}
	}

	private void addCars() {
		Sedan dad = new Sedan("Daniel");
		race1.addCarToRace(dad);
		Sleeper honda = new Sleeper("Honda");
		race1.addCarToRace(honda);
		Delorean marty = new Delorean("McFly");
		race1.addCarToRace(marty);
	}
}
