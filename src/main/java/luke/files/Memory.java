package luke.files;

import luke.errors.LukeTimeError;
import luke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;

public class Memory {

    public static void readMemory(String filePath) throws FileNotFoundException {

        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currentLine = s.nextLine();

            String taskDetails = currentLine.substring(0,5);
            String taskDescription = currentLine.substring(4);
            char[] characters = taskDetails.toCharArray();
            try {
                switch (characters[4]) {
                    case 't':
                        Task newTodo = new Todo(taskDescription);
                        break;
                    case 'd':
                        Task newDeadline = new Deadline(taskDescription);
                        break;
                    case 'e':
                        Task newEvent = new Event(taskDescription);
                        break;
                    default:
                        break;
                }
            } catch (LukeTimeError e) {
                System.out.println("somethings wrong");
            }

            System.out.println(currentLine);
        }

    }

    public static void storeMemory(String filePath, Task[] taskList, int counter) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath); //overwrite file

            for (int i = 0; i < counter; i += 1) {
                fw.write(taskList[i].memoryString() + "\n");
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("IO Exception, storeMemory fail");
            return;
        }


    }
}