package battleship.validator;

import battleship.coordinate.CoordinateInputResult;

public interface Validatable {
    boolean isValid();
    CoordinateInputResult getErrorMessage();
}
