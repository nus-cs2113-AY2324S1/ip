import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int numOfTasks = 0;

        String greetingMsg = "____________________________________________________________\n" +
                " Hello! I'm Chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMsg = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(greetingMsg);


        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(!input.equals("bye")) {
            if( input.equals("list") ) {
                System.out.println("____________________________________________________________");
                for(int i=0; i<numOfTasks; i++){
                    System.out.println( (i+1) + ". " + tasks[i] );
                }
                System.out.println("____________________________________________________________");

            } else {
                // if not bye, list, then the command by default is add
                tasks[numOfTasks] = input;
                numOfTasks++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }

            input = in.nextLine();
        }
        System.out.println(byeMsg);

    }
}
