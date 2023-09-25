package Commands;

import Exceptions.KenException;
import Tasks.TaskList;
import UI.Ui;

import static Storage.Storage.writeToFile;

public class Goodbye extends Command {
    @Override
    public void run(TaskList list) throws KenException {
        Ui.byeUser();
        writeToFile(list);
    }
}
