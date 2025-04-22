package com.ocean.probesim.core;

public class Probe {

    private  int x;
    private  int y;
    private  Direction direction;

    public Probe(int x, int y, Direction direction) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be non-negative");
        }

        this.x = x;
        this.y = y;
        this.direction = direction;
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
        switch (direction) {
            case NORTH -> y++;
            case SOUTH -> y--;
            case EAST  -> x++;
            case WEST  -> x--;
        }
    }
    public void moveBackward() {
        switch (direction) {
            case NORTH -> y--;
            case SOUTH -> y++;
            case EAST  -> x--;
            case WEST  -> x++;
        }
    }

    public void turnLeft() {
        // stub
    }

    public void turnRight() {
        // stub
    }
}
