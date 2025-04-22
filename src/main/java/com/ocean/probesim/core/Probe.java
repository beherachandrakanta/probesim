package com.ocean.probesim.core;

public class Probe {

    private  int x;
    private  int y;
    private  Direction direction;
    private final Grid grid;

    public Probe(int x, int y, Direction direction, Grid grid) {
        if (!grid.isValidPosition(x, y)) {
            throw new IllegalArgumentException("Invalid initial position");
        }

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid=grid;
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

    public void moveForward() {
        int newX = x;
        int newY = y;

        switch (direction) {
            case NORTH -> newY++;
            case SOUTH -> newY--;
            case EAST  -> newX++;
            case WEST  -> newX--;
        }

        if (grid.isValidPosition(newX, newY) && !grid.isObstacle(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    public void moveBackward() {
        int newX = x;
        int newY = y;

        switch (direction) {
            case NORTH -> newY--;
            case SOUTH -> newY++;
            case EAST  -> newX--;
            case WEST  -> newX++;
        }

        if (grid.isValidPosition(newX, newY) && !grid.isObstacle(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case NORTH -> direction = Direction.WEST;
            case WEST  -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.EAST;
            case EAST  -> direction = Direction.NORTH;
        }
    }

    public void turnRight() {
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST  -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST  -> direction = Direction.NORTH;
        }
    }
}
