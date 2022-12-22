package tinkoff.student_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Third {

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String input = reader.readLine();
//
        boolean[][] field = new boolean[4][4];
//
//
//        IntStream.range(0, input.length()).forEach(i -> {
//            inputToField(field, input.charAt(i));
//            clearField(field);
//        });
//
//        reader.close();
        for (int i = 0; i < 100000; i++) {
            if (Math.random()* 10 > 5)
                inputTypeZero(field);
            else
                inputTypeOne(field);
            clearField(field);
        }
    }

    private static void inputToField(boolean[][] field, char type) {
        if (type == '0') inputTypeZero(field);
        if (type == '1') inputTypeOne(field);
    }

    private static void inputTypeOne(boolean[][] field) {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[0].length - 1; col++) {
                if (!field[row][col] && !field[row][col + 1]) {
                    field[row][col] = true;
                    field[row][col + 1] = true;
                    System.out.printf("%d %d\n", row + 1, col + 1);
                    return;
                }
            }
        }
        throw new RuntimeException();

    }

    private static void inputTypeZero(boolean[][] field) {
        for (int row = 0; row < field.length - 1; row++) {
            for (int col = 0; col < field[0].length; col++) {
                if (!field[row][col] && !field[row + 1][col]) {
                    field[row][col] = true;
                    field[row + 1][col] = true;
                    System.out.printf("%d %d\n", row + 1, col + 1);
                    return;
                }
            }
        }
        throw new RuntimeException();
    }

    private static void clearField(boolean[][] field) {
        List<Integer> rowsToClear = new ArrayList<>();
        List<Integer> colsToClear = new ArrayList<>();

        for (int row = 0; row < field.length; row++) {
            boolean isNeedClearRow = true;
            boolean isNeedClearCol = true;

            for (int col = 0; col < field[0].length; col++) {
                if (!field[row][col]) {
                    isNeedClearRow = false;
                }
                if (!field[col][row]) {
                    isNeedClearCol = false;
                }
            }

            if (isNeedClearRow) rowsToClear.add(row);

            if (isNeedClearCol) colsToClear.add(row);
        }

        colsToClear.forEach(col -> clearCol(field, col));
        rowsToClear.forEach(row -> clearRow(field, row));
    }

    private static void clearRow(boolean[][] field, int row) {
        for (int col = 0; col < field[0].length; col++) {
            field[row][col] = false;
        }
    }

    private static void clearCol(boolean[][] field, int col) {
        for (int row = 0; row < field.length; row++) {
            field[row][col] = false;
        }
    }

    private static void printField(boolean[][] field) {
        for (boolean[] booleans : field) {
            System.out.println(Arrays.toString(booleans));
        }
    }
}
