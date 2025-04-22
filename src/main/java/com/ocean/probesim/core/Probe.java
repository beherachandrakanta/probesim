package com.ocean.probesim.core;

public class Probe {

    private final int x;
    private final int y;
    private final Direction direction;

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
}
