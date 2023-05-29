package ladder.domain.enums;

import java.util.Random;

public enum Direction {
    LEFT(-1),
    NONE(0),
    RIGHT(1);

    private final int value;
    private static Random random = new Random();

    Direction(int value) {
        this.value = value;
    }

    private static Direction getRightOrNone() {
        if (random.nextBoolean()) {
            return RIGHT;
        }
        return NONE;
    }

    public static Direction getConnectDirection(Direction previous, boolean isLast) {
        if (previous.equals(RIGHT)) {
            return LEFT;
        }

        if (isLast) {
            return NONE;
        }

        return getRightOrNone();
    }

    public int getNextPosition(int currentPosition) {
        return currentPosition + value;
    }
}
