package nuke.task;

import nuke.storage.exception.TaskParseException;

/**
 * Parser that parses formatted tasks in the save file.
 */
public class TaskParser {

    /**
     * Parses a line of string into {@link Task} and returns it.
     *
     * @param line string of formatted tasks
     * @return task; result of parsing
     * @throws TaskParseException if parse fails
     */
    public static Task parseTask(String line) throws TaskParseException {
        Task task;
        String[] words = line.split(" / ");
        if (words.length < 3) {
            throw new TaskParseException();
        }
        String type = words[0];
        boolean mark;
        switch (words[1]) {
        case "1":
            mark = true;
            break;
        case "0":
            mark = false;
            break;
        default:
            throw new TaskParseException();
        }
        switch (type) {
        case Todo.TYPE:
            task = new Todo(words[2]);
            break;
        case Deadline.TYPE:
            task = new Deadline(words[2], words[3]);
            break;
        case Event.TYPE:
            task = new Event(words[2], words[3], words[4]);
            break;
        default:
            throw new TaskParseException();
        }
        task.setDone(mark);

        return task;
    }
}
