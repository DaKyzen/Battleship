package battleship;

import battleship.coordinate.BoardMark;
import battleship.coordinate.Coordinate;

import java.util.*;

public class BattleshipBoard {
    private final char[][] openBoard;
    private final Map<Ship, Set<Coordinate>> shipCoordinates;
    private int numOccupied = 0;

    public BattleshipBoard() {
        this.openBoard = new char[10][10];
        this.shipCoordinates = new HashMap<>();
        for (char[] chars : this.openBoard) {
            Arrays.fill(chars, BoardMark.FOG.getMark());
        }
    }

    public void addShip(Ship ship, Set<Coordinate> range) {
        this.setCoordinates(range, BoardMark.OCCUPIED);
        this.numOccupied += range.size();
        this.shipCoordinates.put(ship, range);
    }
    public Optional<Ship> shootCoordinate(Coordinate coordinate) throws ArrayIndexOutOfBoundsException {
        if (checkCoordinateHasMark(coordinate, BoardMark.OCCUPIED))
            numOccupied--;
        if (!isSpaceFree(coordinate))
            setCoordinate(coordinate, BoardMark.HIT);
        else
            setCoordinate(coordinate, BoardMark.MISSED);

        return getShipFromCoordinate(coordinate);
    }
    private void setCoordinates(Set<Coordinate> range, BoardMark mark) throws ArrayIndexOutOfBoundsException {
        for (Coordinate coordinate : range) {
            setCoordinate(coordinate, mark);
        }
    }
    private void setCoordinate(Coordinate coordinate, BoardMark mark) throws ArrayIndexOutOfBoundsException {
        this.openBoard[coordinate.getRow() -1][coordinate.getColumn() - 1] = mark.getMark();
    }
    public Optional<Ship> getShipFromCoordinate(Coordinate coordinate) {
        Set<Ship> ships = shipCoordinates.keySet();
        Optional<Ship> foundShip = Optional.empty();
        for (Ship ship : ships) {
            if (shipCoordinates.get(ship).contains(coordinate))
                foundShip = Optional.of(ship);
        }
        return foundShip;
    }

    public boolean isAllOccupiedHit() {
        return this.numOccupied == 0;
    }

    public boolean isShipSunk(Ship ship) {
        Set<Coordinate> coordinates = shipCoordinates.get(ship);
        return coordinates.stream().allMatch(coordinate -> checkCoordinateHasMark(coordinate, BoardMark.HIT));
    }

    public boolean isSurroundingSpaceFree(Coordinate coordinate) {
        Set<Coordinate> surroundingRange = coordinate.getSurroundingRange();
        surroundingRange.removeIf(this::isOutsideBoardBounds);
        return surroundingRange.stream().allMatch(this::isSpaceFree);
    }
    public boolean isSpaceFree(Coordinate coordinate) {
        if (isOutsideBoardBounds(coordinate))
            return false;
        return checkCoordinateHasMark(coordinate, BoardMark.FOG) || checkCoordinateHasMark(coordinate, BoardMark.MISSED);
    }
    private boolean checkCoordinateHasMark(Coordinate coordinate, BoardMark mark) {
        return this.openBoard[coordinate.getRow() - 1][coordinate.getColumn() - 1] == mark.getMark();
    }
    private boolean isOutsideBoardBounds(Coordinate c) {
        return c.getRow() < 1 || c.getColumn() < 1
                || c.getRow() > this.openBoard.length || c.getColumn() > this.openBoard.length;
    }

    public String getOpenBoard() {
        return getColumnHeaders() + getOpenBoardWithRowLetter();
    }
    public String getFogOfWarBoard() {
        var fogOfWarBoard = getOpenBoardWithRowLetter().replace('O', '~');
        return getColumnHeaders() + fogOfWarBoard;
    }
    private String getColumnHeaders() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("  ");

        for (int i = 1; i < 11; i++)
            stringBuilder.append(i).append(" ");

        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }
    private String getOpenBoardWithRowLetter() {
        var stringBuilder = new StringBuilder();
        char letter = 'A';

        for (char[] row: this.openBoard) {
            stringBuilder.append(letter).append(" ");

            for (char space : row)
                stringBuilder.append(space).append(" ");

            letter ++;
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}

