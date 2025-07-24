package mission2.enums;

public enum SteeringSystem {
    BOSCH_S(1),
    MOBIS(2);

    private final int code;

    SteeringSystem(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SteeringSystem fromCode(int code) {
        for (SteeringSystem steeringSystem : values()) {
            if (steeringSystem.code == code) {
                return steeringSystem;
            }
        }
        throw new IllegalArgumentException("Invalid CarType code: " + code);
    }
}
