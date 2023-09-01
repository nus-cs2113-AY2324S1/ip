import java.lang.String;
import java.lang.System;
import java.util.Scanner;

public class ListWhisper {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner s = new Scanner(System.in);
        System.out.println(
                "Hello! I'm IP\n" +
                "What can I do for you?"
                );
        while (true) {
            String input = s.nextLine();
            manager.process(input);
        }
    }
}