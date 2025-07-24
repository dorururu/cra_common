package mission2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static mission2.AssembleProcess.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class AssembleServiceTest {


    @Mock
    AssembleProcess assembleProcess;

    @Test
    @DisplayName("input이 숫자가 아니면 진행 불가")
    void input_should_be_number() {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble("abc", CarType_Question);

        assertEquals(CarType_Question, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "4"})
    @DisplayName("CarType 선택할때 input은 1~3사이여야 assemble 진행 가능")
    void carType_input_should_be_1_3_fail(String userInput) {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble(userInput, CarType_Question);

        assertEquals(CarType_Question, result);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-1", "5"})
    @DisplayName("Engine 선택할때 input은 1~4사이여야 assemble 진행 가능")
    void engine_input_should_be_1_4_fail(String userInput) {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble(userInput, Engine_Question);

        assertEquals(Engine_Question, result);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-1", "4"})
    @DisplayName("BreakSystem 선택할때 input은 1~3사이여야 assemble 진행 가능")
    void breaksystem_input_should_be_1_4_fail(String userInput) {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble(userInput, BrakeSystem_Question);

        assertEquals(BrakeSystem_Question, result);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-1", "4"})
    @DisplayName("SteeringSystem_Question 선택할때 input은 1~3사이여야 assemble 진행 가능")
    void steeringsystem_input_should_be_1_4_fail(String userInput) {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble(userInput, SteeringSystem_Question);

        assertEquals(SteeringSystem_Question, result);
    }

    @Test
    @DisplayName("Run Test 후 0을 받으면 처음으로 돌아간다")
    void go_back_first_step_success() {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble("0", Run_Test);

        assertEquals(CarType_Question, result);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3})
    @DisplayName("step 이 1, 2, 3일때 0을 입력받으면 입력받으면 이전 스텝으로 돌아간다")
    void go_back_first_step_success(int step) {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble("0", step);

        assertEquals(step - 1, result);
    }



}