package bykova.tetris.model;

import java.util.*;

public class Shape {

    private static final Map<Integer, List<Point>> INITIAL_COORDINATES = new HashMap<Integer, List<Point>>(7);
    static {
        INITIAL_COORDINATES.put(ShapeType.I, Arrays.asList(new Point(3, 0), new Point(4, 0), new Point(5, 0), new Point(6, 0)));
        INITIAL_COORDINATES.put(ShapeType.J, Arrays.asList(new Point(3, -1), new Point(3, 0), new Point(4, 0), new Point(5, 0)));
        INITIAL_COORDINATES.put(ShapeType.L, Arrays.asList(new Point(3, 0), new Point(4, 0), new Point(5, 0), new Point(5, -1)));
        INITIAL_COORDINATES.put(ShapeType.O, Arrays.asList(new Point(4, -1), new Point(5, -1), new Point(4, 0), new Point(5, 0)));
        INITIAL_COORDINATES.put(ShapeType.S, Arrays.asList(new Point(3, 0), new Point(4, 0), new Point(4, -1), new Point(5, -1)));
        INITIAL_COORDINATES.put(ShapeType.T, Arrays.asList(new Point(3, 0), new Point(4, 0), new Point(4, -1), new Point(5, 0)));
        INITIAL_COORDINATES.put(ShapeType.Z, Arrays.asList(new Point(3, -1), new Point(4, -1), new Point(4, 0), new Point(5, 0)));
    }

    final int type;
    final List<Point> squares;

    public Shape(int type) {
        this.type = type;
        this.squares = INITIAL_COORDINATES.get(type);
    }
}
