package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    AbstractWorldMap map;
    Vector2d meetingPoint = new Vector2d(1, 1);
    @Test
    public void mapTest1(){
        map = new NotExtendedMap(10, 10, 0.2, 10, 10, 1);
        Animal erin = new Animal(map);
        Animal andy = new Animal(map);
        Animal gabe = new Animal(map);

        andy.setEnergy(5);
        gabe.setEnergy(2);

        map.place(erin);
        map.place(andy);
        map.place(gabe);

        erin.setPosition(meetingPoint);
        andy.setPosition(meetingPoint);
        gabe.setPosition(meetingPoint);

        map.eatPlant(meetingPoint);

        assertEquals(20, erin.getEnergy());
        assertEquals(5, andy.getEnergy());
        assertEquals(2, gabe.getEnergy());
    }

    @Test
    public void mapTest2(){
        map = new NotExtendedMap(10, 10, 0.2, 10, 10, 1);
        Animal erin = new Animal(map);
        Animal andy = new Animal(map);
        Animal gabe = new Animal(map);

        gabe.setEnergy(2);

        map.place(erin);
        map.place(andy);
        map.place(gabe);

        erin.setPosition(meetingPoint);
        andy.setPosition(meetingPoint);
        gabe.setPosition(meetingPoint);

        map.eatPlant(meetingPoint);

        assertEquals(15, erin.getEnergy());
        assertEquals(15, andy.getEnergy());
        assertEquals(2, gabe.getEnergy());


        erin.setEnergy(10);
        andy.setEnergy(10);
        gabe.setEnergy(10);

        map.eatPlant(meetingPoint);

        assertEquals(13, erin.getEnergy());
        assertEquals(13, andy.getEnergy());
        assertEquals(13, gabe.getEnergy());
    }

    @Test
    public void mapTest3(){
        map = new NotExtendedMap(10, 10, 0.2, 10, 10, 1);
        Animal erin = new Animal(map);
        Animal andy = new Animal(map);
        Animal gabe = new Animal(map);

        erin.setEnergy(16);
        andy.setEnergy(8);
        gabe.setEnergy(2);

        map.place(erin);
        map.place(andy);
        map.place(gabe);

        erin.setPosition(meetingPoint);
        andy.setPosition(meetingPoint);
        gabe.setPosition(meetingPoint);

        map.makeLittleAnimal(meetingPoint, 1);

        assertEquals(12, erin.getEnergy());
        assertEquals(6, andy.getEnergy());
        assertEquals(2, gabe.getEnergy());
    }
}
