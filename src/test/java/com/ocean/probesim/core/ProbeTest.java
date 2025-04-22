package com.ocean.probesim.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {

    @Test
    void shouldPlaceProbeAtStartingPositionFacingDirection() {
        Probe probe = new Probe(2, 3, Direction.NORTH);
        assertEquals(2, probe.getX());
        assertEquals(3, probe.getY());
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void shouldNotAllowNegativeCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> new Probe(-1, 2, Direction.NORTH));
        assertThrows(IllegalArgumentException.class, () -> new Probe(3, -5, Direction.SOUTH));
    }

}
