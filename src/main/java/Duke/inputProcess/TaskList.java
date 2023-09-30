package Duke.inputProcess;

import Duke.Task;
import Duke.tasks.Deadline;
import Duke.tasks.Todo;
import Duke.tasks.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list;

    public TaskList(){
        list = new ArrayList<>();
    }

    public void addTodo(String userInput){
        list.add(new Todo(userInput));
    }

    public void addEvent(String eventToAdd, LocalDateTime startTime, LocalDateTime endTime){
        list.add(new Event(eventToAdd, startTime, endTime));
    }

    public void addDeadline(String deadlineToAdd, LocalDateTime deadlineTime){
        list.add(new Deadline(deadlineToAdd, deadlineTime));
    }

    public Task get(int index){
        return list.get(index);
    }

    public int size(){
        return list.size();
    }


    public void printList() {
        for (int i = 0; i < list.size(); ++i) {
            System.out.print("\t" + (i+1) + ".");
            System.out.println(list.get(i));
        }
    }

    public void unmark(int taskNumToUnmark) {
        try {
            list.get(taskNumToUnmark).unmark();
            System.out.print("\tOK, I've marked this task as not done yet:\n\t\t");
            System.out.println(list.get(taskNumToUnmark));
        } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
            System.out.println("\tOOPS!!! Need to specify which task want to unmark");
        }
    }

    public void mark(int taskNumToMark) {
        try {
            list.get(taskNumToMark).markAsDone();
            System.out.print("\tNice! I've marked this task as done:\n\t\t");
            System.out.println(list.get(taskNumToMark));
        } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
            System.out.println("\tOOPS!!! Need to specify which task want to mark as done");
        }
    }


    public void deleteTask(int taskNumToDelete) {
        System.out.println("\tNoted. I've removed this task:\n\t\t" +
                list.get(taskNumToDelete));
        list.remove(taskNumToDelete);
        System.out.println("\tNow you have " + list.size() + " in the list");

    }
}
