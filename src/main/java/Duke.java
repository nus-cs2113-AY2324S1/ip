import java.util.Scanner;

public class Duke {
    static String chatbotName="Andrew Tate";

    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true){
            String commandGiven = myScanner.nextLine();
            if (commandGiven.equals("bye")) {
                break;
            }else{
                System.out.println("____________________________________________________________");
                System.out.println(commandGiven);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}


