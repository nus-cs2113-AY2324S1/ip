import java.util.Scanner;

public class Duke {
    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm TheChattyFatty");
        System.out.println("What can I do for you?");
        printLine();

        Scanner scanner = new Scanner(System.in);
        while(true){
            String response = scanner.nextLine();

            if(response.equals("bye")){
                break;
            }

            printLine();
            System.out.println(response);
            printLine();
        }


        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}