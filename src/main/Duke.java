import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.ToDo;
import duke.Bot;

public class Duke {
    public static final String FILE_PATH = "src\\main\\data\\data.txt";

    public static void addToDoToFile(ToDo newToDo) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String format = "\nT," + (newToDo.isCompleted() ? "1" : "0") + "," + newToDo.getName();
            fw.write(format);
            fw.close();
        } catch (IOException exception) {
            System.out.println("Cannot write to file");
        }
    }

    public static void addDeadlineToFile(Deadline newDeadline) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String format = "\nT," + (newDeadline.isCompleted() ? "1" : "0") + "," + newDeadline.getName() + "," + newDeadline.getBy();
            fw.write(format);
            fw.close();
        } catch (IOException exception) {
            System.out.println("Cannot write to file");
        }
    }

    public static void addEventToFile(Event newEvent) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            String format = "\nT," + (newEvent.isCompleted() ? "1" : "0") + "," + newEvent.getName() + "," + newEvent.getFrom()  + "," + newEvent.getTo();
            fw.write(format);
            fw.close();
        } catch (IOException exception) {
            System.out.println("Cannot write to file");
        }
    }
  
     public static void fileToArrayList(ArrayList<Task> taskList, File f) {
        try{
            Scanner reader = new Scanner(f);
            while(reader.hasNextLine()) {
                String[] dataSplit = (reader.nextLine()).split(",");
                if(dataSplit[0].equals("T")) {
                    ToDo todoTask = new ToDo(dataSplit[2]);
                    if(dataSplit[1].equals("1")) {
                        todoTask.setCompleted(true);
                    } else {
                        todoTask.setCompleted(false);
                    }
                    taskList.add(todoTask);
                } else if(dataSplit[0].equals("D")) {
                    Deadline deadlineTask = new Deadline(dataSplit[2], dataSplit[3]);
                    if(dataSplit[1].equals("1")) {
                        deadlineTask.setCompleted(true);
                    } else {
                        deadlineTask.setCompleted(false);
                    }
                    taskList.add(deadlineTask);
                } else {
                    Event eventTask = new Event(dataSplit[2], dataSplit[3], dataSplit[4]);
                    if(dataSplit[1].equals("1")) {
                        eventTask.setCompleted(true);
                    } else {
                        eventTask.setCompleted(false);
                    }
                    taskList.add(eventTask);
                }
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Error");
        }
    }
  
    public static void main(String[] args) {
        File f = new File(FILE_PATH);
        try {
            if(f.exists()) {
                fileToArrayList(taskList, f);
            } else {
                f.createNewFile();
            }
        } catch (IOException exception) {
            System.out.println("An error occurred.");
        }
        Bot ranayBot = new Bot();
        ranayBot.runBot();
    }
}