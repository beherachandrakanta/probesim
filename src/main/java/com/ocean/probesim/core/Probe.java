package com.ocean.probesim.core;

import com.ocean.probesim.model.Position;
import java.util.ArrayList;
import java.util.List;

public class Probe {
    private int x;
    private int y;
    private Direction direction;
    private final Grid grid;
    private final List<Position> visitedCoordinates = new ArrayList<>();

    public Probe(int x, int y, Direction direction, Grid grid) {
        if (!grid.isValidPosition(x, y)) {
            throw new IllegalArgumentException("Invalid initial position");
        }
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
        recordPosition(); // store initial position
    }

    public void moveForward() {
        int newX = x;
        int newY = y;

        switch (direction) {
            case NORTH -> newY++;
            case SOUTH -> newY--;
            case EAST -> newX++;
            case WEST -> newX--;
        }

        if (grid.isValidPosition(newX, newY) && !grid.isObstacle(newX, newY)) {
            x = newX;
            y = newY;
            recordPosition();
        }
    }

    public void moveBackward() {
        int newX = x;
        int newY = y;

        switch (direction) {
            case NORTH -> newY--;
            case SOUTH -> newY++;
            case EAST -> newX--;
            case WEST -> newX++;
        }

        if (grid.isValidPosition(newX, newY) && !grid.isObstacle(newX, newY)) {
            x = newX;
            y = newY;
            recordPosition();
        }
    }

    public void turnLeft() {
        direction = direction.left();
    }

    public void turnRight() {
        direction = direction.right();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<String> getVisitedCoordinates() {
        List<String> stringCoords = new ArrayList<>();
        for (Position pos : visitedCoordinates) {
            stringCoords.add(pos.getX() + "," + pos.getY());
        }
        return stringCoords;
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Final Position: (")
                .append(x).append(", ")
                .append(y).append(") facing ")
                .append(direction.name()).append("\n");

        sb.append("Visited: [");
        for (int i = 0; i < visitedCoordinates.size(); i++) {
            Position pos = visitedCoordinates.get(i);
            sb.append("(").append(pos.getX()).append(", ").append(pos.getY()).append(")");
            if (i < visitedCoordinates.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void recordPosition() {
        visitedCoordinates.add(new Position(x, y));
    }

    public void executeCommands(String commands) {
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'F' -> moveForward();
                case 'B' -> moveBackward();
                case 'L' -> turnLeft();
                case 'R' -> turnRight();
                default -> throw new IllegalArgumentException("Unknown command: " + command);
            }
        }
    }

}
