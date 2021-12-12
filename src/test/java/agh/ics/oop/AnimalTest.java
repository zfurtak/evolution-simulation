package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    private final AbstractWorldMap map = new RectangularMap(5, 5);
    Animal Bob = new Animal(map, new Vector2d(1,1));
    Animal Mike = new Animal(map, new Vector2d(2,2));

    @Test
    void everythingTest(){
        String[] lista = new String[] {"f", "forward", "right", "r"};
        MoveDirection[] kierunki = new OptionsParser().parse(lista);
        for(MoveDirection arg : kierunki){Bob.move(arg);}
        assertEquals(new Vector2d(1,3), Bob.getPosition());
        assertEquals(MapDirection.SOUTH, Bob.getOrient());

        String[] lista1 = new String[] {"b", "backward", "l" };
        MoveDirection[] kierunki1 = new OptionsParser().parse(lista1);
        for(MoveDirection arg : kierunki1){Mike.move(arg);}
        assertEquals(new Vector2d(2,0), Mike.getPosition());
        assertEquals(MapDirection.WEST, Mike.getOrient());

    }
}
