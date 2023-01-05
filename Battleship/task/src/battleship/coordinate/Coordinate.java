package battleship.coordinate;

import java.util.*;

public class Coordinate {
    private final int row;
    private final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Coordinate coordinate = ((Coordinate) obj);
        return (coordinate.getColumn() == this.getColumn() && coordinate.getRow() == this.getRow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getRow(), this.getColumn());
    }

    public Set<Coordinate> getSurroundingRange() {
        Set<Coordinate> range = new HashSet<>();
        for (int row = this.row - 1; row <= this.row + 1; row++) {
            for (int column = this.column - 1; column <= this.column + 1; column++) {
                range.add(new Coordinate(row, column));
            }
        }
        return range;
    }





}
