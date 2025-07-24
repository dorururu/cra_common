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

    public void run() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (engine.equals(Engine.WRONG)) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        System.out.printf("Car Type : %s\n", carType.name());
        System.out.printf("Engine   : %s\n", engine.name());
        System.out.printf("Brake    : %s\n", brakeSystem.name());
        System.out.printf("Steering : %s\n", steeringSystem.name());
        System.out.println("자동차가 동작됩니다.");
    }

    private boolean isValidCheck() {
        if (isSedanAndContinental()) return false;
        if (isSuvToyota())       return false;
        if (isTruckWia())          return false;
        if (isTruckMando())  return false;
        if (isBoschBAndNotBoschS()) return false;
        return true;
    }

    private boolean isBoschBAndNotBoschS() {
        return brakeSystem.equals(BrakeSystem.BOSCH_B) && !steeringSystem.equals(SteeringSystem.BOSCH_S);
    }

    private boolean isTruckMando() {
        return carType.equals(CarType.TRUCK) && brakeSystem.equals(BrakeSystem.MANDO);
    }

    private boolean isTruckWia() {
        return carType.equals(CarType.TRUCK) && engine.equals(Engine.WIA);
    }

    private boolean isSuvToyota() {
        return carType.equals(CarType.SUV) && engine.equals(Engine.TOYOTA);
    }

    private boolean isSedanAndContinental() {
        return carType.equals(CarType.SEDAN) && brakeSystem.equals(BrakeSystem.CONTINENTAL);
    }


    public void test() {
        if (isSedanAndContinental()) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (isSuvToyota()) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (isTruckWia()) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (isTruckMando()) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (isBoschBAndNotBoschS()) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }
}
