import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    void nameIsNullOnConstructionWithTwoParameters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @Test
    void nameIsNullOnConstructionWithThreeParameters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(chars = {'\t', '\n', '\r', '\f'})
    void nameInWhiteSpaceOnConstructionWithTwoParameters(Character argument) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            String name = Character.toString(argument);
            new Horse(name, 5.0);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(chars = {'\t', '\n', '\r', '\f'})
    void nameInWhiteSpaceOnConstructionWithThreeParameters(Character argument) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            String name = Character.toString(argument);
            new Horse(name, 5.0, 1.0);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void negativeSpeedOnConstructionWithTwoParameters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Lila", - 5.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void negativeSpeedOnConstructionWithThreeParameters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Lila", - 5.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void distanceNegativeOnConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Lila", 5.0, - 1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldNameInGetName() {
        Horse lila = new Horse("Lila", 5.0, 1.0);
        assertEquals("Lila", lila.getName());
    }

    @Test
    void  shouldSpeedInGetSpeed() {
        Horse lila = new Horse("Lila", 5.0, 1.0);
        assertEquals(5.0, lila.getSpeed());
    }

    @Test
    void shouldDistanceInGetDistance() {
        Horse lila = new Horse("Lila", 5.0, 1.0);
        assertEquals(1.0, lila.getDistance());
    }

    @Test
    void shouldDistanceZeroInGetDistance() {
        Horse lila = new Horse("Lila", 5.0);
        assertEquals(0.0, lila.getDistance());
    }

    @Test
    void shouldMethodGetRandomDoubleCalledWithMove() {
        try ( MockedStatic<Horse> mockStat = Mockito.mockStatic(Horse.class)) {
            Horse lila = new Horse("Lila", 5.0, 1.0);
            lila.move();
            mockStat.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5})
    void shouldReturnDistanceOnFormulaMove(double random) {
        try ( MockedStatic<Horse> mockStat = Mockito.mockStatic(Horse.class)) {
            Horse lila = new Horse("Lila", 5.0, 1.0);
            mockStat.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            lila.move();
            assertEquals(1.0 + 5.0 * random, lila.getDistance());
        }
    }
}
