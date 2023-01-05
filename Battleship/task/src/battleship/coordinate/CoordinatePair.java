package battleship.coordinate;

import java.util.HashSet;
import java.util.Set;

public class CoordinatePair {
    private final Coordinate first;
    private final Coordinate second;

    public CoordinatePair(Coordinate first, Coordinate second) {
        this.first = first;
        this.second = second;
    }

    public Coordinate getFirst() {
        return first;
    }

    public Coordinate getSecond() {
        return second;
    }

    public int calculateSize() {
        return (int) Math.sqrt(Math.pow(second.getColumn() - first.getColumn(), 2) + Math.pow(second.getRow() - first.getRow(),2));
    }

    public Set<Coordinate> getPathRange() {
        return getPathRange(this);
    }

    private Set<Coordinate> getPathRange(CoordinatePair pair) {
        Set<Coordinate> range = new HashSet<>();
        try {
            if (pair.calculateSize() != 1) {
                Coordinate midPoint = getMidPoint(pair);
                range.add(midPoint);

                CoordinatePair firstToMiddle = new CoordinatePair(pair.getFirst(), midPoint);
                range.addAll(getPathRange(firstToMiddle));

                CoordinatePair middleToSecond = new CoordinatePair(midPoint, pair.getSecond());
                range.addAll(getPathRange(middleToSecond));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        range.add(first);
        range.add(second);
        return range;
    }

    private Coordinate getMidPoint(CoordinatePair pair) {
        Coordinate first = pair.getFirst();
        Coordinate second = pair.getSecond();
        int row = (first.getRow() + second.getRow()) / 2;
        int column = (first.getColumn() + second.getColumn()) / 2;

        return new Coordinate(row, column);
    }

}
