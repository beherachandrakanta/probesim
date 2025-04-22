package com.ocean.probesim.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    void shouldCreateGridWithGivenDimensions() {
        Grid grid = new Grid(10, 5);
        assertEquals(10, grid.getWidth());
        assertEquals(5, grid.getHeight());
    }

    // for  Negative Grid Dimension Testing
    @Test
    void shouldNotAllowNegativeGridDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(-1, 5));
        assertThrows(IllegalArgumentException.class, () -> new Grid(5, -1));
    }

    @Test
    void shouldNotMoveForwardIntoObstacle() {
        Grid grid = new Grid(5, 5);
        grid.addObstacle(2, 3);
        Probe probe = new Probe(2, 2, Direction.NORTH, grid);
        probe.moveForward();
        assertEquals(2, probe.getX());
        assertEquals(2, probe.getY());
    }


}

