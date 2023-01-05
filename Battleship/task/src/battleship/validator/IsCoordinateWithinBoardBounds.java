package battleship.validator;

import battleship.Ship;
import battleship.coordinate.CoordinateInputResult;
import battleship.coordinate.CoordinatePair;

public class IsCoordinateWithinBoardBounds implements Validatable{
    private final CoordinatePair pair;
    private final Ship ship;

    public IsCoordinateWithinBoardBounds(CoordinatePair pair, Ship ship) {
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
