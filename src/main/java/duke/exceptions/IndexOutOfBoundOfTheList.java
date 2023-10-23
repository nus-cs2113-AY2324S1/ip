package duke.exceptions;

/**
 * An exception in case that the index given by the user is greater than the size of our task list
 */
public class IndexOutOfBoundOfTheList extends DukeException{

    public IndexOutOfBoundOfTheList(String message) {
        super(message);
    }
}
