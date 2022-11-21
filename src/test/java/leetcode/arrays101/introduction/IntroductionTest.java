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

    @Test
    void sortedSquares2() {
        int[] squares = Introduction.sortedSquares2(new int[]{-4, -1, 0, 3, 10});
        int[] test = new int[]{0, 1, 9, 16, 100};
        for (int i = 0; i < squares.length; i++) {
            assertEquals(test[i], squares[i], Arrays.toString(squares));
        }

        squares = Introduction.sortedSquares2(new int[]{-7, -3, 2, 3, 11});
        test = new int[]{4, 9, 9, 49, 121};
        for (int i = 0; i < squares.length; i++) {
            assertEquals(test[i], squares[i], Arrays.toString(squares));
        }

        squares = Introduction.sortedSquares2(new int[]{-5, -3, -2, -1});
        test = new int[]{1, 4, 9, 25};
        for (int i = 0; i < squares.length; i++) {
            assertEquals(test[i], squares[i], Arrays.toString(squares));
        }

        squares = Introduction.sortedSquares2(new int[]{-5, -3, -2, -1, 4});
        test = new int[]{1, 4, 9, 16, 25};
        for (int i = 0; i < squares.length; i++) {
            assertEquals(test[i], squares[i], Arrays.toString(squares));
        }
    }

    @Test
    void duplicateZeros() {
        int[] input = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        int[] test = new int[]{1, 0, 0, 2, 3, 0, 0, 4};
        Introduction.duplicateZeros(input);
        for (int i = 0; i < input.length; i++) {
            assertEquals(test[i], input[i], Arrays.toString(input));
        }

        input = new int[]{1, 2, 3};
        test = new int[]{1, 2, 3};
        Introduction.duplicateZeros(input);
        for (int i = 0; i < input.length; i++) {
            assertEquals(test[i], input[i], Arrays.toString(input));
        }
    }

    @Test
    void merge() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        int m = 3;
        int n = 3;
        Introduction.merge(nums1, m, nums2, n);
        int[] test = new int[]{1, 2, 2, 3, 5, 6};
        for (int i = 0; i < nums1.length; i++) {
            assertEquals(test[i], nums1[i], Arrays.toString(nums1));
        }

        nums1 = new int[]{1, 2, 3, 0, 0, 0, 0, 0};
        nums2 = new int[]{2, 2, 2, 5, 6};
        m = 3;
        n = 5;
        Introduction.merge(nums1, m, nums2, n);
        test = new int[]{1, 2, 2, 2, 2, 3, 5, 6};
        for (int i = 0; i < nums1.length; i++) {
            assertEquals(test[i], nums1[i], Arrays.toString(nums1));
        }

        nums1 = new int[]{1};
        nums2 = new int[]{};
        m = 1;
        n = 0;
        Introduction.merge(nums1, m, nums2, n);
        test = new int[]{1};
        for (int i = 0; i < nums1.length; i++) {
            assertEquals(test[i], nums1[i], Arrays.toString(nums1));
        }

        nums1 = new int[]{0};
        nums2 = new int[]{1};
        m = 0;
        n = 1;
        Introduction.merge(nums1, m, nums2, n);
        test = new int[]{1};
        for (int i = 0; i < nums1.length; i++) {
            assertEquals(test[i], nums1[i], Arrays.toString(nums1));
        }
        nums1 = new int[]{10, 20, 30, 0, 0, 0};
        nums2 = new int[]{12, 14, 16};
        m = 3;
        n = 3;
        Introduction.merge(nums1, m, nums2, n);
        test = new int[]{10, 12, 14, 16, 20, 30};
        for (int i = 0; i < nums1.length; i++) {
            assertEquals(test[i], nums1[i], Arrays.toString(nums1));
        }


    }
}