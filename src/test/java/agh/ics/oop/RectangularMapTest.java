package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangularMapTest {

    @Test
    public void testCanMoveTo(){
        RectangularMap field1 = new RectangularMap(10, 10);
        Animal jim = new Animal(field1, new Vector2d(0,0));
        Animal dwight = new Animal(field1, new Vector2d(2,2));

        field1.place(jim);
        field1.place(dwight);

        assertTrue(field1.canMoveTo(new Vector2d(1,1)));
        assertTrue(field1.canMoveTo(new Vector2d(1,0)));
        assertTrue(field1.canMoveTo(new Vector2d(2,3)));

        //wchodzenie na drugiego zwierzaka
        assertFalse(field1.canMoveTo(new Vector2d(0,0)));
        assertFalse(field1.canMoveTo(new Vector2d(2,2)));

        //wychodzenie poza mapę
        assertFalse(field1.canMoveTo(new Vector2d(-1,-1)));
        assertFalse(field1.canMoveTo(new Vector2d(15,21)));
        assertFalse(field1.canMoveTo(new Vector2d(20,20)));

        jim.move(MoveDirection.FORWARD);
        jim.move(MoveDirection.FORWARD);
        jim.move(MoveDirection.RIGHT);
        jim.move(MoveDirection.FORWARD);

        assertFalse(field1.canMoveTo(new Vector2d(1,2)));
        assertTrue(field1.canMoveTo(new Vector2d(0,0)));

        dwight.move(MoveDirection.BACKWARD);

        assertFalse(field1.canMoveTo(new Vector2d(2,1)));
        assertTrue(field1.canMoveTo(new Vector2d(2,2)));
    }

    @Test
    public void testObjectAt(){
        RectangularMap field2 = new RectangularMap(10, 10);
        Animal Angela = new Animal(field2, new Vector2d(0, 0));
        Animal Kevin = new Animal(field2, new Vector2d(1,1));
        field2.place(Angela);
        field2.place(Kevin);

        assertEquals(Angela, field2.objectAt(new Vector2d(0, 0)));
        assertEquals(Kevin, field2.objectAt(new Vector2d(1, 1)));

        assertTrue(field2.objectAt(new Vector2d(0,0)) instanceof Animal);
        assertTrue(field2.objectAt(new Vector2d(1,1)) instanceof Animal);

        assertFalse(field2.objectAt(new Vector2d(4,8)) instanceof Animal);
        assertFalse(field2.objectAt(new Vector2d(3,6)) instanceof Animal);


        Angela.move(MoveDirection.FORWARD);

        assertTrue(field2.objectAt(new Vector2d(0,1)) instanceof Animal);
        assertFalse(field2.objectAt(new Vector2d(0,0)) instanceof Animal);

        assertNull(field2.objectAt(new Vector2d(5,5 )));
        assertNull(field2.objectAt(new Vector2d(6,6 )));
        assertNull(field2.objectAt(new Vector2d(-1,-1 )));
    }

    @Test
    public void testIsOcuppied(){
        RectangularMap field3 = new RectangularMap(10, 10);
        Animal Phyllis = new Animal(field3, new Vector2d(0, 0));
        Animal Pam = new Animal(field3, new Vector2d(5, 5));
        field3.place(Phyllis);
        field3.place(Pam);

        assertTrue(field3.isOccupied(Phyllis.getPosition()));
        assertTrue(field3.isOccupied(Pam.getPosition()));
        assertTrue(field3.isOccupied(new Vector2d(0, 0)));
        assertTrue(field3.isOccupied(new Vector2d(5, 5)));

        Phyllis.move(MoveDirection.FORWARD);
        Pam.move(MoveDirection.BACKWARD);

        assertTrue(field3.isOccupied(Phyllis.getPosition()));
        assertTrue(field3.isOccupied(Pam.getPosition()));
        assertTrue(field3.isOccupied(new Vector2d(0, 1)));
        assertTrue(field3.isOccupied(new Vector2d(5, 4)));
    }

    @Test
    public void testToString(){
        RectangularMap field6 = new RectangularMap(11, 11);
        Animal Bob = new Animal(field6, new Vector2d(1, 1));
        Animal Meredith = new Animal(field6, new Vector2d(2, 2));
        Animal Creed = new Animal(field6, new Vector2d(3,3 ));
        field6.place(Bob);
        field6.place(Meredith);
        field6.place(Creed);

        String map1 = field6.toString();
        int animalCount = 0;
        for(int i = 0; i <map1.length(); i++){
                if(map1.charAt(i) == 'N' || map1.charAt(i) == 'S' || map1.charAt(i) == 'E' || map1.charAt(i) == 'W')
                    animalCount ++;
        }
        assertEquals(3, animalCount);
    }

    @Test
    public void testPlace(){
        RectangularMap field5 = new RectangularMap(11, 11);
        Animal Bob = new Animal(field5, new Vector2d(1, 1));
        Animal Meredith = new Animal(field5, new Vector2d(2, 2));
        Animal Creed = new Animal(field5, new Vector2d(2,2 ));
        Animal Erin = new Animal(field5, new Vector2d(1,1 ));
        field5.place(Bob);
        assertTrue(Bob.isAt(new Vector2d(1, 1)));

        field5.place(Meredith);
        assertTrue(Meredith.isAt(new Vector2d(2, 2)));

        //sprawdzam czy da się położyć zwierzaka na innym
        assertThrows(IllegalArgumentException.class, () -> field5.place(Creed));
        assertThrows(IllegalArgumentException.class, () -> field5.place(Erin));
        

    }

    @Test
    public void testFindingUpperCorner(){
        RectangularMap field7 = new RectangularMap(11, 11);
        assertEquals(field7.findingUpperCorner(), new Vector2d(10,10));

        RectangularMap field8 = new RectangularMap(67, 67);
        assertEquals(field8.findingUpperCorner(), new Vector2d(66,66));
    }

    @Test
    public void testFindingLowerCorner(){
        RectangularMap field9 = new RectangularMap(11, 11);
        assertEquals(field9.findingLowerCorner(), new Vector2d(0,0));

    }
}



