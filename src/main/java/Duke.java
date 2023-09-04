import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        partition();
        String name = " _____ _                       _     _       _         \n" +
                "/  __ \\ |                     | |   | |     | |        \n" +
                "| /  \\/ |__   ___  ___ _______| |__ | | ___ | | __ ____\n" +
                "| |   | '_ \\ / _ \\/ _ \\_  / _ \\ '_ \\| |/ _ \\| |/ /|_  /\n" +
                "| \\__/\\ | | |  __/  __// /  __/ |_) | | (_) |   <  / / \n" +
                " \\____/_| |_|\\___|\\___/___\\___|_.__/|_|\\___/|_|\\_\\/___|";

        System.out.print("Hello! My name is:\n" + name + "\n\n" +
                "What can I do for you today? :)\n" + ">>");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while(!userInput.equalsIgnoreCase("bye"))
        {
            System.out.println(userInput);
            partition();

            System.out.print("What do you want to do next? :o\n" + ">>");
            userInput = scanner.nextLine();
        }
        System.out.println(" Bye. Hope to see you again soon! :D");
        partition();
    }

    private static void partition(){
        System.out.println("____________________________________________________________");
    }
}
