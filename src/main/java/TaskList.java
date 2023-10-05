import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import dukey.DukeyException;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public static void addTodo(String words, ArrayList<Task> tasks) {
 /*       String[] words = line.split(" ");
        String description = "";
        try {
            for (int i = 1; i < words.length; i++) {
                description += words[i] + " ";
            } */
            tasks.add(new Todo(words));
            tasks.get(tasks.size() - 1).printNewTask();
     /*   } catch(IndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println(DukeyException.todoDescriptionError());
            Ui.printLine();
        } */
    }

    public static void deleteTask(String line, ArrayList<Task> tasks) {
        try {
            String[] words = line.split(" ");
            int index  = Integer.parseInt(words[1]) - 1;
            Task delete = tasks.get(index);
            tasks.get(index).printDeleteTask();
            tasks.remove(index);
        }
        catch(IndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println(DukeyException.todoDescriptionError());
            Ui.printLine();
        }
    }

    public static void addEvent(String from, String to, String description, ArrayList<Task> tasks) {
     //   int startIndexOfFrom = line.indexOf("/from");
     //   int startIndexOfTo = line.indexOf("/to");
     //   final int beginIndex = 6;
  //      try {
  //          String from = line.substring(startIndexOfFrom + 6, startIndexOfTo);
  //          String to = line.substring(startIndexOfTo + 4);
  //          String description = line.substring(beginIndex, startIndexOfFrom);
            tasks.add(new Event(from, to, description));
            tasks.get(tasks.size() - 1).printNewTask();
   //     } catch(StringIndexOutOfBoundsException e) {
   //         Ui.printLine();
   //         System.out.println(DukeyException.EventFormatError());
   //         Ui.printLine();
    //    }
    }

    public static void addDeadline(String description, String by, ArrayList<Task> tasks) {
 //       String[] words = line.split("/by");
//        String[] words2 = line.split(" ");
        //    int indexOfDescriptionEnd = line.indexOf("/by");
 //       try {
 //           String description = words2[1];
  //          String by = words[1];
           tasks.add(new Deadline(description, by));
            tasks.get(tasks.size() - 1).printNewTask();
 //       } catch(ArrayIndexOutOfBoundsException e) {
  //          Ui.printLine();
  //          System.out.println(DukeyException.deadlineDescriptionError());
  //          Ui.printLine();
  //      }
    }

    public static void unmarkTask(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]) - 1;
        tasks.get(taskNum).unmarkTask();
    }

    public static void markTask(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]) - 1;
        tasks.get(taskNum).setDone();
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        Ui.printLine();
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println((index++) + "." + task);
        }
        Ui.printLine();
    }

}
