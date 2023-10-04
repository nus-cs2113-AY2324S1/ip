package jerry.commands;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;

import jerry.task.Task;
/**
 * Finds and lists all tasks in task list whose description contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose description contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " tutorial CS2113 java";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<Task> tasksFound = getTasksWithNameContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForTaskListShownSummary(tasksFound), tasksFound);
    }

    /**
     * Retrieves all tasks in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of tasks found
     */
    private List<Task> getTasksWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList.getAllTasks()) {
            final Set<String> wordsInDescription = new HashSet<>(task.getWordsInDescription());
            if (!Collections.disjoint(wordsInDescription, keywords)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

}
