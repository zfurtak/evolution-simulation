package agh.ics.oop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d v1 = new Vector2d(-1,-1);
    Vector2d v2 = new Vector2d(-1,-1);
    Vector2d v3 = new Vector2d(1,1);
    Vector2d v4 = new Vector2d(-1,1);
    Vector2d v5 = new Vector2d(1,-1);
    @Test
    void testEquals(){
        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
        assertNotEquals(v1, v4);
        assertNotEquals(v1, v5);
    }
    @Test
    void testToString(){
        assertEquals("(1,1)", v3.to_String());
        assertEquals("(-1,-1)", v1.to_String());
        assertEquals("(1,-1)", v5.to_String());
    }
    @Test
    void testPrecedes(){
        assertTrue(v2.precedes(v3));
        assertTrue(v1.precedes(v3));
        assertFalse(v3.precedes(v2));
        assertFalse(v3.precedes(v1));
    }
    @Test
    void testFollows(){
        assertTrue(v3.follows(v2));
        assertTrue(v1.follows(v2));
        assertFalse(v2.follows(v3));
        assertFalse(v1.follows(v3));
    }
    @Test
    void testUpperRight(){
        assertEquals(v3, v4.upperRight(v5));
        assertEquals(v3, v3.upperRight(v1));
    }
    @Test
    void testLowerLeft(){
        assertEquals(v1, v4.lowerLeft(v5));
        assertEquals(v1, v1.lowerLeft(v3));
    }
    @Test
    void testAdd(){
        assertEquals(new Vector2d(-2,-2), v1.add(v2));
        assertEquals(new Vector2d(0,0), v4.add(v5));
        assertEquals(new Vector2d(2,0), v3.add(v5));
        assertEquals(new Vector2d(0,0), v1.add(v3));
    }
    @Test
    void testSubtract(){
        assertEquals(new Vector2d(0,0), v1.subtract(v2));
        assertEquals(new Vector2d(-2,-2), v1.subtract(v3));
        assertEquals(new Vector2d(-2,2), v4.subtract(v5));
        assertEquals(new Vector2d(0,2), v3.subtract(v5));
    }
    @Test
    void testOpposite(){
        assertEquals(v1, v3.opposite());
        assertEquals(v2, v3.opposite());
        assertEquals(v3, v2.opposite());
        assertEquals(v1.opposite(), v2.opposite());
        assertEquals(v4, v5.opposite());
        assertEquals(v5, v4.opposite());
    }


}
