package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrassFieldTest {

    @Test
    public void testCanMoveTo(){
        GrassField field1 = new GrassField(6);
        Animal jim = new Animal(field1, new Vector2d(0,0));
        Animal dwight = new Animal(field1, new Vector2d(2,2));

        field1.place(jim);
        field1.place(dwight);

        assertTrue(field1.canMoveTo(new Vector2d(1,1)));
        assertTrue(field1.canMoveTo(new Vector2d(1,0)));
        assertTrue(field1.canMoveTo(new Vector2d(-1,-1)));
        assertTrue(field1.canMoveTo(new Vector2d(2,3)));

        assertFalse(field1.canMoveTo(new Vector2d(0,0)));
        assertFalse(field1.canMoveTo(new Vector2d(2,2)));

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
        GrassField field2 = new GrassField(5);
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

        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if(field2.objectAt(new Vector2d(i, j)) != null && !(field2.objectAt(new Vector2d(i, j))  instanceof Animal))
                    assertTrue(field2.objectAt(new Vector2d(i, j)) instanceof Tree);
            }
        }

        Angela.move(MoveDirection.FORWARD);

        assertTrue(field2.objectAt(new Vector2d(0,1)) instanceof Animal);
        assertFalse(field2.objectAt(new Vector2d(0,0)) instanceof Animal);
    }

    @Test
    public void testIsOcuppied(){
        GrassField field3 = new GrassField(10);
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

        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(field3.objectAt(new Vector2d(i,j)) == null)
                    assertFalse(field3.isOccupied(new Vector2d(i, j)));
                else if(field3.objectAt(new Vector2d(i ,j)) instanceof Tree)
                    assertTrue(field3.isOccupied(new Vector2d(i, j)));
            }

        }
    }

    @Test
    public void testToString(){
        GrassField field4 = new GrassField(10);
        String map1 = field4.toString();
        int grassCnt = 0;
        for (int i = 0; i < map1.length(); i++) {
            if(map1.charAt(i) == '*')
                grassCnt ++;
        }
        assertEquals(10, grassCnt);

        GrassField field5 = new GrassField(25);
        String map2 = field5.toString();
        grassCnt = 0;
        for (int i = 0; i < map2.length(); i++) {
            if(map2.charAt(i) == '*')
                grassCnt ++;
        }
        assertEquals(25, grassCnt);
    }

    @Test
    public void testFindingUpperCorner(){
        GrassField field7 = new GrassField(10);
        Animal Oscar = new Animal(field7, new Vector2d(100, 100));
        Animal Ryan = new Animal(field7, new Vector2d(160, 160));
        field7.place(Oscar);
        assertEquals(new Vector2d(100,100), field7.findingUpperCorner());
        field7.place(Ryan);
        assertEquals(new Vector2d(160,160), field7.findingUpperCorner());


        GrassField field8 = new GrassField(10);
        Animal Toby = new Animal(field8, new Vector2d(10, 10));
        field8.place(Toby);
        assertEquals(new Vector2d(10,10), field8.findingUpperCorner());
    }

    @Test
    public void testFindingLowerCorner(){
        GrassField field9 = new GrassField(10);
        Animal Andy = new Animal(field9, new Vector2d(-100, -100));
        Animal Michael = new Animal(field9, new Vector2d(-160, -160));
        field9.place(Andy);
        assertEquals(new Vector2d(-100,-100), field9.findingLowerCorner());
        field9.place(Michael);
        assertEquals(new Vector2d(-160,-160), field9.findingLowerCorner());


        GrassField field10 = new GrassField(10);
        Animal Jan = new Animal(field10, new Vector2d(-10, -10));
        field10.place(Jan);
        assertEquals(new Vector2d(-10,-10), field10.findingLowerCorner());
    }
}


