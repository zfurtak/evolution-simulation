package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineTest {
    private final AbstractWorldMap map = new RectangularMap(5, 5);

    @Test
    public void engineTest1() {
        String[] args = new String[] {"forward", "f", "right", "r", "left"};
        MoveDirection[] directions = OptionsParser.parse(args);
        Vector2d[] positions1 = new Vector2d[] {new Vector2d(0,0), new Vector2d(1,1) };
        SimulationEngine engine = new SimulationEngine(this.map, positions1, 100);
        engine.getMoves(directions);
        Animal animal1 = (Animal) this.map.objectAt(new Vector2d(0,0));
        Animal animal2 = (Animal) this.map.objectAt(new Vector2d(1,1));
        engine.run();
        assertEquals(MapDirection.NORTH, animal1.getOrient());
        assertEquals(MapDirection.EAST, animal2.getOrient());
        assertEquals(new Vector2d(0,1), animal1.getPosition());
        assertEquals(new Vector2d(1,2), animal2.getPosition());
    }

    @Test
    public void engineTest2() {
        String[] args = new String[] {"f", "forward", "right", "l", "r", "right"};
        MoveDirection[] directions = OptionsParser.parse(args);
        Vector2d[] positions2 = new Vector2d[] {new Vector2d(0,0), new Vector2d(1,1), new Vector2d(2,2) };
        SimulationEngine engine = new SimulationEngine(this.map, positions2, 100);
        engine.getMoves(directions);
        Animal animal1 = (Animal) this.map.objectAt(new Vector2d(0,0));
        Animal animal2 = (Animal) this.map.objectAt(new Vector2d(1,1));
        Animal animal3 = (Animal) this.map.objectAt(new Vector2d(2,2));
        engine.run();
        assertEquals(MapDirection.SOUTH, animal3.getOrient());
        assertEquals(MapDirection.EAST, animal2.getOrient());
        assertEquals(MapDirection.WEST, animal1.getOrient());
        assertEquals(new Vector2d(0,1), animal1.getPosition());
        assertEquals(new Vector2d(1,2), animal2.getPosition());
    }

    @Test
    public void engineTest3() {
        String[] args = new String[] {"f", "forward", "f", "l", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        Vector2d[] positions3 = new Vector2d[] {new Vector2d(0,0), new Vector2d(0,1) };
        SimulationEngine engine = new SimulationEngine(this.map, positions3, 100);
        engine.getMoves(directions);
        Animal animal1 = (Animal) this.map.objectAt(new Vector2d(0,0));
        Animal animal2 = (Animal) this.map.objectAt(new Vector2d(0,1));
        engine.run();
        assertEquals(new Vector2d(0,1), animal1.getPosition());
        assertEquals(new Vector2d(0,2), animal2.getPosition());
        assertEquals(MapDirection.WEST, animal2.getOrient());
    }
}


