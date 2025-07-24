package mission2;

import mission2.enums.BrakeSystem;
import mission2.enums.CarType;
import mission2.enums.Engine;
import mission2.enums.SteeringSystem;

public class Car {
    private CarType carType;
    private Engine engine;
    private BrakeSystem brakeSystem;
    private SteeringSystem steeringSystem;

    public void setCarType(int input) {
        this.carType = CarType.fromCode(input);
    }

    public void setEngine(int input) {
        this.engine = Engine.fromCode(input);
    }

    public void setBreakSystem(int input) {
        this.brakeSystem = BrakeSystem.fromCode(input);
    }

    public void setSteeringSystem(int input) {
        this.steeringSystem = SteeringSystem.fromCode(input);
    }

    public CarType getCarType() {
        return carType;
    }

    public Engine getEngine() {
        return engine;
    }

    public BrakeSystem getBrakeSystem() {
        return brakeSystem;
    }

    public SteeringSystem getSteeringSystem() {
        return steeringSystem;
    }
}
