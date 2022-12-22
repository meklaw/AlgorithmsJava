package tinkoff.student_algo;

import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        System.out.println(countFigures(n));
    }

    private static int countFigures(int n) {
        if (n % 3 == 0)
            return (int) Math.pow(2, n / 3.0);

        return 0;
    }
}
