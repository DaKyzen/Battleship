package battleship.coordinate;

public enum CoordinateInputResult {
    INCORRECT_LENGTH("Error! Wrong length of the Submarine! Try again"),
    DIAGONAL_DIRECTION("Error! Wrong ship location! Try again"),
    CONFLICTING_LOCATION("Error! You placed it too close to another one. Try again"),
    INVALID_COORDINATE("Error! You entered the wrong coordinates! Try again"),
    SUCCESS("Success");

    private final String description;
    private CoordinateInputResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
