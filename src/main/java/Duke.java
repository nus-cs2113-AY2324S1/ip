import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        // Greetings
        String intro = "Hello! I'm Wenny!\n"
                        + "How may I help you?\n";
        System.out.println(intro);
        List list = new List();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                bye();
            } else if (userInput.equals("list")) {
                list.print_list();
            } else {
                list.add_to_list(userInput);
            }
        }
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
