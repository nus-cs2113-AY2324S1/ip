package jarvis.command;

import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

public class Command {
    public enum CommandType {TODO, DEADLINE, EVENT, DELETE, LIST, MARK, UNMARK, FIND, EXIT};
}
