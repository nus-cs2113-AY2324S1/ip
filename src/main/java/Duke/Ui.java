package Duke;

public class Ui {
    public void greeting(){
        System.out.println("Hello! I'm Bot Hilary");
        System.out.println("What can I do for you?");
    }

    public void help(){
        System.out.println("This is a task management robot, you can add todo tasks, deadline tasks and event tasks");
        System.out.println("To add todo task, simply input format \"todo task\"");
        System.out.println("To add deadline task, simply input format \"deadline task deadlineTime\"");
        System.out.println("To add event task,the input format \"event task eventTime\"");
        System.out.println("The time format for deadline task will be \"/by dd/MM/yyyy hhmm\" and for event task is \"/from dd/MM/yyyy hhmm /to dd/MM/yyyy hhmm\"");
        System.out.println("Delete a task: enter the command \"delete indexOfTheTask\"");
        System.out.println("Mark a task as done: enter the command \"mark indexOfTheTask\"");
        System.out.println("Unmark a task: enter the command \"unmark indexOfTheTask\"");
        System.out.println("Show what's in the list: enter the command \"list\"");
        System.out.println("Search for tasks by name: enter the command \"find taskName\"");
        System.out.println("Quit: Enter the command \"bye\"");
    }
}
