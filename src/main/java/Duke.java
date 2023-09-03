import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printLines(30,'-');
        System.out.println("Hello! I'm En\nWhat can i do for you?");
        printLines(30,'-');

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();
            printLines(30,'-');

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Have a nice day.");
                printLines(30,'-');
                break;
            } else {
                System.out.println("En: " + userInput);
                printLines(30,'-');
            }
        }
   }
    public static void printLines(int l, char c){
        for (int i = 0; i < l; i++){
            System.out.print(c);
        }
        System.out.println();
    }
}
