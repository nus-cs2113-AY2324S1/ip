package Storage;

import Exceptions.KenDateException;
import Exceptions.KenFileCorruptedException;
import Exceptions.KenReadFromFileException;
import Exceptions.KenWriteToFileException;
import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class Storage {
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
                char taskType = lineInput.charAt(1);

                boolean isDone;
                if (lineInput.contains("][ ]")) {
                    isDone = false;
                } else if (lineInput.contains("][X]")) {
                    isDone = true;
                } else {
                    throw new KenFileCorruptedException("Uh-oh, darling! The task status is like, totally hiding from the spotlight!");
                }

                String[] line = lineInput.split("] ", 2);
                String lineInformation;
                if (line.length < 2) {
                    throw new KenFileCorruptedException("Sweetie, where's the sparkle? The task description is like, totally missing from the scene!");
                } else {
                    lineInformation = line[1];
                }

                switch (taskType) {
                case TODO:
                    Task todo = getTodo(lineInformation);
                    todo.setDone(isDone);
                    list.addTask(todo);
                    break;
                case DEADLINE:
                    Task deadline = getDeadline(lineInformation);
                    deadline.setDone(isDone);
                    list.addTask(deadline);
                    break;
                case EVENT:
                    Task event = getEvent(lineInformation);
                    event.setDone(isDone);
                    list.addTask(event);
                    break;
                default:
                    throw new KenFileCorruptedException("Oopsie, darling! The task type is like, totally not on our fabulous radar!");
                }

            }
        } catch (FileNotFoundException e) {
            throw new KenReadFromFileException();
        }
    }

    public static void writeToFile(TaskList list) throws KenWriteToFileException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < list.getSize(); i++) {
                Task task = list.getTask(i);
                String taskText = task.toString().trim();
                fw.write(taskText + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            throw new KenWriteToFileException();
        }
    }

    public static Task getDeadline(String input) throws KenFileCorruptedException {
        String[] deadlineInformation = input.split("[(]by:", 2);
        String deadlineDescription;
        String deadlineByString;
        LocalDateTime deadlineBy;
        try {
            deadlineDescription = deadlineInformation[0].trim();
            deadlineByString = deadlineInformation[1].replace(")", "").trim();
            deadlineBy = LocalDateTime.parse(deadlineByString, DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
        } catch (IndexOutOfBoundsException e) {
            throw new KenFileCorruptedException("The deadline is like, totally missing its fabulous moment!");
        } catch (DateTimeParseException e) {
            throw new KenFileCorruptedException("The deadline date seems to be corrupted!");
        }
        return new Deadline(deadlineDescription, deadlineBy);
    }

    public static Task getTodo(String input) {
        String todoDescription = input.trim();
        return new Todo(todoDescription);
    }

    public static Task getEvent(String input) throws KenFileCorruptedException {
        String[] eventInformation = input.split("[(]from:|to:", 3);
        String eventDescription;
        String eventFromString;
        String eventToString;
        LocalDateTime eventFrom;
        LocalDateTime eventTo;
        try {
            eventDescription = eventInformation[0].trim();
            eventFromString = eventInformation[1].trim();
            eventFrom = LocalDateTime.parse(eventFromString, DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
            eventToString = eventInformation[2].replace(")", "").trim();
            eventTo = LocalDateTime.parse(eventToString, DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
        } catch (IndexOutOfBoundsException e) {
            throw new KenFileCorruptedException("The event period is like, totally missing from the spotlight!");
        }
        return new Event(eventDescription, eventFrom, eventTo);
    }
}
