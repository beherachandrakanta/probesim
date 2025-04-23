package com.ocean.probesim.core;

import com.ocean.probesim.dto.InitRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void shouldPrintSummaryAfterCommands() {
        Grid grid = new Grid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.executeCommands("FFRFF");

        String summary = probe.getSummary();

        String expected = """
        Final Position: (2, 2) facing EAST
        Visited: [(0, 0), (0, 1), (0, 2), (1, 2), (2, 2)]
        """;

        assertEquals(expected.trim(), summary.trim());
    }

    @Test
    void shouldInitializeProbe() throws Exception {
        InitRequest request = new InitRequest(5, 5, 1, 1, "NORTH");
        request.setObstacles(List.of("2,2", "3,3"));

        mockMvc.perform(post("/probe/init")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
