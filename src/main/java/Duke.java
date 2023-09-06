import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello my name is DUKE");
        System.out.println("What can I do for you today?");

        ArrayList<Task> tasks = new ArrayList<>(100);

        Scanner scan = new Scanner( System.in );

        boolean isEchoing = true;
        while (isEchoing){
            String input;
            input = scan.nextLine();

            if (input.equals("bye")){
                isEchoing = false;
                break;
            }

            if (input.equals("list")){
                int numOfTasks = 0;

                for (Task task: tasks){
                    numOfTasks++;
                    System.out.println(numOfTasks + ": " + task.getDescription());
                }

            } else if (input.toLowerCase().startsWith("mark ")){
                String[] parts = input.split(" ");

                int taskNum = Integer.parseInt(parts[1]);
                Task markedTask = tasks.get(taskNum - 1);

                markedTask.isDone = true;
                System.out.println("Congrats I marked this class as done : " + markedTask.getDescription());

            } else if (input.toLowerCase().startsWith("unmark ")) {
                String[] parts = input.split(" ");

                int taskNum = Integer.parseInt(parts[1]);
                tasks.get(taskNum - 1).isDone = false;

                Task unmarkedTask = tasks.get(taskNum - 1);

                unmarkedTask.isDone = true;
                System.out.println("I unmarked this class as done: " + unmarkedTask.getDescription());
            } else {
                // Add the task to the list
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println("Added: " + input);
            }






        }
        System.out.println("Fine. If you have no ideas Imma head out");

    }
}
