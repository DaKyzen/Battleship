package battleship.validator;

import battleship.BattleshipBoard;
import battleship.coordinate.Coordinate;
import battleship.coordinate.CoordinateInputResult;
import battleship.coordinate.CoordinatePair;

import java.util.Set;

public class IsSpaceSurroundingPathFree implements Validatable{
    private BattleshipBoard board;
    private final CoordinatePair pair;

    public IsSpaceSurroundingPathFree(BattleshipBoard board, CoordinatePair pair) {
        this.board = board;
        this.pair = pair;
    }

    @Override
    public boolean isValid() {
        Set<Coordinate> range = pair.getPathRange();
        return range.stream().allMatch(board::isSurroundingSpaceFree);
    }

    @Override
    public CoordinateInputResult getErrorMessage() {
        return isValid() ? CoordinateInputResult.SUCCESS : CoordinateInputResult.CONFLICTING_LOCATION;
    }
}
