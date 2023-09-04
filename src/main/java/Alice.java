import java.util.Scanner;

public class Alice {
    private static final String LINE = "____________________________________________________________\n";
    private static Task[] tasks = new Task[100];
    private static int numberOfTask = 0;

    /**
     *
     * List all tasks that have been added by user.
     *
     */
    public static void listTasks(){
        for (int i=0; i<numberOfTask; i++){
            System.out.println("[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        System.out.println(LINE);
    }

    /**
     *
     * Records down task from user input.
     * User is able to mark and unmark tasks, and also list all the tasks.
     *
     */
    public static void main(String[] args) {
        String helloMessage = " Hi fellow citizen, I am Alice, and you're in my wonderland :)\n What can I do for you?\n";
        Task newTask;
        int taskNumber;

        Scanner in = new Scanner(System.in);
        System.out.println(helloMessage + LINE);
        String userInput = in.nextLine();
        String actionOfInput;

        while (!(userInput.equals("bye"))){
            actionOfInput = userInput.split(" ")[0];
            switch (actionOfInput){
            case "list":
                listTasks();
                break;
            case "unmark":
                taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskNumber].unmarkStatusIcon();
                break;
            case "mark":
                taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskNumber].markStatusIcon();
                break;
            default:
                newTask = new Task(userInput);
                tasks[numberOfTask] = newTask;
                numberOfTask++;
                System.out.println(" added: " + userInput + "\n" + LINE);
            }
            userInput = in.nextLine();
        }

        String byeMessage = " Bye for now... We will miss you:( See you again very soon!\n";
        System.out.println(byeMessage + LINE);
    }
}