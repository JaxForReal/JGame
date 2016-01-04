package com.jaxforreal.jgame;

import java.util.Random;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private int x, y;

    private static final Direction[] VALUES = values();
    private static final Random RANDOM = new Random();

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

     public static Direction random() {
        return VALUES[RANDOM.nextInt(VALUES.length)];
     }
}
