import java.util.Scanner;

public class Duke {
    static private List taskList = new List();

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args){

        System.out.println("Hello! I'm TheChattyFatty");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                break;
            } else if (response.equals("list")) {
                taskList.show();
            } else {
                taskList.add(response);
            }
        }


        System.out.println("Bye. Hope to see you again soon!");
    }
}