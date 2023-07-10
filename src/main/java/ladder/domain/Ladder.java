package ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ladder {
    public static final int MIN_HEIGHT = 1;
    private final List<JerryLine> lines;

    private Ladder(List<JerryLine> lines) {
        validateHeight(lines.size());

        this.lines = lines;
    }

    public static Ladder of(int width, int height) {
        validateHeight(height);

        return new Ladder(Stream.generate(() -> JerryLine.createLineWithWidth(width))
                .limit(height)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    public static Ladder of(List<JerryLine> lines) {
        return new Ladder(lines);
    }

    private static void validateHeight(int height) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("사다리 높이는 " + MIN_HEIGHT + " 이상이어야 합니다.");
        }
    }

    public List<JerryLine> getLines() {
        return lines;
    }

    public int getLastPosition(int position) {
        return lines.stream()
                .reduce(position, (x, line) -> line.getNextPosition(x), (x, y) -> y);
    }
}
