package Ken;

import Exceptions.KenFileCorruptedException;
import Exceptions.KenMissingTaskException;
import Exceptions.KenReadFromFileException;
import Exceptions.KenWriteToFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class FileHandler {
    private static final String FILE_PATH = "data/kenbot.txt";
    private static final char TODO = 'T';
    private static final char DEADLINE = 'D';
    private static final char EVENT = 'E';

    public static void readFromFile(TaskList list) throws KenReadFromFileException, KenFileCorruptedException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                throw new KenReadFromFileException();
            }
        } else {
            getDataFromFile(file, list);
        }
    }

    private static void getDataFromFile(File file, TaskList list) throws KenReadFromFileException, KenFileCorruptedException {
        try {
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                String lineInput = in.nextLine();
                char activityType = lineInput.charAt(1);
                boolean isDone = lineInput.contains("[X]");
                String lineInformation = lineInput.split("] ", 2)[1];
                if (lineInformation.isEmpty()) {
                    throw new KenFileCorruptedException("Sweetie, where's the sparkle? The task description is like, totally missing from the scene!");
                }

                switch (activityType) {
                case TODO:
                    String todoDescription = lineInformation.trim();
                    Task todo = new Todo(todoDescription);
                    todo.setDone(isDone);
                    list.addTask(todo);
                    break;
                case DEADLINE:
                    String[] deadlineInformation = lineInformation.split("[(]by:", 2);
                    String deadlineDescription = deadlineInformation[0].trim();
                    String deadlineBy = deadlineInformation[1].replace(")", "").trim();
                    Task deadline = new Deadline(deadlineDescription, deadlineBy);
                    deadline.setDone(isDone);
                    list.addTask(deadline);
                    break;
                case EVENT:
                    String[] eventInformation = lineInformation.split("[(]from:|to:", 3);
                    String eventDescription = eventInformation[0].trim();
                    String eventFrom = eventInformation[1].trim();
                    String eventTo = eventInformation[2].replace(")", "").trim();
                    Task event = new Event(eventDescription, eventFrom, eventTo);
                    event.setDone(isDone);
                    list.addTask(event);
                    break;
                default:
                    throw new KenFileCorruptedException("Oopsie, darling! The task type is like, totally not on our fabulous radar!");
                }

            }
        } catch (FileNotFoundException e) {
            throw new KenReadFromFileException();
        } catch (KenFileCorruptedException e) {
            throw new KenFileCorruptedException("The file is throwing a Barbie-sized tantrum. It's all corrupted, darling!");
        }
    }

    public static void writeToFile(TaskList list) throws KenWriteToFileException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 1; i <= list.getSize(); i++) {
                Task task = list.getTask(i);
                String taskText = task.toString().trim();
                fw.write(taskText + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            throw new KenWriteToFileException();
        }
    }
}
