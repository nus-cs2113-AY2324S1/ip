package Duke;

import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {
    private static void saveToTextFile(ArrayList<Task> list) throws IOException{
        FileWriter fw = new FileWriter("duke.txt");
        for (int i = 0; i < list.size(); ++i){
            String textToAdd = "";
            String task = list.get(i).getDescription();
            String time = list.get(i).getEventTime();
            String isDone = list.get(i).getStatus() ? "1" : "0";
            if(list.get(i).getClass() == Todo.class){
                textToAdd = "T" + " | " + isDone + " | " + task + "\n";
            }
            else if(list.get(i).getClass() == Deadline.class){
                textToAdd = "D" + " | " + isDone + " | " + task + " | " + time + "\n";
            }
            else if(list.get(i).getClass() == Event.class){
                textToAdd = "E" + " | " + isDone + " | " + task + " | " + time + "\n";
            }
            fw.write(textToAdd);
        }
        fw.close();
    }

    private static void getFromTextFile(ArrayList<Task> list) throws FileNotFoundException{
        File f = new File("duke.txt"); // create a File for the given file path
        try {
            if(f.createNewFile()) {
                return;
            }
        } catch(IOException e){
            System.out.println("Something went wrong");
        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int i = 0;
        while (s.hasNext()) {
            String textLine = s.nextLine();
            try {
                String[] words = textLine.split(" \\| ");
                String command = textLine.split(" \\| ")[0];
                String isDone = textLine.split(" \\| ")[1];
                String task = textLine.split(" \\| ")[2];
                String time = "";
                if (!command.equals("T")) {
                    time = textLine.split(" \\| ")[3];
                }
                switch (command){
                case "T":
                    list.add(new Todo(task));
                    break;
                case "D":
                    list.add(new Deadline(task,time));
                    break;
                case "E":
                    list.add(new Event(task, time));
                    break;
                default:
                }
                if(isDone.equals("1")) {
                    list.get(i).markAsDone();
                }
                i++;
            } catch(IndexOutOfBoundsException e){
                System.out.println("The file not in the correct format");
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<Task> list= new ArrayList<>();
        try{
            getFromTextFile(list);
        } catch(FileNotFoundException e){
            System.out.printf("a");
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Bot Hilary");
        System.out.println("What can I do for you?");
        String line = in.nextLine();
        String eventTime = "";
        while (!line.equals("bye")) {
            String command = line.split(" ", 2)[0];
            if (!command.equals("list")){
                try{
                    line = line.split(" ", 2)[1];
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                    line = in.nextLine();
                    continue;
                }
            }
            switch (command){
            case "list":
                for (int i = 0; i < list.size(); ++i) {
                    System.out.print("\t" + (i+1) + ".");
                    System.out.println(list.get(i));
                }
                break;
            case "unmark":
                try {
                    list.get(Integer.parseInt(line) - 1).unmark();
                    System.out.print("\tOK, I've marked this task as not done yet:\n\t\t");
                    System.out.println(list.get(Integer.parseInt(line) - 1));
                } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! Need to specify which task want to unmark");
                }
                break;
            case "mark":
                try {
                    list.get(Integer.parseInt(line) - 1).markAsDone();
                    System.out.print("\tNice! I've marked this task as done:\n\t\t");
                    System.out.println(list.get(Integer.parseInt(line) - 1));
                } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! Need to specify which task want to mark as done");
                }
                break;
            case "deadline":
                try {
                    eventTime = line.split("/by ", 2)[1];
                    line = line.split("/", 2)[0];
                    eventTime = eventTime.replace("from", "from:");
                    eventTime = eventTime.replace("/", "");
                    list.add(new Deadline(line, eventTime));
                    System.out.println("\tGot it. I've added this task:\n\t\t" + list.get(list.size() - 1) + "\n\tNow you have "+ list.size() + " tasks in the list.");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The deadline need to separated by \"/by\"");
                }
                break;
            case "event":
                try {
                    eventTime = line.split("/from ", 2)[1];
                    line = line.split("/",2)[0];
                    eventTime = eventTime.replace("/to", "to:");
                    list.add(new Event(line, eventTime));
                    System.out.println("\tGot it. I've added this task:\n\t\t" + list.get(list.size() - 1) + "\n\tNow you have "+ list.size() + " tasks in the list.");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The event timing need to separated by \"/from\"");
                }
                break;
            case "todo":
                try {
                    list.add(new Todo(line));
                    System.out.print("\tGot it. I've added this task: \n\t\t");
                    System.out.println(list.get(list.size() - 1));
                    Task.listCount++;
                    System.out.println("\tNow you have " + list.size() + " in the list");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "delete":
                try {
                    System.out.print("\tNoted. I've removed this task:\n\t\t");
                    System.out.println(list.get(Integer.parseInt(line) - 1));
                    list.remove(Integer.parseInt(line) - 1);
                    System.out.println("\tNow you have " + list.size() + " in the list");
                } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! Need to specify which task want to unmark");
                }
                break;
            default:
                System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            try {
                saveToTextFile(list);
            } catch(IOException e){
                System.out.println("Something wrong to save the file");
            }
            line = in.nextLine();
        }
        System.out.println("  Bye. Hope to see you again soon! ");
    }
}