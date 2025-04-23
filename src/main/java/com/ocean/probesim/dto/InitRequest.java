package com.ocean.probesim.dto;

import java.util.List;

public class InitRequest {
     public int gridWidth;
     int gridHeight;
     int startX;
     int startY;
     String direction;
     List<String> obstacles; // NEW

    public InitRequest() {}

    public InitRequest(int gridWidth, int gridHeight, int startX, int startY, String direction) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.startX = startX;
        this.startY = startY;
        this.direction = direction;
    }

    // Getters and setters...

    public List<String> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<String> obstacles) {
        this.obstacles = obstacles;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}

