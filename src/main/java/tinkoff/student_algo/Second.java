package tinkoff.student_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int previous = Integer.parseInt(reader.readLine());

        System.out.print(previous + " ");

        for (int i = 1; i < n; i++) {
            int speed = Integer.parseInt(reader.readLine());

            if (previous < speed) {
                speed = previous;
            }

            System.out.print(speed + " ");
            previous = speed;
        }

        reader.close();
    }
}
