package duke.storage;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import duke.exception.DukeException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Tasklist;

import static duke.ui.MessageConstants.MESSAGE_ERROR_FILE_CREATION;
import static duke.ui.MessageConstants.MESSAGE_ERROR_FILE_READING;
import static duke.ui.MessageConstants.MESSAGE_ERROR_FILE_WRITING;


import static duke.storage.StorageSettings.FILE_PATH;


/**
 * Represents a class for reading and writing tasks to a file.
 */
public class FileRW {

    /**
     * Reads tasks from the file and adds them to the given Tasklist.
     *
     * @param tasks The Tasklist to add the tasks to.
     * @throws DukeException If there is an error reading the file.
     */
    public static void readFromFile(Tasklist tasks) throws DukeException {
        
        if (!new File(FILE_PATH).exists()) {
            try {
                File file = new File(FILE_PATH);
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                throw new DukeException(MESSAGE_ERROR_FILE_CREATION);
            }
        }

        try {
            File file = new File(FILE_PATH);
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
                    newTask = new Event(lineSplit[2], lineSplit[3], lineSplit[3]);
                }

                if (newTask != null) {
                    if (lineSplit[1].equals("true")) {
                        newTask.setDone(true);
                    }
                    tasks.add(newTask);
                }
            }
            fileScan.close();
        } catch (Exception e) {
            throw new DukeException(MESSAGE_ERROR_FILE_READING);
        }
    }

    /**
     * Writes tasks from the given Tasklist to the file.
     *
     * @param tasks The Tasklist to write the tasks from.
     * @throws DukeException If there is an error writing to the file.
     */
    public static void writeToFile(Tasklist tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task : tasks) {
                if (task instanceof Todo) {
                    bw.write("T | " + task.getIsDone() + " | " + task.getTaskName());
                } else if (task instanceof Deadline) {
                    bw.write("D | " + task.getIsDone() + " | " + task.getTaskName() + " | " + ((Deadline) task).getDeadline());
                } else if (task instanceof Event) {
                    bw.write("E | " + task.getIsDone() + " | " + task.getTaskName() + " | " + ((Event) task).getFromTime() + " | " + ((Event) task).getToTime());
                }
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new DukeException(MESSAGE_ERROR_FILE_WRITING);
        }
    }

}