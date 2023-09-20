package chat0pt.helper;

import chat0pt.commands.Task;

import java.util.ArrayList;

public class Printer {
    final static String LINE = "_______________________________________________________________________";

    public static void print(String[] toPrint){
        System.out.println(LINE);
        for (String str: toPrint){
            System.out.println(str);
        }
        System.out.println(LINE);
    }

    public static void welcomeMessage(){
        String[] welcome = {"Hello! I'm Chat0PT!", "What can I do for you?"};
        print(welcome);
    }

    public static void goodbyeMessage(){
        String[] goodbye = {"Bye. Hope to see you again soon!"};
        print(goodbye);
    }

    public static void invalidMessage(String action){
        print(new String[]{"☹ OOPS!!! The description of a " + action + " cannot be empty or the format is incorrect."});
    }

    public static void successfulTask(Task t, int arrayListSize){
        String[] successPrint = {"Got it. I've added this task:", t.toString(), "Now you have " + arrayListSize + " tasks in your list."};
        print(successPrint);
    }

    public static void unsupportedCommand(){
        String[] unsupported = {"Sorry! I do not support this command at this point. I can only add Deadline, Event and Todo!"};
        print(unsupported);
    }
    public static void invalidDelete(){
        String[] message = {"☹ Unable to delete task! Check if the format or task number is correct."};
        print(message);
    }

    public static void deleteMessage(Task t, int size){
        size -= 1;
        String[] message = {"Noted. I've removed this task: ", t.toString(), "Now you have " + size + " tasks in the list."};
        print(message);
    }

    public static void invalidMark(){
        String[] message = {"Please check your mark input. It can only be a number and it has to exist in the list."};
        print(message);
    }

    public static void mark(Task t, boolean toMark){
        String[] message = {"",t.toString()};
        if (toMark){
            message[0] = "Nice! I've marked this task as done:";
        }else{
            message[0] = "OK, I've marked this task as not done yet:";
        }
        print(message);
    }
    public static void listHandler(ArrayList<Task> tasks){
        int count = 1;
        String[] taskStrings = new String[tasks.size()+1];
        taskStrings[0] = "Here are the tasks in your list: ";
        for(Task t: tasks){
            taskStrings[count] = count + ". " +t.toString();
            count++;
        }
        print(taskStrings);
    }
}
