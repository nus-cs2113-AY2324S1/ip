package dawson;

import dawson.task.*;

public class Parser {

    public static Task parseTask(String encodedTaskString) throws DawsonException {
        final String DELIMITER = "\\|";

        final int TODO_FIELDS_NO = 3;
        final int DEADLINE_FIELDS_NO = 4;
        final int EVENT_FIELDS_NO = 5;

        final int TYPE_INDEX = 0;
        final int ISDONE_INDEX = 1;
        final int DESC_INDEX = 2;
        final int BY_INDEX = 3;
        final int START_INDEX = 3;
        final int END_INDEX = 4;

        String[] split = encodedTaskString.split(DELIMITER, 2);
        String taskType = split[TYPE_INDEX].trim(); // Get first letter

        switch (taskType) {
            case "T":
                String[] todoSplit = encodedTaskString.split(DELIMITER, TODO_FIELDS_NO);
                if (todoSplit.length >= TODO_FIELDS_NO) {
                    String desc = todoSplit[DESC_INDEX].trim();
                    String isDone = todoSplit[ISDONE_INDEX].trim();

                    Todo todo = new Todo(desc);
                    if (isDone.equals("1")) todo.markAsDone();
                    return todo;
                }
                break;

            case "D":
                String[] deadlineSplit = encodedTaskString.split(DELIMITER, DEADLINE_FIELDS_NO);
                if (deadlineSplit.length >= DEADLINE_FIELDS_NO) {
                    String desc = deadlineSplit[DESC_INDEX].trim();
                    String isDone = deadlineSplit[ISDONE_INDEX].trim();
                    String by = deadlineSplit[BY_INDEX].trim();

                    Deadline deadline = new Deadline(desc, by);
                    if (isDone.equals("1")) deadline.markAsDone();
                    return deadline;
                }
                break;

            case "E":
                String[] eventSplit = encodedTaskString.split(DELIMITER, EVENT_FIELDS_NO);
                if (eventSplit.length >= EVENT_FIELDS_NO) {
                    String desc = eventSplit[DESC_INDEX].trim();
                    String isDone = eventSplit[ISDONE_INDEX].trim();
                    String start = eventSplit[START_INDEX].trim();
                    String end = eventSplit[END_INDEX].trim();

                    Event event = new Event(desc, start, end);
                    if (isDone.equals("1")) event.markAsDone();
                    return event;
                }
                break;
        }
        throw new DawsonException("Error parsing tasks from storage!");
    }

}
