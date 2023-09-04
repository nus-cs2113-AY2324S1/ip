import java.util.Scanner;

public class Alice {

    /**
     *
     * Records down task from user input.
     * User is able to mark and unmark tasks, and also list all the tasks.
     *
     */
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String helloMessage = " Hi there, I'm Alice, a chat bot!\n What can I do for you?\n";
        Task[] tasks = new Task[100];
        Task newTask;
        int numberOfTask = 0;
        int taskNumber;
        Scanner in = new Scanner(System.in);
        System.out.println(helloMessage + line);
        String userInput = in.nextLine();
        while (!(userInput.equals("bye"))){
            if (userInput.equals("list")){
                for (int i=0; i<numberOfTask; i++){
                    System.out.println("[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println(line);
            } else if (userInput.contains("unmark")){
                userInput = userInput.substring(7);
                taskNumber = Integer.parseInt(userInput) - 1;
                tasks[taskNumber].unmarkStatusIcon();
            } else if (userInput.contains("mark")){
                userInput= userInput.substring(5);
                taskNumber = Integer.parseInt(userInput) - 1;
                tasks[taskNumber].markStatusIcon();
            } else{ //add new task to list
                newTask = new Task(userInput);
                tasks[numberOfTask] = newTask;
                numberOfTask++;
                System.out.println(" added: " + userInput + "\n" + line);
            }
            userInput = in.nextLine();
        }

        String byeMessage = " Bye. Hope to see you again soon!\n";
        System.out.println(byeMessage + line);
    }
}
