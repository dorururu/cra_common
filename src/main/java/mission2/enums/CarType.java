package mission2.enums;

public enum CarType {
    SEDAN(1),
    SUV(2),
    TRUCK(3);

    private final int code;

    CarType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CarType fromCode(int code) {
        for (CarType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CarType code: " + code);
    }
}
