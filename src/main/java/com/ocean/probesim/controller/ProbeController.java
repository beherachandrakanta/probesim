package com.ocean.probesim.controller;

import com.ocean.probesim.dto.*;
import com.ocean.probesim.service.ProbeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probe")
public class ProbeController {

    private final ProbeService probeService;

    public ProbeController(ProbeService probeService) {
        this.probeService = probeService;
    }

    @PostMapping("/init")
    public ResponseEntity<Void> init(@RequestBody InitRequest request) {
        probeService.initialize(request);
        return ResponseEntity.ok().build();
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
