package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {
    AbstractWorldMap map;
    @Test
    public void animalTest1(){
        map = new NotExtendedMap(10, 10, 0.2, 10, 10, 1);
        Animal michael = new Animal(map);
        Animal dwight = new Animal(map);
        michael.exercise();
        assertEquals(9, michael.getEnergy());
        michael.exercise();
        assertEquals(8, michael.getEnergy());

        dwight.yummy(1);
        assertEquals(20, dwight.getEnergy());
        dwight.yummy(2);
        assertEquals(25, dwight.getEnergy());
    }

    @Test
    public void animalTest2(){
        map = new NotExtendedMap(10, 10, 0.2, 10, 10, 1);
        Animal angela = new Animal(map);
        Animal dwight = new Animal(map);

        dwight.setEnergy(16);
        angela.setEnergy(12);
        dwight.reproduce(angela);
        assertEquals(12, dwight.getEnergy());
        assertEquals(9, angela.getEnergy());

        angela.setEnergy(1);
        angela.exercise();
        assertTrue(angela.isAnimalDead());

        dwight.setEnergy(1);
        dwight.exercise();
        dwight.exercise();
        dwight.exercise();
        dwight.exercise();
        assertTrue(dwight.isAnimalDead());
    }

    @Test
    public void animalTest3(){
        map = new NotExtendedMap(10, 10, 0.2, 10, 10, 1);
        Animal angela = new Animal(map);

        angela.setOrient(MapDirection.NORTH);
        angela.turn(2);
        assertEquals(MapDirection.EAST, angela.getOrient());
        angela.turn(3);
        assertEquals(MapDirection.SOUTH_WEST, angela.getOrient());

    }
}
