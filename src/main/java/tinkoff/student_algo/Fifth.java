package tinkoff.student_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Fifth {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        reader.close();

        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += i;
            list.add(i);
        }

        if (sum % 3 != 0) {
            System.out.println(-1);
            return;
        }

        findGroups(list, sum / 3);
    }

    private static void findGroups(List<Integer> allValues, int medium) {
        for (int i = 0; i < 3; i++) {
            List<Integer> group = new ArrayList<>();
            int index = allValues.size() - 1;
            int sum = 0;

            while (sum != medium) {
                int val = allValues.get(index);

                if (sum + val <= medium) {
                    group.add(val);
                    sum += val;
                    allValues.remove(index);
                }

                index--;
            }
            System.out.println(group.size());
            group.forEach(integer -> System.out.print(integer + " "));
            System.out.println();
            System.out.println();
        }
    }
}
