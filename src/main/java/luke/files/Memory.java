package luke.files;

import luke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

import java.io.IOException;

public class Memory {

    public static void readMemory(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void storeMemory(String filePath, Task[] taskList, int counter) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath); //overwrite file

            for (int i = 0; i < counter; i += 1) {
                fw.write("\t" + (i + 1) + "." + taskList[i] + "\n");
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("IO Exception, storeMemory fail");
            return;
        }


    }
}