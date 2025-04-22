package com.ocean.probesim.core;

public class Probe {

    private final int x;
    private final int y;
    private final Direction direction;

    public Probe(int x, int y, Direction direction) {
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
