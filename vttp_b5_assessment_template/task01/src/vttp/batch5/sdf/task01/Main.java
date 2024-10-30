package vttp.batch5.sdf.task01;

import java.io.IOException;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) {

		Cyclist cyclist = new Cyclist();
		try {
			cyclist.loadFile("day.csv");
			cyclist.printTopFiveDays();
		} catch (IOException ex) {
			System.err.println("Error reading the CSV file: " + ex.getMessage());
		}
	}

}
