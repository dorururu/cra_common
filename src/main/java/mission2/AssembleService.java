package mission2;

import java.util.Scanner;

import static mission2.AssembleProcess.*;

public class AssembleService {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private Car car;
    private int assembleStep;

    public void startAssembleProcess() {
        Scanner sc = new Scanner(System.in);

        car = new Car();

        assembleStep = CarType_Question;

        while (true) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();

            AssembleProcess.showMenu(assembleStep);

            String buf = getUserInput(sc);

            if (isExit(buf)) break;

            validateAndAssemble(buf);
        }

        sc.close();
    }

    public void validateAndAssemble(String buf) {
        int userInput;

        try {
            userInput = Integer.parseInt(buf);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            delay(800);
            return;
        }

        if (!AssembleProcess.isValidUserInputRange(assembleStep, userInput)) {
            delay(800);
            return;
        }

        if (isGoFirstStep(userInput)) {
            assembleStep = CarType_Question;
            return;
        }

        if (isGoBackToPreviousStep(userInput)) {
            assembleStep--;
            return;
        }

        assembleStep = assembleAndGetNextStep(userInput);
    }

    public String getUserInput(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    public int assembleAndGetNextStep(int userInput) {
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
                    car.run();
                    delay(2000);
                } else if (userInput == 2) {
                    System.out.println("Test...");
                    delay(1500);
                    car.test();
                    delay(2000);
                }
                break;
        }
        return assembleStep;
    }

    private boolean isGoBackToPreviousStep(int userInput) {
        return userInput == 0 && assembleStep > CarType_Question && assembleStep != Run_Test;
    }

    private boolean isGoFirstStep(int userInput) {
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

    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
