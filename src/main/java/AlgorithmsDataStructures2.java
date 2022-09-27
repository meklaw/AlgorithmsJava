import java.util.Arrays;

public class AlgorithmsDataStructures2 {
    public static int[] GenerateBBSTArray(int[] a) {
        Arrays.sort(a);
        int[] resultBST = generateBinaryArray(a.length);
        arrayToBST(resultBST, 0, a, 0, a.length - 1);
        return resultBST;
    }

    protected static void arrayToBST(int[] aBST, int element, int[] fromArr, int start, int end) {
        int mid = (start + end) / 2;
        aBST[element] = fromArr[mid];
        if (start == end)
            return;


        int leftChild = element * 2 + 1;
        if (leftChild < aBST.length && mid != (start + mid - 1) / 2 && fromArr[(start + mid - 1) / 2] != aBST[0])
            arrayToBST(aBST, leftChild, fromArr, start, mid - 1);

        int rightChild = element * 2 + 2;
        if (rightChild < aBST.length && mid != (mid + 1 + end) / 2)
            arrayToBST(aBST, rightChild, fromArr, mid + 1, end);

    }

    protected static int[] generateBinaryArray(int length) {
        int newLength = 1;
        for (int pow = 0; length >= newLength; pow++) {
            newLength = (int) Math.pow(2, pow);
        }

        return new int[newLength - 1];
    }
}