package luke.actions;

import luke.files.Storage;
import luke.tasks.*;
import luke.user.LukeTimeError;
import luke.user.Ui;

public class AddCommand extends Command {
    public AddCommand(ActionType theAction, String parameters) {

        super(theAction, parameters);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //ui has String echo, storage has ArrayList<Task> tasks, tasks has ArrayList<Task> mainTaskList;

        case TODO:
        Task newTodo = new Todo(echo);
        addTask(newTodo);

        System.out.println("\tGot it. I've added this task:" + "\n" + taskList.get(taskList.size() - 1));
        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
        break;

        case DEADLINE:
        try {
            Task newDeadline = new Deadline(echo);
            addTask(newDeadline);

            System.out.println("\tGot it. I've added this task:" + "\n" + taskList.get(taskList.size() - 1));
            System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
        } catch (LukeTimeError e) {
            System.out.println("\tOOPS!!! There's an error in the deadline's 'do by' date.");
        }
        break;

        case EVENT:
        try {
            Task newEvent = new Event(echo);
            addTask(newEvent);

            System.out.println("\tGot it. I've added this task:" + "\n" + taskList.get(taskList.size() - 1));
            System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
        } catch (LukeTimeError e) {
            System.out.println("\tOOPS!!! There's an error in the event's start and end time.");
        }
        break;
    } catch (IndexOutOfBoundsException e) { //empty for MARK, UNMARK, TO DO description, DEADLINE description, EVENT description
        System.out.println("\tOOPS!!! You have missing arguments for " + words[0] + ".");
    }
    }
}
