package battleship;

import battleship.coordinate.Coordinate;
import battleship.coordinate.CoordinateInputResult;
import battleship.coordinate.CoordinatePair;
import battleship.input.ScannerInput;
import battleship.validator.Validatable;
import battleship.validator.ValidatorFactory;

import java.util.*;

public class Main {
    private static final BattleshipBoard playerOneBoard = new BattleshipBoard();
    private static final BattleshipBoard playerTwoBoard = new BattleshipBoard();
    private static final ScannerInput scannerInput = new ScannerInput();
    private static final String NEXT_PLAYER_PROMPT = "Press Enter and pass the move to another player";

    public static void main(String[] args) {
        // Place player one ships
        display("Player 1, place your ships on the game field");
        display(playerOneBoard.getOpenBoard());
        placeShipsOnBoard(playerOneBoard);

        display(NEXT_PLAYER_PROMPT);
        scannerInput.promptEnter();

        // Place player two ships
        display("Player 2, place your ships on the game field");
        display(playerTwoBoard.getOpenBoard());
        placeShipsOnBoard(playerTwoBoard);

        // Start game
        scannerInput.promptEnter(NEXT_PLAYER_PROMPT);
        while (!playerOneBoard.isAllOccupiedHit() && !playerTwoBoard.isAllOccupiedHit()) {
            if (!playerOneBoard.isAllOccupiedHit())
                askPlayerOneToTakeShot();
            if (!playerTwoBoard.isAllOccupiedHit())
                askPlayerTwoToTakeShot();
        }
    }

    private static void placeShipsOnBoard(BattleshipBoard board) {
        List<Ship> shipsToBePlaced = new ArrayList<>(List.of(
                Ship.AIRCRAFT_CARRIER,
                Ship.BATTLESHIP,
                Ship.SUBMARINE,
                Ship.CRUISER,
                Ship.DESTROYER)
        );
        ValidatorFactory validatorFactory = new ValidatorFactory(board);

        for (Ship ship : shipsToBePlaced) {
            CoordinateInputResult result = CoordinateInputResult.INCORRECT_LENGTH;
            String prompt = "";
            CoordinatePair pair = null;

            while (result != CoordinateInputResult.SUCCESS) {
                pair = scannerInput.askShipCoordinate(ship, prompt);
                List<Validatable> validators = validatorFactory.getValidators(pair, ship);
                result = validators.stream()
                        .map(Validatable::getErrorMessage)
                        .filter(message -> message != CoordinateInputResult.SUCCESS)
                        .findFirst()
                        .orElse(CoordinateInputResult.SUCCESS);
                prompt = result.getDescription();
            }

            Set<Coordinate> range = pair.getPathRange();
            board.addShip(ship, range);
            display(board.getOpenBoard());
        }
    }

    public static void askPlayerOneToTakeShot() {
        displayBoardStatus(playerTwoBoard, playerOneBoard);
        takeShots("Player 1, it's your turn", playerTwoBoard);
    }
    public static void askPlayerTwoToTakeShot() {
        displayBoardStatus(playerOneBoard, playerTwoBoard);
        takeShots("Player 2, it's your turn", playerOneBoard);
    }
    private static void takeShots(String startingPrompt, BattleshipBoard board) {
        Coordinate shot = scannerInput.askCoordinate(startingPrompt);
        boolean isValidShot = false;
        while (!isValidShot) {
            try {
                Optional<Ship> hitShip = board.shootCoordinate(shot);
                display(board.getFogOfWarBoard());

                if (board.isAllOccupiedHit())
                    display("You sank the last ship. You won. Congratulations!");
                else if (hitShip.isEmpty())
                    scannerInput.promptEnter("You missed!" + System.lineSeparator() + NEXT_PLAYER_PROMPT);
                else if (board.isShipSunk(hitShip.get()))
                    scannerInput.promptEnter("You sank a ship!" + System.lineSeparator() +  NEXT_PLAYER_PROMPT);
                else
                    scannerInput.promptEnter("You hit a ship!" + System.lineSeparator() +  NEXT_PLAYER_PROMPT);
                isValidShot = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                shot = scannerInput.askCoordinate("Error! You entered the wrong coordinates! Try again");
            }
        }
    }
    private static void displayBoardStatus(BattleshipBoard hidden, BattleshipBoard open) {
        String status = hidden.getFogOfWarBoard() +
                System.lineSeparator() +
                "---------------------" +
                System.lineSeparator() +
                open.getOpenBoard();
        display(status);
    }

    public static void display(String string) {
        System.out.println(string);
        System.out.println();
    }
}
