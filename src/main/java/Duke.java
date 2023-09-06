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

            if (input.equalsIgnoreCase("bye")){
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
                try {
                    Task markedTask = tasks.get(taskNum - 1);

                    markedTask.isDone = true;
                    System.out.println("Congrats I marked this class as done : " + markedTask.getDescription());
                } catch (Exception ArrayIndexOutOfBoundsException){
                    System.out.println("That task doesnt exist");
                }

            } else if (input.toLowerCase().startsWith("unmark ")) {
                String[] parts = input.split(" ");

                int taskNum = Integer.parseInt(parts[1]);
                tasks.get(taskNum - 1).isDone = false;

                try {
                    Task unmarkedTask = tasks.get(taskNum - 1);

                    unmarkedTask.isDone = false;
                    System.out.println("I unmarked this class as done: " + unmarkedTask.getDescription());
                } catch (Exception ArrayIndexOutOfBoundsException){
                    System.out.println("That task doesnt exist");
                }
            } else if (input.toLowerCase().startsWith("deadline")){
                try {
                    String[] toDoSplit = input.split("/");
                    //First part is task, and last is when by
                    String desc = toDoSplit[0].substring(9).trim();  // removes "deadline
                    Deadline deadline = new Deadline(desc, toDoSplit[1].trim());
                    System.out.println(deadline.getDescription());

                    tasks.add(deadline);

                } catch (Exception ArrayIndexOutOfBoundsException){
                    System.out.println("Put a / after your task if you want to add a todo");
                }


            } else if (input.toLowerCase().startsWith("event")){
                try {
                    String[] toDoSplit = input.split("/");
                    //First part is task, and last is when by
                    String desc = toDoSplit[0].substring(6).trim();
                    Event event = new Event(desc, toDoSplit[1].trim(), toDoSplit[2].trim());
                    System.out.println(event.getDescription());
                    tasks.add(event);

                } catch (Exception ArrayIndexOutOfBoundsException){
                    System.out.println("Put a / after your task if you want to add a todo");
                }


            } else if (input.toLowerCase().startsWith("todo")) {
                try {
                    String desc = input.substring(4).trim();
                    Todo todo = new Todo(desc);

                    System.out.println(todo.getDescription());
                    tasks.add(todo);

                } catch (Exception e) {
                    System.out.println("An error occurred while adding the todo.");
                }
            } else {
                // Add the task to the list
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println("Added: " + input);
            }






        }
        System.out.println("Fine. If you have no ideas imma head out");

    }
}
