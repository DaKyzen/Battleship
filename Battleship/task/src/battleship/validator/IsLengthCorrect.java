package battleship.validator;

import battleship.coordinate.CoordinateInputResult;
import battleship.coordinate.CoordinatePair;
import battleship.Ship;

public class IsLengthCorrect implements Validatable{
    private final CoordinatePair pair;
    private final Ship ship;

    public IsLengthCorrect(CoordinatePair pair, Ship ship) {
        this.ship = ship;
        this.pair =pair;

    }

    @Override
    public boolean isValid() {
        int length = pair.calculateSize() + 1;
        return this.ship.getSize() == length;
    }

    @Override
    public CoordinateInputResult getErrorMessage() {
        return isValid() ? CoordinateInputResult.SUCCESS : CoordinateInputResult.INCORRECT_LENGTH;
    }
}
