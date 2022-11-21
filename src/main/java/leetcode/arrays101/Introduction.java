package leetcode.arrays101;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Introduction {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;

        for (int i : nums) {
            if (i == 1) {
                count++;
                continue;
            }
            if (count > max) {
                max = count;
            }
            count = 0;
        }

        return Math.max(max, count);
    }


    public static int findNumbers(int[] nums) {
        return (int) Arrays.stream(nums).filter(i -> ((int) Math.log10(i) + 1) % 2 == 0).count();
    }

    public static int[] sortedSquares(int[] nums) {
        int[] answer = new int[nums.length];
        int pointA = 0;
        int pointB = nums.length - 1;
        int a;
        int b;

        for (int i = nums.length - 1; i >= 0; i--) {
            a = (int) Math.pow(nums[pointA], 2);
            b = (int) Math.pow(nums[pointB], 2);

            if (a > b) {
                answer[i] = a;
                pointA++;
                continue;
            }

            answer[i] = b;
            pointB--;
        }

        return answer;
    }

    public static int[] sortedSquares2(int[] nums) {
        return Arrays.stream(nums).map(i -> (int) Math.pow(i, 2)).sorted().toArray();
    }

    public static void duplicateZeros(int[] arr) {
        ArrayDeque<Integer> nextQue = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) nextQue.addLast(0);
            Integer add = nextQue.pollFirst();
            if (add != null) {
                nextQue.addLast(arr[i]);
                arr[i] = add;
            }
        }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int firstPoint = m - 1;
        int secondPoint = n - 1;
        for (int i = nums1.length - 1; i >= 0; i--) {
            if (firstPoint >= 0 && secondPoint < 0){
                nums1[i] = nums1[firstPoint];
                firstPoint--;
                continue;
            }
            if (secondPoint >= 0 && firstPoint < 0){
                nums1[i] = nums2[secondPoint];
                secondPoint--;
                continue;
            }
            if (nums1[firstPoint] > nums2[secondPoint]){
                nums1[i] = nums1[firstPoint];
                firstPoint--;
                continue;
            }
            nums1[i] = nums2[secondPoint];
            secondPoint--;
        }
    }

}
