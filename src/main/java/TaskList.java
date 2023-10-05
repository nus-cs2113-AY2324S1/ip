import java.util.ArrayList;
import java.util.List;

public class TaskList {
    
    public static List<String> taskDescriptions = new ArrayList<>();
    public static List<Boolean> taskDoneStatus = new ArrayList<>();
    public static List<String> taskTypes = new ArrayList<>();
    public static List<String> taskDates = new ArrayList<>();

    public void parseAndAddTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            taskTypes.add(parts[0]);
            taskDoneStatus.add(parts[1].equals("1"));
            taskDescriptions.add(parts[2]);

            if (parts.length >= 4) {
                taskDates.add(parts[3]);
            } else {
                taskDates.add(null);
            }
        }
    }

    // Add getter methods for task-related lists
    public List<String> getTaskDescriptions() {
        return taskDescriptions;
    }

    public List<Boolean> getTaskDoneStatus() {
        return taskDoneStatus;
    }

    public List<String> getTaskTypes() {
        return taskTypes;
    }

    public List<String> getTaskDates() {
        return taskDates;
    }

    public static void listTasks(TaskList taskList) {
        System.out.println(" Here are the tasks in your list:");
        int numTasks = taskList.getTaskDescriptions().size(); // Get the number of tasks

        for (int i = 0; i < numTasks; i++) {
            char doneSymbol = taskList.getTaskDoneStatus().get(i) ? 'X' : ' ';
            String dateInfo = taskList.getTaskDates().get(i) != null ? taskList.getTaskDates().get(i) : "";
            System.out.println(" " + (i + 1) + ".[" + taskList.getTaskTypes().get(i) + "][" + doneSymbol + "] " + taskList.getTaskDescriptions().get(i) + dateInfo);
        }
    }

    public static void handleAddTask(String taskType, String taskDescription, TaskList taskList) throws EmptyDescriptionException {
        if (taskDescription.isEmpty()) {
            throw new EmptyDescriptionException("Hey!! Description cannot be empty for a " + taskType + " task.");
        }

        taskType = taskType.substring(0, 1).toUpperCase();
        String[] dateInfo = CommandParser.extractDateInfo(taskDescription);
        taskDescription = dateInfo[0];
        taskList.getTaskTypes().add(taskType);
        taskList.getTaskDates().add(dateInfo[1]);
        taskList.getTaskDescriptions().add(taskDescription.trim());
        taskList.getTaskDoneStatus().add(false);

        System.out.println(" Got it. I've added this task:");
        String dateInfoText = taskList.getTaskDates().get(taskList.getTaskDescriptions().size() - 1) != null ? " " + taskList.getTaskDates().get(taskList.getTaskDescriptions().size() - 1) : "";
        System.out.println("   [" + taskType + "][ ] " + taskDescription + dateInfoText);
        System.out.println(" Now you have " + taskList.getTaskDescriptions().size() + " tasks in the list.");
    }


    public static void handleDeleteTask(String taskDescription, TaskList taskList) throws KenException {
        try {
            int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.getTaskDescriptions().size()) {
                String deletedTaskDescription = taskList.getTaskDescriptions().get(taskIndex);

                System.out.println("Got it. I've removed this task:");
                System.out.println("   [" + taskList.getTaskTypes().get(taskIndex) + "][ ] " + deletedTaskDescription + (taskList.getTaskDates().get(taskIndex) != null ? taskList.getTaskDates().get(taskIndex) : ""));
                Storage.deleteTask(taskIndex, taskList);
                System.out.println("Now you have " + taskList.getTaskDescriptions().size() + " tasks in the list.");
            } else {
                throw new TaskNotFoundException("I can't find this task. Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException("Wrong format!! Please provide the number of the task.");
        }
    }

    public static void handleMarkTask(String taskDescription, TaskList taskList) throws KenException {
        try {
            int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.getTaskDescriptions().size()) {
                if (!taskList.getTaskDoneStatus().get(taskIndex)) {
                    taskList.getTaskDoneStatus().set(taskIndex, true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" [X] " + taskList.getTaskDescriptions().get(taskIndex) + (taskList.getTaskDates().get(taskIndex) != null ? taskList.getTaskDates().get(taskIndex) : ""));
                } else {
                    System.out.println("This task is already marked as done.");
                }
            } else {
                throw new TaskNotFoundException("I can't find this task. Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException("Wrong format!! Please provide the number of the task.");
        }
    }

}

