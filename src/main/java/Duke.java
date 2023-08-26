import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Duke {
    private static boolean ended = false;
    private final static Scanner scanner = new Scanner(System.in);
    private final static HashMap<String, Function<Void, Void>> commands = new HashMap<>();

    public static void main(String[] args) {
        commands.put("bye", (e) -> bye());
        greet();
        while (!ended) {
            System.out.print("User: ");
            String userInput = getInput();
            Function<Void, Void> command = commands.getOrDefault(userInput, (e) -> printWrapped(userInput));
            command.apply(null);
        }
    }

    public static String getInput() {
        return scanner.nextLine();
    }

    public static Void printWrapped(String printOut) {
        ProgramConstants.printSeparator();
        System.out.println(printOut);
        ProgramConstants.printSeparator();
        return null;
    }

    public static void greet() {
        String greeting = String.format("Hello I'm %s\n", ProgramConstants.BOT_NAME) +
                "What can I do for you?";
        printWrapped(greeting);
    }

    public static Void bye() {
        printWrapped("Bye. Hope to see you again soon!");
        ended = true;
        return null;
    }
}
