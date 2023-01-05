package battleship.coordinate;

public enum BoardMark {
    OCCUPIED('O'),
    HIT('X'),
    MISSED('M'),
    FOG('~');


    private final char mark;
    private BoardMark(char description) {
        this.mark = description;
    }

    public char getMark() {
        return this.mark;
    }
}
