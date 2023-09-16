package rene.tasklist;
import rene.task.Task;
import rene.task.ToDo;
import rene.task.Deadline;
import rene.task.Event;
import rene.exception.ReneExceptions;
import java.util.ArrayList;
public class TaskList {
    private static ArrayList<Task> allTasks = new ArrayList<>(); //array of inputs

    public static void addToTaskList(String input, Task.TaskType taskType){
        switch (taskType){
            case TODO:
                try{
                    String toDoDescription = input.split("todo")[1].strip();
                    if(toDoDescription.equals("")){
                        throw new ReneExceptions("Incomplete Command");
                    }
                    allTasks.add(new ToDo(toDoDescription));
                    System.out.println("    I have added the following task OwO:");
                    System.out.printf("      [T][] %s\n", viewTaskByIndex(getTaskListSize()));
                    System.out.println("    Now you have " + getTaskListSize() + " tasks in the list! UWU");
                    System.out.println("    ____________________________________________________________");
                    break;
                }
                catch(ArrayIndexOutOfBoundsException | ReneExceptions incompleteCommand){
                    System.out.println("    Ohnus! You did not use give todo a name!");
                    System.out.println("    Pwease format your input as todo [task name]!");
                    System.out.println("    ____________________________________________________________");
                    return;
                }
            case DEADLINE:
                String deadlineTiming;
                String deadlineDescription;
                String[] deadlineDetails;
                try {
                    deadlineDetails = input.split("deadline")[1].strip().split("/");
                }
                catch(ArrayIndexOutOfBoundsException incompleteCommand){
                    System.out.println("    Ohnus! You did not use give deadline a name!");
                    System.out.println("    Pwease format your input as deadline [task name] /by [time]!");
                    System.out.println("    ____________________________________________________________");
                    return;
                }

                try{
                    deadlineDescription = deadlineDetails[0].strip();
                    if(deadlineDescription.equals("")){
                        throw new ReneExceptions("Incomplete Deadline Description");
                    }
                    deadlineTiming = deadlineDetails[1].strip().split("by")[1].strip();;
                    if(deadlineTiming.equals("")){
                        throw new ReneExceptions("Incomplete Due Time");
                    }
                    allTasks.add(new Deadline(deadlineDescription, deadlineTiming));
                    System.out.println("    I have added the following task OwO:");
                    System.out.printf("      [D][] %s\n", viewTaskByIndex(getTaskListSize()));
                    System.out.println("    Now you have " + getTaskListSize() + " tasks in the list! UWU");
                    System.out.println("    ____________________________________________________________");
                    break;
                }
                catch(IndexOutOfBoundsException incompleteCommand){
                    System.out.println("    Ohnus! You did not use '/by' to signal due time!");
                    System.out.println("    Pwease format your input as deadline [task name] /by [time]!");
                    System.out.println("    ____________________________________________________________");
                    return;
                }
                catch(ReneExceptions incompleteCommand){
                    String exceptionMessage = incompleteCommand.getMessage();
                    switch (exceptionMessage){
                        case "Incomplete Deadline Description":
                            System.out.println("    Ohnus! You did not use give deadline a name!");
                            System.out.println("    Pwease format your input as deadline [task name] /by [time]!");
                            System.out.println("    ____________________________________________________________");
                            return;
                        case "Incomplete Due Time":
                            System.out.println("    Ohnus! You did not use give deadline a due time!");
                            System.out.println("    Pwease format your input as deadline [task name] /by [time]!");
                            System.out.println("    ____________________________________________________________");
                            return;
                        default:
                            return;

                    }
                }
            case EVENT:
                String eventStartTiming = null;
                String eventEndTiming = null;
                String [] eventDetails;
                String eventDescription = null;

                try {
                    eventDetails = input.split("event")[1].strip().split("/");
                }
                catch(ArrayIndexOutOfBoundsException incompleteCommand){
                    System.out.println("    Ohnus! You did not use give event a name!");
                    System.out.println("    Pwease format your input as event [task name] /from [start time] /to [end time]!");
                    System.out.println("    ____________________________________________________________");
                    return;
                }
                try {
                    eventDescription= eventDetails[0].strip();
                    if(eventDescription.equals("")){
                        throw new ReneExceptions("Incomplete Event Description");
                    }
                    eventStartTiming = eventDetails[1].strip().split("from")[1].strip();
                    if(eventStartTiming.equals("")){
                        throw new ReneExceptions("Incomplete Start Time");
                    }
                }
                catch (IndexOutOfBoundsException incompleteCommand) {
                    System.out.println("    Ohnus! You did not use '/from' to signal start time!");
                    System.out.println("    Pwease format your input as event [task name] /from [start time] /to [end time]!");
                    System.out.println("    ____________________________________________________________");
                    return;
                }
                catch(ReneExceptions incompleteCommand) {
                    String exceptionMessage = incompleteCommand.getMessage();
                    switch (exceptionMessage){
                        case "Incomplete Event Description":
                            System.out.println("    Ohnus! You did not use give event a name!");
                            System.out.println("    Pwease format your input as event [task name] /from [start time] /to [end time]!");
                            System.out.println("    ____________________________________________________________");
                            return;
                        case "Incomplete Start Time":
                            System.out.println("    Ohnus! You did not use give event a start time!");
                            System.out.println("    Pwease format your input as event [task name] /from [start time] /to [end time]!");
                            System.out.println("    ____________________________________________________________");
                            return;
                        default:
                            return;
                    }
                }
                try {
                    eventEndTiming = eventDetails[2].strip().split("to")[1].strip();
                    if(eventEndTiming.equals("")){
                        throw new ReneExceptions("Incomplete Start Time");
                    }
                    allTasks.add(new Event(eventDescription, eventStartTiming, eventEndTiming));
                    System.out.println("    I have added the following task OwO:");
                    System.out.printf("      [E][] %s\n", viewTaskByIndex(getTaskListSize()));
                    System.out.println("    Now you have " + getTaskListSize() + " tasks in the list! UWU");
                    System.out.println("    ____________________________________________________________");
                }
                catch(IndexOutOfBoundsException incompleteCommand) {
                    System.out.println("    Ohnus! You did not use '/to' to signal end time!");
                    System.out.println("    Pwease format your input as event [task name] /from [start time] /to [end time]!");
                    System.out.println("    ____________________________________________________________");
                    return;
                }
                catch(ReneExceptions incompleteCommand) {
                    System.out.println("    Ohnus! You did not use give event a end time!");
                    System.out.println("    Pwease format your input as event [task name] /from [start time] /to [end time]!");
                    System.out.println("    ____________________________________________________________");
                    return;
                }
                break;
        }
    }

    public static void printTask(Task task, boolean asList){
        int taskIndex = allTasks.indexOf(task);
        switch(task.getTaskType()) {
            case TODO:
                if (task.taskIsDone()) {
                    if(asList){
                        System.out.printf("    %d: [T][X] %s\n", taskIndex+1, task.getTaskDescription());
                    } else{
                        System.out.printf("        [T][X] %s\n", task.getTaskDescription());
                    }

                } else {
                    if(asList){
                        System.out.printf("    %d: [T][] %s\n", taskIndex+1, task.getTaskDescription());
                    } else{
                        System.out.printf("        [T][] %s\n", task.getTaskDescription());
                    }
                }
                break;
            case DEADLINE:
                if (task.taskIsDone()) {
                    if (asList) {
                        System.out.printf("    %d: [D][X] %s %s\n", taskIndex + 1, task.getTaskDescription(), task.getTaskTiming());
                    } else {
                        System.out.printf("        [D][X] %s %s\n", task.getTaskDescription(), task.getTaskTiming());
                    }
                }
                else {
                    if (asList) {
                        System.out.printf("    %d: [D][] %s %s\n", taskIndex + 1, task.getTaskDescription(), task.getTaskTiming());
                    } else {
                        System.out.printf("        [D][] %s %s\n", task.getTaskDescription(), task.getTaskTiming());
                    }
                }
                break;
            case EVENT:
                if (task.taskIsDone()) {
                    if (asList) {
                        System.out.printf("    %d: [E][X] %s %s\n", taskIndex+1, task.getTaskDescription(), task.getTaskTiming());
                    } else {
                        System.out.printf("        [E][X] %s %s\n", task.getTaskDescription(), task.getTaskTiming());
                    }
                } else {
                    if (asList) {
                        System.out.printf("    %d: [E][] %s %s\n", taskIndex+1, task.getTaskDescription(), task.getTaskTiming());
                    } else {
                        System.out.printf("        [E][] %s %s\n", task.getTaskDescription(), task.getTaskTiming());
                    }
                }
                break;
        }
    }
    public static void printTaskList(){
        if(allTasks.isEmpty()){
            System.out.println("    No tasks found! Time to add some OWO");
        }
        else {
            for (Task task : allTasks) {
                printTask(task, true);
            }
        }
    }
    public static void markTaskAsDone(int index){
        try{
            allTasks.get(index-1).markAsDone();
            Task task = allTasks.get(index-1);
            System.out.println("    Roger that! I have marked the following task as done >w< !");
            printTask(task, false);
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid task number *sobs*");
            System.out.println("    ____________________________________________________________");
        }
    }
    public static void markTaskAsNotDone(int index){
        try{
            allTasks.get(index-1).markAsNotDone();
            Task task = allTasks.get(index-1);
            System.out.println("    Roger that! I have unmarked the following task as done >w< !");
            printTask(task, false);
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid task number *sobs*");
            System.out.println("    ____________________________________________________________");
        }
    }

    public static void deleteTaskByIndex(int index){
        try{
            Task task = allTasks.get(index-1);
            allTasks.remove(index - 1);
            System.out.println("    Roger that! I have deleted the following task >w< !");
            printTask(task, false);
            System.out.println("    Now you have " + getTaskListSize() + " tasks in the list! UWU");
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid task number *sobs*");
            System.out.println("    ____________________________________________________________");
        }
    }

    public static String viewTaskByIndex(int index){
        try{
            switch(allTasks.get(index-1).getTaskType()) {
                case TODO:
                    return allTasks.get(index-1).getTaskDescription();
                case DEADLINE:
                case EVENT:
                    return allTasks.get(index-1).getTaskDescription() + " " + allTasks.get(index-1).getTaskTiming();
                default:
                    return "Task Not Found";
            }
        } catch(NullPointerException | IndexOutOfBoundsException invalidIndex){
            return "Task Not Found";
        }
    }
    public static int getTaskListSize(){
        return allTasks.size();
    }
}