package luke.files;

import luke.user.LukeTimeError;
import luke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;

public class Memory {

    public static ArrayList<Task> readMemory(String filePath) throws FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<>();

        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            System.out.println("\t" + currentLine);

            String taskDetails = currentLine.substring(0,6);
            //System.out.println(taskDetails);
            String taskDescription = currentLine.substring(7);
            //System.out.println(taskDescription);
            char[] characters = taskDetails.toCharArray();
            try {
                Task newTask;
                switch (characters[1]) {
                    case 'T':
                        newTask = new Todo(taskDescription);
                        break;
                    case 'D':
                        newTask = new Deadline(taskDescription);
                        break;
                    case 'E':
                        newTask = new Event(taskDescription);
                        break;
                    default:
                        newTask = new Todo("error");
                        break;
                }

                tasks.add(newTask);
            } catch (LukeTimeError e) {
                System.out.println("\tsomethings wrong");
            }
        }
        return tasks;
    }

    public static void storeMemory(String filePath, ArrayList<Task> taskList){
        try {
            FileWriter fw = new FileWriter(filePath); //overwrite file

            for (int i = 0; i < taskList.size(); i += 1) {
                fw.write(taskList.get(i).getType() + taskList.get(i).memoryString() + "\n");
            }

            fw.close();

            System.out.println("\tMemory Stored Safely!");

        } catch (IOException e) {
            System.out.println("\tIO Exception: fail to store memory");
            //return;
        }

    }
}