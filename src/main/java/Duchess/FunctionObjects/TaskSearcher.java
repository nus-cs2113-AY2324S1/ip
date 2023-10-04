package Duchess.FunctionObjects;

import Duchess.TaskObjects.TaskList;
import Duchess.TaskObjects.Task;

public class TaskSearcher {

    TaskList taskList;
    UI ui;

    public TaskSearcher(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public void search(String keyword) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).contains(keyword)) {
                ui.printTask(taskList.get(i));
            }
        }
    }
}
