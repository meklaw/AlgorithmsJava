package leetcode.arrays101;

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
        return Arrays.stream(nums).map(i-> (int) Math.pow(i, 2)).sorted().toArray();
    }

}
