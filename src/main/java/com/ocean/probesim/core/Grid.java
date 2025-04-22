package com.ocean.probesim.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Grid {

    private final int width;
    private final int height;
    private final Set<Position> obstacles = new HashSet<>();

    public Grid(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Grid dimensions must be non-negative");
        }
        this.width = width;
        this.height = height;
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addObstacle(int x, int y) {
        if (isValidPosition(x, y)) {
            obstacles.add(new Position(x, y));
        }
    }

    public boolean isObstacle(int x, int y) {
        return obstacles.contains(new Position(x, y));
    }

    // Internal position class to track obstacles
    private static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position pos = (Position) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
