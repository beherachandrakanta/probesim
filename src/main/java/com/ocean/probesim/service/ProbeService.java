package com.ocean.probesim.service;

import com.ocean.probesim.core.*;
import com.ocean.probesim.dto.*;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
    private Probe probe;

    public void initialize(InitRequest req) {
        Grid grid = new Grid(req.gridWidth(), req.gridHeight());
        probe = new Probe(req.startX(), req.startY(), Direction.valueOf(req.direction()), grid);
    }

    public void executeCommands(java.util.List<String> commands) {
        for (String cmd : commands) {
            switch (cmd.toUpperCase()) {
                case "MOVE" -> probe.moveForward();
                case "BACK" -> probe.moveBackward();
                case "LEFT" -> probe.turnLeft();
                case "RIGHT" -> probe.turnRight();
            }
        }
    }

    public StatusResponse getStatus() {
        return new StatusResponse(
                probe.getX(),
                probe.getY(),
                probe.getDirection().name(),
                probe.getVisitedCoordinates()
        );
    }
}
