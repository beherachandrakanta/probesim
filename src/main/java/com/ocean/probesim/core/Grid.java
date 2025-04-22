package com.ocean.probesim.core;

public class Grid {

    private final int width;
    private final int height;

    public Grid(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Grid dimensions must be non-negative");
        }
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

