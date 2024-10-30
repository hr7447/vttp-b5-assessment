package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vttp.batch5.sdf.task01.models.BikeEntry;

public class Cyclist {

    private List<BikeEntry> entries;

    public Cyclist() {
        this.entries = new ArrayList<>();
    }

    public void loadFile(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] col = line.split(",");
                BikeEntry entry = BikeEntry.toBikeEntry(col);
                entries.add(entry);
            }
        }
    }

    private List<BikeEntry> getTopFiveDays() {
        List<BikeEntry> sortedEntries = new ArrayList<>(entries);

        Collections.sort(sortedEntries, (a, b) -> {
            int totalA = a.getCasual() + a.getRegistered();
            int totalB = b.getCasual() + b.getRegistered();
            return Integer.compare(totalB, totalA);
        });

        return sortedEntries.subList(0, Math.min(5, sortedEntries.size()));
    }

    public void printTopFiveDays() {
        List<BikeEntry> topFive = getTopFiveDays();
        String[] positions = { "highest", "second highest", "third highest", "fourth highest", "fifth highest" };

        for (int i = 0; i < topFive.size(); i++) {
            BikeEntry entry = topFive.get(i);
            String formattedOutput = formatOutput(entry, positions[i]);
            System.out.println(formattedOutput);
            System.out.println();
        }
    }

    private String formatOutput(BikeEntry entry, String position) {
        int total = entry.getCasual() + entry.getRegistered();
        String season = Utilities.toSeason(entry.getSeason());
        String day = Utilities.toWeekday(entry.getWeekday());
        String month = Utilities.toMonth(entry.getMonth());
        String holiday = entry.isHoliday() ? "a holiday" : "not a holiday";

        return String.format(
                "The %s recorded number of cyclists was in %s, " + "on a %s in the month of %s.\n"
                        + "There were a total of %d cyclist. The weather was %s.\n" + "%s was %s.",
                position, season, day, month,
                total, weatherDescription(entry.getWeather()), day, holiday);
    }

    private String weatherDescription(int weather) {
        switch (weather) {
            case 1:
                return "Clear, Few clouds, Partly cloudy, Partly cloudy";
            case 2:
                return "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";
            case 3:
                return "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds";
            case 4:
                return "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog";
            default:
                return "Unknown weather";
        }
    }

}
