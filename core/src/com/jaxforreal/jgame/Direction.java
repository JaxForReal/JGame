package com.jaxforreal.jgame;

import java.util.Random;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private static final Direction[] VALUES = values();
    private static final Random RANDOM = new Random();
    private int x, y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction random() {
        return VALUES[RANDOM.nextInt(VALUES.length)];
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
