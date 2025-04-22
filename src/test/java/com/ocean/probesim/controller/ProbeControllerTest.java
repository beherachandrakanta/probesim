package com.ocean.probesim.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocean.probesim.dto.CommandRequest;
import com.ocean.probesim.dto.InitRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProbeController.class)
class ProbeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldInitializeProbe() throws Exception {
        InitRequest request = new InitRequest(5, 5, 1, 1, "NORTH");

        mockMvc.perform(post("/probe/init")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSendCommandToProbe() throws Exception {
        CommandRequest commandRequest = new CommandRequest(List.of("MOVE", "RIGHT", "MOVE"));

        mockMvc.perform(post("/probe/command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commandRequest)))
                .andExpect(status().isOk());
    }
}

