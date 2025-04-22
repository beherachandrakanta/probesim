package com.ocean.probesim.core;

import java.util.ArrayList;
import java.util.List;

public class Probe {
    private int x;
    private int y;
    private Direction direction;
    private final Grid grid;
    private final List<String> visitedCoordinates = new ArrayList<>();

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
        return new ArrayList<>(visitedCoordinates);
    }

    private void recordPosition() {
        visitedCoordinates.add(x + "," + y);
    }
}

