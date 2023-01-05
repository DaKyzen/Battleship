package battleship.validator;

import battleship.BattleshipBoard;
import battleship.Ship;
import battleship.coordinate.CoordinatePair;

import java.util.ArrayList;
import java.util.List;

public class ValidatorFactory {
    private final BattleshipBoard board;

    public ValidatorFactory(BattleshipBoard board) {
        this.board = board;
    }
    public List<Validatable> getValidators(CoordinatePair pair, Ship ship) {
        List<Validatable> validators = new ArrayList<>();
        validators.add(new IsLengthCorrect(pair, ship));
        validators.add(new IsPathFree(board, pair));
        validators.add(new IsSpaceSurroundingPathFree(board, pair));
        validators.add(new IsValidShipDirection(pair));
        return validators;
    }
}
