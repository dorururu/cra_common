package mission2.enums;

public enum Engine {
    GM(1),
    TOYOTA(2),
    WIA(3);

    private final int code;

    Engine(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Engine fromCode(int code) {
        for (Engine engine : values()) {
            if (engine.code == code) {
                return engine;
            }
        }
        throw new IllegalArgumentException("Invalid CarType code: " + code);
    }
}
