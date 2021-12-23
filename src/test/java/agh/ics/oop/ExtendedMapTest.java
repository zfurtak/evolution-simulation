package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtendedMapTest {

    @Test
    public void extendedMapTest(){
        AbstractWorldMap map = new ExtendedMap(10, 10, 0.2, 10, 10, 1);
        Animal jim = new Animal(map);
        Animal pam = new Animal(map);
        jim.setPosition(new Vector2d(0, 0));
        jim.setOrient(MapDirection.SOUTH_WEST);
        pam.setPosition(new Vector2d(9, 5));
        pam.setOrient(MapDirection.EAST);

        jim.move(0);
        pam.move(0);
        assertEquals(jim.getPosition(), new Vector2d(9,9));
        assertEquals(pam.getPosition(), new Vector2d(0,5));


        jim.setPosition(new Vector2d(0, 9));
        pam.setPosition(new Vector2d(7, 9));
        jim.setOrient(MapDirection.SOUTH_EAST);
        pam.setOrient(MapDirection.SOUTH);

        jim.move(4);
        pam.move(4);
        assertEquals(jim.getPosition(), new Vector2d(9,0));
        assertEquals(pam.getPosition(), new Vector2d(7,0));

    }
}
