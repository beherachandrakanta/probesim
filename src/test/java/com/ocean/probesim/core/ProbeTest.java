package com.ocean.probesim.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {

    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(5, 5);
    }

    @Test
    void shouldPlaceProbeAtStartingPositionFacingDirection() {
        Probe probe = new Probe(2, 3, Direction.NORTH,grid);
        assertEquals(2, probe.getX());
        assertEquals(3, probe.getY());
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void shouldNotAllowNegativeCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> new Probe(-1, 2, Direction.NORTH,grid));
        assertThrows(IllegalArgumentException.class, () -> new Probe(3, -5, Direction.SOUTH,grid));
    }

    @Test
    void shouldMoveForwardWhenFacingNorth() {
        Probe probe = new Probe(2, 2, Direction.NORTH,grid);
        probe.moveForward();
        assertEquals(2, probe.getX());
        assertEquals(3, probe.getY());
    }

    @Test
    void shouldMoveForwardWhenFacingEast() {
        Probe probe = new Probe(1, 1, Direction.EAST,grid);
        probe.moveForward();
        assertEquals(2, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void shouldMoveForwardWhenFacingSouth() {
        Probe probe = new Probe(1, 1, Direction.SOUTH,grid);
        probe.moveForward();
        assertEquals(1, probe.getX());
        assertEquals(0, probe.getY());
    }

    @Test
    void shouldMoveForwardWhenFacingWest() {
        Probe probe = new Probe(1, 1, Direction.WEST,grid);
        probe.moveForward();
        assertEquals(0, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void shouldMoveBackwardWhenFacingNorth() {
        Probe probe = new Probe(2, 2, Direction.NORTH,grid);
        probe.moveBackward();
        assertEquals(2, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void shouldMoveBackwardWhenFacingEast() {
        Probe probe = new Probe(2, 2, Direction.EAST,grid);
        probe.moveBackward();
        assertEquals(1, probe.getX());
        assertEquals(2, probe.getY());
    }

    @Test
    void shouldMoveBackwardWhenFacingSouth() {
        Probe probe = new Probe(2, 2, Direction.SOUTH,grid);
        probe.moveBackward();
        assertEquals(2, probe.getX());
        assertEquals(3, probe.getY());
    }

    @Test
    void shouldMoveBackwardWhenFacingWest() {
        Probe probe = new Probe(2, 2, Direction.WEST,grid);
        probe.moveBackward();
        assertEquals(3, probe.getX());
        assertEquals(2, probe.getY());
    }

    @Test
    void shouldTurnLeftFromNorthToWest() {
        Probe probe = new Probe(0, 0, Direction.NORTH,grid);
        probe.turnLeft();
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void shouldTurnRightFromNorthToEast() {
        Probe probe = new Probe(0, 0, Direction.NORTH,grid);
        probe.turnRight();
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void shouldTurnLeftAndRightInAllDirections() {
        Probe probe = new Probe(0, 0, Direction.SOUTH,grid);
        probe.turnLeft();
        assertEquals(Direction.EAST, probe.getDirection());
        probe.turnRight();
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    //Grid Boundary Checks
    @Test
    void shouldNotMoveForwardBeyondGridNorthEdge() {
        Grid grid = new Grid(5, 5);
        Probe probe = new Probe(2, 5, Direction.NORTH, grid);
        probe.moveForward();
        assertEquals(2, probe.getX());
        assertEquals(5, probe.getY());
    }

    @Test
    void shouldNotMoveBackwardBeyondGridSouthEdge() {
        Grid grid = new Grid(5, 5);
        Probe probe = new Probe(2, 0, Direction.NORTH, grid);
        probe.moveBackward();
        assertEquals(2, probe.getX());
        assertEquals(0, probe.getY());
    }

    @Test
    void shouldNotMoveForwardBeyondGridEastEdge() {
        Grid grid = new Grid(5, 5);
        Probe probe = new Probe(5, 2, Direction.EAST, grid);
        probe.moveForward();
        assertEquals(5, probe.getX());
        assertEquals(2, probe.getY());
    }

    @Test
    void shouldNotMoveForwardBeyondGridWestEdge() {
        Grid grid = new Grid(5, 5);
        Probe probe = new Probe(0, 2, Direction.WEST, grid);
        probe.moveForward();
        assertEquals(0, probe.getX());
        assertEquals(2, probe.getY());
    }

    //Visited Coordinates Tracking
    @Test
    void shouldTrackVisitedCoordinates() {
        Grid grid = new Grid(5, 5);
        Probe probe = new Probe(1, 1, Direction.NORTH, grid);

        probe.moveForward(); // (1,2)
        probe.turnRight();   // EAST
        probe.moveForward(); // (2,2)

        List<String> expected = List.of("1,1", "1,2", "2,2");

        assertEquals(expected, probe.getVisitedCoordinates());
    }

}
