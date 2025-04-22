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
}
