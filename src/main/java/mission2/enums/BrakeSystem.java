package mission2.enums;

public enum BrakeSystem {
    MANDO(1),
    CONTINENTAL(2),
    BOSCH_B(3);

    private final int code;

    BrakeSystem(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BrakeSystem fromCode(int code) {
        for (BrakeSystem brakeSystem : values()) {
            if (brakeSystem.code == code) {
                return brakeSystem;
            }
        }
        throw new IllegalArgumentException("Invalid CarType code: " + code);
    }
}
