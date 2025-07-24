package mission1;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CarType_Question = 0;
    private static final int Engine_Question = 1;
    private static final int BrakeSystem_Question = 2;
    private static final int SteeringSystem_Question = 3;
    private static final int Run_Test       = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;

    private static int[] userInputArray = new int[5];

    public static void main(String[] args) {
        startAssembleProcess();
    }

    private static void startAssembleProcess() {
        Scanner sc = new Scanner(System.in);

        int assembleStep = CarType_Question;

        while (true) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();

            showMenuByAssembleStep(assembleStep);

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

    public static String getUserInput(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    private static int assembleAndGetNextStep(int assembleStep, int userInput) {
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

    private static boolean isGoBackToPreviousStep(int userInput, int assembleStep) {
        return userInput == 0 && assembleStep > CarType_Question;
    }

    private static boolean isGoFirstStep(int userInput, int assembleStep) {
        return userInput == 0 && assembleStep == Run_Test;
    }

    private static boolean isExit(String buf) {
        if (buf.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;
    }

    private static void showMenuByAssembleStep(int assembleStep) {
        switch (assembleStep) {
            case CarType_Question:
                showCarTypeMenu(); break;
            case Engine_Question:
                showEngineMenu(); break;
            case BrakeSystem_Question:
                showBrakeMenu(); break;
            case SteeringSystem_Question:
                showSteeringMenu(); break;
            case Run_Test:
                showRunTestMenu(); break;
        }
    }

    private static void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }
    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }
    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }
    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }
    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    private static boolean isValidUserInputRange(int step, int ans) {
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

    private static void selectCarType(int userInput) {
        userInputArray[CarType_Question] = userInput;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", userInput == 1 ? "Sedan" : userInput == 2 ? "SUV" : "Truck");
    }
    private static void selectEngine(int a) {
        userInputArray[Engine_Question] = a;
        String name = a == 1 ? "GM" : a == 2 ? "TOYOTA" : a == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }
    private static void selectBrakeSystem(int a) {
        userInputArray[BrakeSystem_Question] = a;
        String name = a == 1 ? "MANDO" : a == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }
    private static void selectSteeringSystem(int a) {
        userInputArray[SteeringSystem_Question] = a;
        String name = a == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }


    private static boolean isValidCheck() {
        if (userInputArray[CarType_Question] == SEDAN && userInputArray[BrakeSystem_Question] == CONTINENTAL) return false;
        if (userInputArray[CarType_Question] == SUV   && userInputArray[Engine_Question] == TOYOTA)       return false;
        if (userInputArray[CarType_Question] == TRUCK && userInputArray[Engine_Question] == WIA)          return false;
        if (userInputArray[CarType_Question] == TRUCK && userInputArray[BrakeSystem_Question] == MANDO)  return false;
        if (userInputArray[BrakeSystem_Question] == BOSCH_B && userInputArray[SteeringSystem_Question] != BOSCH_S) return false;
        return true;
    }

    private static void runProducedCar() {
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

    private static void testProducedCar() {
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

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }


    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
