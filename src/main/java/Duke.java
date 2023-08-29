import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);

    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static void printUserInput(String output) {
        if(output.toLowerCase().equals("bye")){
            output = " Bye. Hope to see you again soon! ";
        }
        System.out.println("____________________________________________________________\n" +
                " " + output + "\n" +
                "____________________________________________________________\n");
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static void main(String[] args) {

        String logo = "\n" +
        "     ____.  _____ ______________   ____.___  _________\n" +
        "    |    | /  _  \\\\______   \\   \\ /   /|   |/   _____/\n" +
        "    |    |/  /_\\  \\|       _/\\   Y   / |   |\\_____  \\ \n" +
        "/\\__|    /    |    \\    |   \\ \\     /  |   |/        \\\n" +
        "\\________\\____|__  /____|_  /  \\___/   |___/_______  /\n" +
        "                 \\/       \\/                       \\/ \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm JARVIS \n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n" );

        String userInput;
        do{
            userInput = getUserInput();
            printUserInput(userInput);
        }while(!userInput.equals("bye"));

        closeScanner();
    }
}
