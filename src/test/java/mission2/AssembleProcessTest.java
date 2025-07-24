package mission2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AssembleProcessTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 4, 5})
    @DisplayName("CarType 선택할때 input은 1~3사이가 아니면 진행 불가")
    void carType_input_should_be_1_3_fail(int userInput) {
        boolean result = AssembleProcess.isValidUserInputRange(AssembleProcess.CarType_Question, userInput);

        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("CarType 선택할때 input은 1~3사이여야 assemble 진행 가능")
    void carType_input_should_be_1_3_success(int userInput) {
        boolean result = AssembleProcess.isValidUserInputRange(AssembleProcess.CarType_Question, userInput);

        assertTrue(result);
    }
}