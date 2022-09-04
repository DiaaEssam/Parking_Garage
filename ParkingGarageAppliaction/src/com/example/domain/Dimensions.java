package com.example.domain;

public class Dimensions {
    private final int WIDTH;
    private final int DEPTH;

    public Dimensions(int width, int depth) {
        this.WIDTH = width;
        this.DEPTH = depth;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getDepth() {
        return DEPTH;
    }

    @Override
//    (WIDTHxDEPTH)
    public String toString() {
        return "(" + this.WIDTH + "⨉" + this.DEPTH + ")";
    }
}
