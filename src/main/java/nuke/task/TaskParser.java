package nuke.task;

import nuke.storage.exception.TaskParseException;

import java.util.Arrays;

/**
 * Parser that parses formatted tasks in the save file.
 */
public class TaskParser {
    public static final String TASK_FORMAT_SEPARATOR = " / ";
    public static final String TASK_FORMAT_MARKED = "1";
    public static final String TASK_FORMAT_UNMARKED = "0";

    /**
     * Parses a line of string into {@link Task} and returns it.
     *
     * @param line string of a formatted task
     * @return task; result of parsing
     * @throws TaskParseException if parse fails
     */
    public static Task parseTask(String line) throws TaskParseException {
        String[] words = line.split(TASK_FORMAT_SEPARATOR);
        if (words.length < 3) {
            throw new TaskParseException();
        }

        String type = words[0];
        boolean mark = parseMark(words[1]);
        String[] args = Arrays.copyOfRange(words, 2, words.length);

        Task task = parseArgs(type, args);
        task.setDone(mark);
        return task;
    }

    private static boolean parseMark(String mark) throws TaskParseException {
        switch (mark) {
        case TASK_FORMAT_MARKED:
            return true;
        case TASK_FORMAT_UNMARKED:
            return false;
        default:
            throw new TaskParseException();
        }
    }

    private static Task parseArgs(String type, String[] args) throws TaskParseException {
        Task task;
        switch (type) {
        case Todo.TYPE:
            task = new Todo(args[0]);
            break;
        case Deadline.TYPE:
            task = new Deadline(args[0], args[1]);
            break;
        case Event.TYPE:
            task = new Event(args[0], args[1], args[2]);
            break;
        default:
            throw new TaskParseException();
        }
        return task;
    }
}
