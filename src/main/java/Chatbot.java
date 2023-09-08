import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        System.out.println("Hello I'm Rias-chan!");
        System.out.println("Welcome back goshujin-sama, what can I do for you?");
        ResponseProcessor processor = new ResponseProcessor();
        waitForResponse(processor);
        System.out.println("Bye masta!");
    }
    public static void waitForResponse(ResponseProcessor processor) {
        Scanner scanner = new Scanner(System.in);
        String response = "";
        do {
            response = scanner.nextLine();
            if (!"bye".equalsIgnoreCase(response)) {
                processor.process(response);
            }
        } while (!"bye".equalsIgnoreCase(response));

        scanner.close();
    }
}
