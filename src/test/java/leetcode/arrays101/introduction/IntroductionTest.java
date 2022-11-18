package leetcode.arrays101.introduction;

import leetcode.arrays101.Introduction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntroductionTest {

    @Test
    void findMaxConsecutiveOnes() {
        assertEquals(Introduction.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}), 3);
        assertEquals(Introduction.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}), 2);
    }

    @Test
    void findNumbers() {
        assertEquals(Introduction.findNumbers(new int[]{12, 345, 2, 6, 7896}), 2);
        assertEquals(Introduction.findNumbers(new int[]{555, 901, 482, 1771}), 1);
    }

    @Test
    void sortedSquares() {
        int[] squares = Introduction.sortedSquares(new int[]{-4, -1, 0, 3, 10});
        int[] test = new int[]{0, 1, 9, 16, 100};
        for (int i = 0; i < squares.length; i++) {
            assertEquals(test[i], squares[i], Arrays.toString(squares));
        }

        squares = Introduction.sortedSquares(new int[]{-7, -3, 2, 3, 11});
        test = new int[]{4, 9, 9, 49, 121};
        for (int i = 0; i < squares.length; i++) {
            assertEquals(test[i], squares[i], Arrays.toString(squares));
        }

        squares = Introduction.sortedSquares(new int[]{-5, -3, -2, -1});
        test = new int[]{1, 4, 9, 25};
        for (int i = 0; i < squares.length; i++) {
            assertEquals(test[i], squares[i], Arrays.toString(squares));
        }

        squares = Introduction.sortedSquares(new int[]{-5, -3, -2, -1, 4});
        test = new int[]{1, 4, 9, 16, 25};
        for (int i = 0; i < squares.length; i++) {
            assertEquals(test[i], squares[i], Arrays.toString(squares));
        }
    }
}