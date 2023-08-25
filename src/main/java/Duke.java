import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void printHorizontalLines(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void printList(List<String> list, int length){
        if (list.isEmpty()) {
            printHorizontalLines(length);
            System.out.println("List is empty. Enter a text to add to the list.");
            printHorizontalLines(length);
            return;
        } else {
            printHorizontalLines(length);
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
            printHorizontalLines(length);
        }
    }
    public static void main(String[] args) {
        int length = 50;
        //String[] textList = new String[100];
        List<String> textList = new ArrayList<>();

        printHorizontalLines(length);
        System.out.println("Hi! I'm Joshua");
        System.out.println("What can I do for you?");
        System.out.println();
        System.out.println("To exit, enter 'bye'. To view list, enter 'list'");
        printHorizontalLines(length);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                printHorizontalLines(length);
                System.out.println("Bye! See you soon.");
                printHorizontalLines(length);
                scanner.close();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printList(textList, length);
            } else {
                textList.add(input);
                printHorizontalLines(length);
                System.out.println("added: " + input);
                printHorizontalLines(length);
            }
        }
    }
}
