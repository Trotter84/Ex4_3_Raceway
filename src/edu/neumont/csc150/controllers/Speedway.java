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
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		scheduler.scheduleAtFixedRate(() -> {
										  SpeedwayUI.clear();
										  race1.moveAllCars();
										  SpeedwayUI.showCars(race1.getCars(), race1.getRaceDistance(), race1.getLiveRaceDuration());
										  if (race1.isOver()) {
											  race1.endRace();
											  scheduler.shutdown();
										  }
									  }, 1L, 1L, TimeUnit.SECONDS
		);

		race1.startRace();

		try {
			scheduler.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		SpeedwayUI.showRaceOver(race1.getEndRaceDuration(), race1.getWinner(), race1.getCars());

	}

	private void addCars() {
		Sedan dad1 = new Sedan("Daniel");
		Sedan dad2 = new Sedan("Dad");
		Sleeper honda = new Sleeper("Honda");
		F1 f1 = new F1("Mclaren");
		F1 f2 = new F1("Neralcm");
		Delorean marty = new Delorean("McFly");
		MarioBro mario = new MarioBro("MarioBro", dad1, f1);
		MarioBro luigi = new MarioBro("Luigi", honda, marty);

		race1.addCarToRace(dad1);
		race1.addCarToRace(mario);
		race1.addCarToRace(f1);
		race1.addCarToRace(honda);
		race1.addCarToRace(luigi);
		race1.addCarToRace(marty);
		race1.addCarToRace(f2);
		race1.addCarToRace(dad2);
	}
}
