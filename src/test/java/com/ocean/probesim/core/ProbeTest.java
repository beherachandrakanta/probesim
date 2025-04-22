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

    @Test
    void shouldMoveForwardWhenFacingNorth() {
        Probe probe = new Probe(2, 2, Direction.NORTH);
        probe.moveForward();
        assertEquals(2, probe.getX());
        assertEquals(3, probe.getY());
    }

    @Test
    void shouldMoveForwardWhenFacingEast() {
        Probe probe = new Probe(1, 1, Direction.EAST);
        probe.moveForward();
        assertEquals(2, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void shouldMoveForwardWhenFacingSouth() {
        Probe probe = new Probe(1, 1, Direction.SOUTH);
        probe.moveForward();
        assertEquals(1, probe.getX());
        assertEquals(0, probe.getY());
    }

    @Test
    void shouldMoveForwardWhenFacingWest() {
        Probe probe = new Probe(1, 1, Direction.WEST);
        probe.moveForward();
        assertEquals(0, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void shouldMoveBackwardWhenFacingNorth() {
        Probe probe = new Probe(2, 2, Direction.NORTH);
        probe.moveBackward();
        assertEquals(2, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void shouldMoveBackwardWhenFacingEast() {
        Probe probe = new Probe(2, 2, Direction.EAST);
        probe.moveBackward();
        assertEquals(1, probe.getX());
        assertEquals(2, probe.getY());
    }

    @Test
    void shouldMoveBackwardWhenFacingSouth() {
        Probe probe = new Probe(2, 2, Direction.SOUTH);
        probe.moveBackward();
        assertEquals(2, probe.getX());
        assertEquals(3, probe.getY());
    }

    @Test
    void shouldMoveBackwardWhenFacingWest() {
        Probe probe = new Probe(2, 2, Direction.WEST);
        probe.moveBackward();
        assertEquals(3, probe.getX());
        assertEquals(2, probe.getY());
    }

}
