import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Rias-chan!");
        System.out.println("Welcome back goshujin-sama, what can I do for you?");
        waitForResponse();
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void waitForResponse() {
        Scanner scanner = new Scanner(System.in);
        String response = "";
        do {
            response = scanner.nextLine();
            if (!"bye".equalsIgnoreCase(response)) {
                process(response);
            }
        } while (!"bye".equalsIgnoreCase(response));

        scanner.close();
    }
    public static void process(String response) {
        System.out.println(response);
    }
}
