package mission2;

import mission2.enums.BrakeSystem;
import mission2.enums.CarType;
import mission2.enums.Engine;
import mission2.enums.SteeringSystem;

import java.util.Scanner;

import static mission2.AssembleProcess.*;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private Car car;


    public void startAssembleProcess() {
        Scanner sc = new Scanner(System.in);

        car = new Car();

        int assembleStep = CarType_Question;

        while (true) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();

            AssembleProcess.showMenu(assembleStep);

            String buf = getUserInput(sc);

            if (isExit(buf)) break;

            int userInput;
            try {
                userInput = Integer.parseInt(buf);
            } catch (NumberFormatException e) {
                System.out.println("ERROR :: 숫자만 입력 가능");
                delay(800);
                continue;
            }

            if (!AssembleProcess.isValidUserInputRange(assembleStep, userInput)) {
                delay(800);
                continue;
            }

            if (isGoFirstStep(userInput, assembleStep)) {
                assembleStep = CarType_Question;
                continue;
            }

            if (isGoBackToPreviousStep(userInput, assembleStep)) {
                assembleStep--;
                continue;
            }

            assembleStep = assembleAndGetNextStep(assembleStep, userInput);
        }

        sc.close();
    }

    public String getUserInput(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    private int assembleAndGetNextStep(int assembleStep, int userInput) {
        switch (assembleStep) {
            case CarType_Question:
                selectCarType(userInput);
                delay(800);
                assembleStep = Engine_Question;
                break;
            case Engine_Question:
                selectEngine(userInput);
                delay(800);
                assembleStep = BrakeSystem_Question;
                break;
            case BrakeSystem_Question:
                selectBrakeSystem(userInput);
                delay(800);
                assembleStep = SteeringSystem_Question;
                break;
            case SteeringSystem_Question:
                selectSteeringSystem(userInput);
                delay(800);
                assembleStep = Run_Test;
                break;
            case Run_Test:
                if (userInput == 1) {
                    runProducedCar();
                    delay(2000);
                } else if (userInput == 2) {
                    System.out.println("Test...");
                    delay(1500);
                    testProducedCar();
                    delay(2000);
                }
                break;
        }
        return assembleStep;
    }

    private boolean isGoBackToPreviousStep(int userInput, int assembleStep) {
        return userInput == 0 && assembleStep > CarType_Question && assembleStep != Run_Test;
    }

    private boolean isGoFirstStep(int userInput, int assembleStep) {
        return userInput == 0 && assembleStep == Run_Test;
    }

    private boolean isExit(String buf) {
        if (buf.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;
    }

    private void selectCarType(int userInput) {
        car.setCarType(userInput);
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", car.getCarType().name());
    }
    private void selectEngine(int userInput) {
        car.setEngine(userInput);
        System.out.printf("%s 엔진을 선택하셨습니다.\n", car.getEngine().name());
    }
    private void selectBrakeSystem(int userInput) {
        car.setBreakSystem(userInput);
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", car.getBrakeSystem().name());
    }
    private void selectSteeringSystem(int userInput) {
        car.setSteeringSystem(userInput);
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", car.getSteeringSystem().name());
    }

    private boolean isValidCheck() {
        if (car.getCarType().equals(CarType.SEDAN) && car.getBrakeSystem().equals(BrakeSystem.CONTINENTAL)) return false;
        if (car.getCarType().equals(CarType.SUV)   && car.getEngine().equals(Engine.TOYOTA))       return false;
        if (car.getCarType().equals(CarType.TRUCK) && car.getEngine().equals(Engine.WIA))          return false;
        if (car.getCarType().equals(CarType.TRUCK) && car.getBrakeSystem().equals(BrakeSystem.MANDO))  return false;
        if (car.getBrakeSystem().equals(BrakeSystem.BOSCH_B) && !car.getSteeringSystem().equals(SteeringSystem.BOSCH_S)) return false;
        return true;
    }

    private void runProducedCar() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (car.getEngine().equals(Engine.WRONG)) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        System.out.printf("Car Type : %s\n", car.getCarType().name());
        System.out.printf("Engine   : %s\n", car.getEngine().name());
        System.out.printf("Brake    : %s\n", car.getBrakeSystem().name());
        System.out.printf("Steering : %s\n", car.getSteeringSystem().name());
        System.out.println("자동차가 동작됩니다.");
    }

    private void testProducedCar() {
        if (car.getCarType().equals(CarType.SEDAN) && car.getBrakeSystem().equals(BrakeSystem.CONTINENTAL)) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (car.getCarType().equals(CarType.SUV) && car.getEngine().equals(Engine.TOYOTA)) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (car.getCarType().equals(CarType.TRUCK) && car.getEngine().equals(Engine.WIA)) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (car.getCarType().equals(CarType.TRUCK) && car.getBrakeSystem().equals(BrakeSystem.MANDO)) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (car.getBrakeSystem().equals(BrakeSystem.BOSCH_B) && !car.getSteeringSystem().equals(SteeringSystem.BOSCH_S)) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }


    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
