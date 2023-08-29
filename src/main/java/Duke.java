import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        
        String name = "Lexi";
        final String horizontal_line = "--------------------------------------------";

        System.out.println(logo);

        System.out.println(horizontal_line);
        System.out.println("Hello! I'm " + name);
        System.out.println("How can I help you buddy?");
        System.out.println(horizontal_line);
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        do{
            System.out.println(horizontal_line);
            System.out.println(input);
            System.out.println(horizontal_line);
            input = in.nextLine();
    } while(!input.toLowerCase().equals("bye"));

        System.out.println(horizontal_line);
        System.out.println("Have a wonderful day! Hope to see you again soon!");
        System.out.println(horizontal_line);

    }
}
