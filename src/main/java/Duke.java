import java.util.Scanner;

public class Duke {
    public static void printList(String[] list, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("\t" + (i + 1) + ": " + list[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("\tHello! I'm RC\n\tWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String line = "";
        String[] list = new String[100];
        int count = 0;

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                printList(list, count);
            } else {
                System.out.println("\tadded: " + line);
                list[count] = line;
                count++;
            }
        }
        System.out.println("\tBye. Hope to see you again soon!\n");
    }
}
