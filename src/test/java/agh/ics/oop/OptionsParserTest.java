package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionsParserTest {

    @Test
    public void parseTest (){
        String[] args = new String[] {"forward", "f", "right", "r", "terefere"};
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args));

        String[] args1 = new String[] {"forward", "f", "Right", "r"};
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args1));

        String[] args2 = new String[] {"h", "f", "right", "r"};
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args2));

        String[] args3 = new String[] {"forward", "f", "right", "Left"};
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args3));

        String[] args4 = new String[] {"forward", "f", "right", "left", "r", "l", "backward", "b"};
        assertDoesNotThrow(() -> OptionsParser.parse(args4));
    }
}

