import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class TaskList {
    private static Task[] allTasks = {}; //array of inputs

    public static void addToTaskList(String input, Task.TaskType taskType){
        Task[] newTaskList = new Task[allTasks.length+1];
        System.arraycopy(allTasks, 0, newTaskList, 0, allTasks.length);
        switch (taskType){
            case TODO:
                try{
                    String toDoDescription = input.split("todo")[1].strip();
                    if(toDoDescription.equals("")){
                        throw new ReneExceptions("Incomplete Command");
                    }
                    newTaskList[allTasks.length] = new ToDo(toDoDescription);
                    allTasks = newTaskList;
                    System.out.println("    I have added the following task OwO:");
                    System.out.printf("      [T][] %s\n", viewTaskByIndex(getTaskListSize() - 1));
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
                    newTaskList[allTasks.length] = new Deadline(deadlineDescription, deadlineTiming);
                    allTasks = newTaskList;
                    System.out.println("    I have added the following task OwO:");
                    System.out.printf("      [D][] %s\n", viewTaskByIndex(getTaskListSize() - 1));
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
                    newTaskList[allTasks.length] = new Event(eventDescription, eventStartTiming, eventEndTiming);
                    allTasks = newTaskList;
                    System.out.println("    I have added the following task OwO:");
                    System.out.printf("      [E][] %s\n", viewTaskByIndex(getTaskListSize() - 1));
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

    public static void printTaskList(){
        for(int i = 0; i<allTasks.length; i++) {
            switch(allTasks[i].getTaskType()) {
                case TODO:
                    if (allTasks[i].taskIsDone()) {
                        System.out.printf("    %d: [T][X] %s\n", i+1, allTasks[i].getTaskDescription());
                    } else {
                        System.out.printf("    %d: [T][] %s\n", i+1, allTasks[i].getTaskDescription());
                    }
                    break;
                case DEADLINE:
                    if (allTasks[i].taskIsDone()) {
                        System.out.printf("    %d: [D][X] %s %s\n", i+1, allTasks[i].getTaskDescription(), allTasks[i].getTaskTiming());
                    } else {
                        System.out.printf("    %d: [D][] %s %s\n", i+1, allTasks[i].getTaskDescription(), allTasks[i].getTaskTiming());
                    }
                    break;
                case EVENT:
                    if (allTasks[i].taskIsDone()) {
                        System.out.printf("    %d: [E][X] %s %s\n", i+1, allTasks[i].getTaskDescription(), allTasks[i].getTaskTiming());
                    } else {
                        System.out.printf("    %d: [E][] %s %s\n", i+1, allTasks[i].getTaskDescription(), allTasks[i].getTaskTiming());
                    }
                    break;
            }
        }
    }
    public static void markTaskAsDone(int index){
        try{
            allTasks[index-1].markAsDone();
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid task number *sobs*");
            System.out.println("    ____________________________________________________________");
        }
    }
    public static void markTaskAsNotDone(int index){
        try{
            allTasks[index-1].markAsNotDone();
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid task number *sobs*");
            System.out.println("    ____________________________________________________________");
        }
    }
    public static String viewTaskByIndex(int index){
        try{
            switch(allTasks[index].getTaskType()) {
                case TODO:
                    return allTasks[index].getTaskDescription();
                case DEADLINE:
                case EVENT:
                    return allTasks[index].getTaskDescription() + " " + allTasks[index].getTaskTiming();
                default:
                    return "Task Not Found";
            }
        } catch(NullPointerException | ArrayIndexOutOfBoundsException invalidIndex){
            return "Task Not Found";
        }
    }
    public static int getTaskListSize(){
        return allTasks.length;
    }
}