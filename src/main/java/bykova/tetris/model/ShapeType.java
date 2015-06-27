package bykova.tetris.model;

public enum ShapeType {
    EMPTY,
    I,
    J,
    L,
    O,
    S,
    T,
    Z;

    public static ShapeType fromInteger(int index) {
        ShapeType[] values = values();
        if (index < 0 || index >= values.length) {
            throw new IllegalArgumentException(
                    String.format("Index must be between 0 and %d. Actual value is %d.", values.length, index)
            );
        } else {
            return values[index];
        }
    }
}
