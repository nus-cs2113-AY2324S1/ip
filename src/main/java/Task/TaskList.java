package Task;

import java.util.ArrayList;

import Exceptions.DukeException;

public class TaskList {


    public static ArrayList<Task> list;

    //instantiate an array list
    public TaskList() {
        //creation of array list
        list = new ArrayList<>();
    }

    //method to add to arraylist
    public static void add(Task task){
        list.add(task);
    }

    //method to remove elements from arraylist using index
    public static void remove(int index){
        list.remove(index);
    }

    //getting elements
    public static Task get(int index){
        return list.get(index);
    }

    //obtaining size of array
    public static int size(){
        return list.size();
    }

    //find tasks with keyword
    public static void findTasks(String keyword) {
        ArrayList<Task> foundList = new ArrayList<>();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String taskAsString = list.get(i).toString();
            if (taskAsString.contains(keyword)) {
                foundList.add(list.get(i));
                System.out.print((i + 1) + ". ");
                System.out.println(get(i));
            }
        }
        if (foundList.isEmpty()) {
            System.out.println("oops, there are none :(");
        }
    }



    //method to add todo tasks
    public static void createTodoTasks(String task) {
        //instantiate new todo object
        Todo todoTask = new Todo(task);
        //add to array
        list.add(todoTask);
//        System.out.println("Got it. I've added this task:");
//        System.out.println(todoTask);
//        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
//        System.out.println(line);
    }

    //method to add deadline tasks
    public static void createDeadlineTasks(String task, String deadline) {
//        String task;
//        String deadline;
//        if (!input.contains("/by")) {
//            throw new DukeException("Ohno... Please check your format and include '/by'~");
//        } else {
//            String[] parts = input.split(" /by ");
//            //check if task or deadline are null
//            if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
//                throw new DukeException("Task or deadline cannot be empty... Please check your input again~");
//            }
//            task = parts[0].substring("deadline ".length());
//            deadline = parts[1];
//        }
        //instantiate new deadline object
        Deadline deadlineTask = new Deadline(task, deadline);
        //add to array
        list.add(deadlineTask);
//        System.out.println("Got it. I've added this task:");
//        System.out.println(deadlineTask);
//        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
//        System.out.println(line);
    }

    //method to add eventTask
    public static void createEventTasks(String task, String from, String to) {
//        String task;
//        String from;
//        String to;
//        if (!input.contains("/from") || !input.contains("/to")) {
//            throw new DukeException("Uhoh... Please check your format and include '/from' and '/to'~");
//        } else {
//            String[] parts = input.split(" /");
//            //check if task, to, from are null
//            if (parts.length != 3 || parts[0].isEmpty() || parts[1].equals("from") || parts[2].equals("to")) {
//                throw new DukeException("Task.Task, from or to cannot be empty... Please check your input again~");
//            }
//            task = parts[0].substring("event ".length());
//            from = parts[1].substring("from".length());
//            to = parts[2].substring("to".length());
//        }
        //instantiate new event object
        Event eventTask = new Event(task, from, to);
        //add to array
        list.add(eventTask);
//        System.out.println("Got it. I've added this task:");
//        System.out.println(eventTask);
//        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
//        System.out.println(line);
    }

}
