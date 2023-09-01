import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class HippodromeTest {
    @Test
    void nullInConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void emptyListInConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void testReturnListHorsesFromGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippo = new Hippodrome(horses);
        assertEquals(horses, hippo.getHorses());
    }

    @Test
    void shouldCallingMoveAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippo = new Hippodrome(horses);
        hippo.move();
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void shouldReturnMaxDistance(){
        Horse horse1 = new Horse("Quick", 2.5, 2.4);
        Horse horse2 = new Horse("Rain", 2.1, 2.35);
        Horse horse3 = new Horse("Shade", 2.3, 2.3);
        Horse horse4 = new Horse("Zap", 2.6, 2.1);
        Hippodrome hippo = new Hippodrome(List.of(horse1, horse2, horse3, horse4));
        assertSame(horse1, hippo.getWinner());
    }
}
