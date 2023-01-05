package battleship.validator;

import battleship.coordinate.CoordinateInputResult;
import battleship.coordinate.CoordinatePair;

public class IsValidShipDirection implements Validatable {
    private CoordinatePair pair;

    public IsValidShipDirection(CoordinatePair pair) {
        this.pair = pair;
    }

    @Override
    public boolean isValid() {
        return isDiagonal() || isHorizontal() || isVertical();
    }

    @Override
    public CoordinateInputResult getErrorMessage() {
        return isValid() ? CoordinateInputResult.SUCCESS : CoordinateInputResult.DIAGONAL_DIRECTION;
    }

    private boolean isHorizontal() {
        return this.pair.getFirst().getRow() == this.pair.getSecond().getRow();
    }

    private boolean isVertical() {
        return this.pair.getFirst().getColumn() == this.pair.getSecond().getColumn();
    }

    private boolean isDiagonal() {
        return Math.abs(this.pair.getFirst().getRow() - this.pair.getSecond().getRow()) ==
                Math.abs(this.pair.getFirst().getColumn() - this.pair.getSecond().getColumn());
    }
}
