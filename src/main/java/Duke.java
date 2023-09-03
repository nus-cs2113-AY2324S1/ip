import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        printLines(30,'-');
        System.out.println("Hello! I'm En\nWhat can i do for you?");
        printLines(30,'-');

        Scanner scanner = new Scanner(System.in);
        String userInput;
        List<String> userInputs = new ArrayList<>();
        int inputCount = 0;

        while (true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();
            printLines(30,'-');

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Have a nice day.");
                printLines(30,'-');
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printLines(30, '-');
                for (int i = 0; i < inputCount; i++) {
                    System.out.println((i + 1) + "." + userInputs.get(i));
                }
                printLines(30,'-');
            } else {
                inputCount ++;
                userInputs.add(userInput);
                System.out.println("Added:" + userInput);
                printLines(30,'-');
            }
        }
        scanner.close();
   }

   public static void printLines(int l, char c){
        for (int i = 0; i < l; i++){
            System.out.print(c);
        }
        System.out.println();
    }
}
