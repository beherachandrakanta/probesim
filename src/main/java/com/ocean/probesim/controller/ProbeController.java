package com.ocean.probesim.controller;

import com.ocean.probesim.core.Direction;
import com.ocean.probesim.core.Grid;
import com.ocean.probesim.core.Probe;
import com.ocean.probesim.dto.*;
import com.ocean.probesim.service.ProbeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probe")
public class ProbeController {

    private final ProbeService probeService;
    private Probe probe;

    public ProbeController(ProbeService probeService) {
        this.probeService = probeService;
    }

    @PostMapping("/init")
    public ResponseEntity<String> init(@RequestBody InitRequest request) {
        Grid grid = new Grid(request.getGridWidth(), request.getGridHeight());

        if (request.getObstacles() != null) {
            for (String obstacle : request.getObstacles()) {
                String[] parts = obstacle.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                grid.addObstacle(x, y);
            }

            Direction dir = Direction.valueOf(request.getDirection().toUpperCase());
            probe = new Probe(request.getStartX(), request.getStartY(), dir, grid);


        }
        return ResponseEntity.ok("Initialized");
    }

    @PostMapping("/command")
    public ResponseEntity<Void> command(@RequestBody CommandRequest request) {
        probeService.executeCommands(request.commands());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> status() {
        return ResponseEntity.ok(probeService.getStatus());
    }
}
