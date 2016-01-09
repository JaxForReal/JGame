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

    /**
     * @param x Used when moving objects to add to the object's position
     * @param y Used when moving objects to add to the object's position
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return random direction of either UP, DOWN, LEFT, or RIGHT
     */
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
