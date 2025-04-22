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
}

