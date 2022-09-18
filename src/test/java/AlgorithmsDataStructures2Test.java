import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgorithmsDataStructures2Test {

    protected int[] abs1 = {50, 25, 75, 20, 37, 62, 84};
    protected int[] abs2 = {50, 25, 75, 20, 37, 84};
    protected int[] abs3 = {50, 25, 75, 20, 84};
    protected int[] abs4 = {50};
    protected int[] abs5 = {50, 75};

    @Test
    void generateBBSTArray() {
        int[] generateBBSTArray1 = AlgorithmsDataStructures2.GenerateBBSTArray(abs1);
        int[] generateBBSTArray2 = AlgorithmsDataStructures2.GenerateBBSTArray(abs2);
        int[] generateBBSTArray3 = AlgorithmsDataStructures2.GenerateBBSTArray(abs3);
        int[] generateBBSTArray4 = AlgorithmsDataStructures2.GenerateBBSTArray(abs4);
        int[] generateBBSTArray5 = AlgorithmsDataStructures2.GenerateBBSTArray(abs5);

        assertEquals(generateBBSTArray1[0], 50);
        assertEquals(generateBBSTArray1[1], 25);
        assertEquals(generateBBSTArray1[2], 75);
        assertEquals(generateBBSTArray1[3], 20);
        assertEquals(generateBBSTArray1[4], 37);
        assertEquals(generateBBSTArray1[5], 62);
        assertEquals(generateBBSTArray1[6], 84);
        assertEquals(generateBBSTArray3[0], 50);
        assertEquals(generateBBSTArray3[1], 20);
        assertEquals(generateBBSTArray3[2], 75);
        assertEquals(generateBBSTArray3[3], 0);
        assertEquals(generateBBSTArray3[4], 25);
        assertEquals(generateBBSTArray3[5], 0);
        assertEquals(generateBBSTArray3[6], 84);

        assertEquals(generateBBSTArray4[0], 50);

        assertEquals(generateBBSTArray5[0], 50);
        assertEquals(generateBBSTArray5[1], 0);
        assertEquals(generateBBSTArray5[2], 75);

    }

    @Test
    void generateBinaryArray() {
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(abs1.length).length, 7);
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(abs2.length).length, 7);
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(abs3.length).length, 7);
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(0).length, 0);
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(1).length, 1);
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(2).length, 3);
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(3).length, 3);
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(4).length, 7);
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(15).length, 15);
        assertEquals(AlgorithmsDataStructures2.generateBinaryArray(16).length, 31);

    }
}