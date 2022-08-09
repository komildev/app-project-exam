package pdp.com.appprojectexam.payload;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("3 ta son kiriting");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (a < b & b < c) {
            System.out.println(a * 2);
        } else {
            System.out.println(-a);
        }
    }
}
