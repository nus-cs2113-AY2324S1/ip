import src.main.java.Task;
import java.util.Scanner;

public class Alice {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String helloMessage = " Hi there, I'm Alice, a chatbot!\n What can I do for you?\n";
        Task[] tasks = new Task[100];
        Task newTask;
        int inputCount = 0;
        int itemNumber;
        Scanner in = new Scanner(System.in);
        System.out.println(helloMessage + line);
        String userInput = in.nextLine();
        while (!(userInput.equals("bye"))){
            if (userInput.equals("list")){
                for (int i=0; i<inputCount; i++){
                    System.out.println("[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println(line);
            } else if (userInput.contains("unmark")){
                userInput = userInput.substring(7);
                itemNumber = Integer.parseInt(userInput) - 1;
                tasks[itemNumber].unmarkStatusIcon();
            } else if (userInput.contains("mark")){
                userInput= userInput.substring(5);
                itemNumber = Integer.parseInt(userInput) - 1;
                tasks[itemNumber].markStatusIcon();
            } else{ //add new task to list
                newTask = new Task(userInput);
                tasks[inputCount] = newTask;
                inputCount++;
                System.out.println(" added: " + userInput + "\n" + line);
            }
            userInput = in.nextLine();
        }

        String byeMessage = " Bye. Hope to see you again soon!\n";
        System.out.println(byeMessage + line);
    }
}
