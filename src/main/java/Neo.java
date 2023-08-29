import java.util.Scanner;
public class Neo {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Neo.");
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String[] list = new String[100];
        int listCount = 0;
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                for (int i = 0; i < listCount; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
                line = in.nextLine();
            }
            else {
                list[listCount] = line;
                listCount++;
                System.out.println("added: " + line);
                line = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
