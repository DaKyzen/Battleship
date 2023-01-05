package battleship.validator;

import battleship.BattleshipBoard;
import battleship.coordinate.Coordinate;
import battleship.coordinate.CoordinateInputResult;
import battleship.coordinate.CoordinatePair;

import java.util.Set;

public class IsPathFree implements Validatable{
    private BattleshipBoard board;
    private final CoordinatePair pair;

    public IsPathFree(BattleshipBoard board, CoordinatePair pair) {
        this.board = board;
        this.pair = pair;
    }

    @Override
    public boolean isValid() {
        Set<Coordinate> range = pair.getPathRange();
        return range.stream().allMatch(board::isSpaceFree);
    }

    @Override
    public CoordinateInputResult getErrorMessage() {
        return isValid() ? CoordinateInputResult.SUCCESS : CoordinateInputResult.CONFLICTING_LOCATION;
    }
}
