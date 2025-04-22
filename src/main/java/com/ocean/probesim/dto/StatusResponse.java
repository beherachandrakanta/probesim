package com.ocean.probesim.dto;


import java.util.List;

public record StatusResponse(int x, int y, String direction, List<String> visited) {

}