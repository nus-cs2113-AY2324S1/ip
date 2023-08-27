import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm LinguoBot");
        System.out.println("What can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);

        String[] list;
        list = new String[100];
        int itemCount = 0;

        while (true) {
            line = in.nextLine();
            if (line.equals("list")) {
                System.out.println("-------------------------");
                for (int i = 0 ; i<itemCount; i++) {
                    System.out.println(i + 1 + "." + list[i]);
                }
                System.out.println("-------------------------");
            }

            else if (line.equals("bye")) {
                System.out.println("-------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------");
                break;
            }

            else {
                System.out.println("-------------------------");
                System.out.println("added: " + line);
                System.out.println("-------------------------");
                list[itemCount] = line;
                itemCount ++;
            }
        }
    }
}