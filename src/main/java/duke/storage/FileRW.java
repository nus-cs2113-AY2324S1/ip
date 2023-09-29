package duke.storage;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileRW {

    public static void readFromFile(ArrayList<Task> tasks) {
        try {
            File file = new File("data/duke.txt");
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                String[] lineSplit = line.split(" \\| ");
                Task newTask = null;

                if (lineSplit[0].equals("T")) {
                    newTask = new Todo(lineSplit[2]);
                } else if (lineSplit[0].equals("D")) {
                    newTask = new Deadline(lineSplit[2], lineSplit[3]);
                } else if (lineSplit[0].equals("E")) {
                    newTask = new Event(lineSplit[2], lineSplit[3]);
                }

                if (newTask != null) {
                    if (lineSplit[1].equals("true")) {
                        newTask.markAsDone();
                    }
                    tasks.add(newTask);
                }
            }
            fileScan.close();
        } catch (Exception e) {
            try {
                File file = new File("data/duke.txt");
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task : tasks) {
                if (task instanceof Todo) {
                    bw.write("T | " + task.isDone() + " | " + task.getTaskName());
                } else if (task instanceof Deadline) {
                    bw.write("D | " + task.isDone() + " | " + task.getTaskName() + " | " + ((Deadline) task).getDeadline());
                } else if (task instanceof Event) {
                    bw.write("E | " + task.isDone() + " | " + task.getTaskName() + " | " + ((Event) task).getEventTime());
                }
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

}
