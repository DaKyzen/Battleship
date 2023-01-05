package battleship.input;

import battleship.Main;
import battleship.Ship;
import battleship.coordinate.Coordinate;
import battleship.coordinate.CoordinatePair;

import java.util.Scanner;

public class ScannerInput {
    private final Scanner scanner = new Scanner(System.in);

    public CoordinatePair askShipCoordinate(Ship ship, String prompt) {
        String[] coordinates = promptShipCoordinates(ship, prompt);

        return new CoordinatePair(
            parseLetterNumberCoordinate(coordinates[0]),
            parseLetterNumberCoordinate(coordinates[1])
        );
    }

    public Coordinate askCoordinate(String prompt) {
        String input = promptInput(prompt);
        return parseLetterNumberCoordinate(input);
    }

    private String[] promptShipCoordinates(Ship ship, String prompt) {
        String defaultPrompt = String.format("Enter the coordinates of the %s (%d cells)", ship.getName(), ship.getSize());
        String messageLine = prompt.isBlank() ? defaultPrompt : prompt;
        String input = promptInput(messageLine);
        return input.split(" ");
    }

    private String promptInput(String prompt) {
        if (!prompt.isBlank())
            Main.display(prompt + ":");
        String input = scanner.nextLine().toUpperCase();
        System.out.println();
        return input;
    }

    private Coordinate parseLetterNumberCoordinate(String coordinate) {
        String[] split = coordinate.split("(?<=\\D)(?=\\d)");
        int row = split[0].charAt(0) - (int) 'A' + 1;
        int column = Integer.parseInt(split[1]);
        return new Coordinate(row, column);
    }

    public void promptEnter() {
        scanner.nextLine();
    }
    public void promptEnter(String prompt) {
        System.out.println(prompt);
        scanner.nextLine();
    }
}
