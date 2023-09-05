import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String intro = "Hello! I'm Wenny!\n"
                        + "How may I help you?\n";
        System.out.println(intro);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            System.out.println(userInput);
            if (userInput.equals("bye")) {
                bye();
            }
        }
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
