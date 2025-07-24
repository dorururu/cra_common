package mission2;

import java.util.Scanner;

import static mission2.AssembleStep.*;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;

    private static int[] userInputArray = new int[5];

    public void startAssembleProcess() {
        Scanner sc = new Scanner(System.in);

        int assembleStep = CarType_Question;

        while (true) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();

            AssembleStep.showMenu(assembleStep);

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

            if (!isValidUserInputRange(assembleStep, userInput)) {
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
        return userInput == 0 && assembleStep > CarType_Question;
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



    private boolean isValidUserInputRange(int step, int ans) {
        switch (step) {
            case CarType_Question:
                if (ans < 1 || ans > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case Engine_Question:
                if (ans < 0 || ans > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case BrakeSystem_Question:
                if (ans < 0 || ans > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case SteeringSystem_Question:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
                break;
            case Run_Test:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }

    private void selectCarType(int userInput) {
        userInputArray[CarType_Question] = userInput;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", userInput == 1 ? "Sedan" : userInput == 2 ? "SUV" : "Truck");
    }
    private void selectEngine(int a) {
        userInputArray[Engine_Question] = a;
        String name = a == 1 ? "GM" : a == 2 ? "TOYOTA" : a == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }
    private void selectBrakeSystem(int a) {
        userInputArray[BrakeSystem_Question] = a;
        String name = a == 1 ? "MANDO" : a == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }
    private void selectSteeringSystem(int a) {
        userInputArray[SteeringSystem_Question] = a;
        String name = a == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }


    private boolean isValidCheck() {
        if (userInputArray[CarType_Question] == SEDAN && userInputArray[BrakeSystem_Question] == CONTINENTAL) return false;
        if (userInputArray[CarType_Question] == SUV   && userInputArray[Engine_Question] == TOYOTA)       return false;
        if (userInputArray[CarType_Question] == TRUCK && userInputArray[Engine_Question] == WIA)          return false;
        if (userInputArray[CarType_Question] == TRUCK && userInputArray[BrakeSystem_Question] == MANDO)  return false;
        if (userInputArray[BrakeSystem_Question] == BOSCH_B && userInputArray[SteeringSystem_Question] != BOSCH_S) return false;
        return true;
    }

    private void runProducedCar() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (userInputArray[Engine_Question] == 4) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        String[] carNames = {"", "Sedan", "SUV", "Truck"};
        String[] engNames = {"", "GM", "TOYOTA", "WIA"};
        System.out.printf("Car Type : %s\n", carNames[userInputArray[CarType_Question]]);
        System.out.printf("Engine   : %s\n", engNames[userInputArray[Engine_Question]]);
        System.out.printf("Brake    : %s\n",
                userInputArray[BrakeSystem_Question]==1? "Mando":
                        userInputArray[BrakeSystem_Question]==2? "Continental":"Bosch");
        System.out.printf("Steering : %s\n",
                userInputArray[SteeringSystem_Question]==1? "Bosch":"Mobis");
        System.out.println("자동차가 동작됩니다.");
    }

    private void testProducedCar() {
        if (userInputArray[CarType_Question] == SEDAN && userInputArray[BrakeSystem_Question] == CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (userInputArray[CarType_Question] == SUV && userInputArray[Engine_Question] == TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (userInputArray[CarType_Question] == TRUCK && userInputArray[Engine_Question] == WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (userInputArray[CarType_Question] == TRUCK && userInputArray[BrakeSystem_Question] == MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (userInputArray[BrakeSystem_Question] == BOSCH_B && userInputArray[SteeringSystem_Question] != BOSCH_S) {
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
