package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenomeTest {
    AbstractWorldMap map = new NotExtendedMap(10, 10, 0.2, 10, 10, 1);

    //testing if baby's genome is generated properly

    @Test
    public void genomeTest1() {
        Animal mum = new Animal(map);
        Animal dad = new Animal(map);
        mum.setEnergy(5);
        mum.getGenome().genomeArray = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7};
        dad.getGenome().genomeArray = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 6, 6, 6, 6, 7, 7, 7, 7, 7};
        Animal baby = new Animal(map, 3, mum, dad, 1);
        int[] babyGenome = baby.getGenome().genomeArray;
        int[] mumGenome = mum.getGenome().genomeArray;
        int[] dadGenome = dad.getGenome().genomeArray;

        assertTrue((Arrays.equals(babyGenome, 0, 20, dadGenome, 0, 20)
                || (Arrays.equals(babyGenome, 11, 31, dadGenome, 11, 31))));
        assertFalse((Arrays.equals(babyGenome, 0, 20, mumGenome, 0, 20)
                && (Arrays.equals(babyGenome, 10, 31, mumGenome, 10, 31))));
    }


    @Test
    public void genomeTest2() {
        Animal mum = new Animal(map);
        Animal dad = new Animal(map);
        dad.setEnergy(8);
        mum.getGenome().genomeArray = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7};
        dad.getGenome().genomeArray = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 6, 6, 6, 6, 7, 7, 7, 7, 7};
        Animal baby = new Animal(map, 4, mum, dad, 1);

        int[] babyGenome = baby.getGenome().genomeArray;
        int[] mumGenome = mum.getGenome().genomeArray;
        int[] dadGenome = dad.getGenome().genomeArray;

        assertTrue((Arrays.equals(babyGenome, 0, 16, mumGenome, 0, 16)
                || (Arrays.equals(babyGenome, 14, 31, mumGenome, 14, 31))));
        assertFalse((Arrays.equals(babyGenome, 0, 16, dadGenome, 0, 16)
                && (Arrays.equals(babyGenome, 14, 31, dadGenome, 14, 31))));

    }

}
