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
    @ValueSource(strings = {"-1", "0", "4"})
    @DisplayName("CarType 선택할때 input은 1~3사이여야 assemble 진행 가능")
    void carType_input_should_be_1_3_fail(String userInput) {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble(userInput, CarType_Question);

        assertEquals(CarType_Question, result);
    }

    @ParameterizedTest
    @ValueSource(strings = { "0", "5"})
    @DisplayName("Engine 선택할때 input은 1~4사이여야 assemble 진행 가능")
    void engine_input_should_be_1_4_fail(String userInput) {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble(userInput, Engine_Question);

        assertEquals(Engine_Question, result);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-1", "0", "4"})
    @DisplayName("BreakSystem 선택할때 input은 1~3사이여야 assemble 진행 가능")
    void breaksystem_input_should_be_1_4_fail(String userInput) {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble(userInput, BrakeSystem_Question);

        assertEquals(BrakeSystem_Question, result);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-1", "0", "3"})
    @DisplayName("SteeringSystem_Question 선택할때 input은 1~3사이여야 assemble 진행 가능")
    void steeringsystem_input_should_be_1_4_fail(String userInput) {
        AssembleService assembleService = new AssembleService();

        int result = assembleService.validateAndAssemble(userInput, SteeringSystem_Question);

        assertEquals(SteeringSystem_Question, result);
    }


}