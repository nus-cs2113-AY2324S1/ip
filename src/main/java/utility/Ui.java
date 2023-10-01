package utility;

import exception.FrankException;
import task.Task;
import task.TaskList;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

import static utility.Constants.LOGO;
import static utility.Constants.SOLIDLINE;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Ui {
    public void showTasks(ArrayList<Task> tasks) {
        System.out.print(SOLIDLINE);
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).toString() );
        }
        System.out.print(SOLIDLINE);
    }
    public void showSelectedTasks(ArrayList<Task> tasks, ArrayList<Integer> index) {
        System.out.print(SOLIDLINE);
        if(index.isEmpty()) {
            System.out.println("There was nothing matching the search terms");
        } else {
            System.out.println("Here are the selected tasks: ");
            for (Integer i : index) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.print(SOLIDLINE);
    }
    public void showMarked(Task task) {
        System.out.println(SOLIDLINE + "Tres Bien! I have marked this as " +
                (task.getIsDone() ? "done: " : "not done yet: "));
        System.out.println("[" + task.getStatusIcon() + "] " +
                task.getDescription() + SOLIDLINE);
    }
    public void showDeleted(Task task) {
        System.out.println(SOLIDLINE + "Deleted: " + task + SOLIDLINE);
    }
    public void showAdded(Task task) {
        System.out.println(SOLIDLINE + "Added: " + task + SOLIDLINE);
    }
    public void showGoodbye() {
        System.out.println(SOLIDLINE + "\nGoodbye User, zai jian yong ze, sampai jumpa lagi user \n" + SOLIDLINE);
    }
    public boolean confirmExecute() {
        System.out.println("This will remove all tasks! Are you sure? + " +
                "Type Y to confirm, another key to cancel. ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("y")){
            System.out.println(SOLIDLINE + "Clearing tasks..." + SOLIDLINE);
            return true;
        }
        System.out.println(SOLIDLINE + "Cancelling..." + SOLIDLINE);
        return false;
    }
    public String getTodo() throws FrankException {
        Scanner input = new Scanner(System.in);
        System.out.println("What is your todo?" );
        String description = input.nextLine();
        if(description.isEmpty()) {
            throw new FrankException("Brough this description is missing");
        }
        return description;
    }
    public String[] getDeadline() throws FrankException {
        String[] descDueDate = new String[2];
        Scanner input = new Scanner(System.in);
        System.out.println("Bisa! What is the task?");
        descDueDate[0] = input.nextLine();

        // Get the date
        System.out.println("Ke Yi! When is it due? (In DD-MM-YY format)");
        LocalDateTime formattedDate = parseUserDate("d/MM/yy"); // 02/10/23 for example
        LocalDateTime now = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(now, formattedDate);
        descDueDate[1] = formattedDate.format(DateTimeFormatter.ofPattern("E, d MMM yy")); // Thu, 5 Mar 23

        // Check for invalid input
        if(descDueDate[0].isEmpty() || descDueDate[1].isEmpty()) {
            throw new FrankException("Brough you forgot to fill in something!");
        }
        descDueDate[1] = descDueDate[1] + ", " + daysBetween + " days from now!";
        return descDueDate;
    }
    public String[] getEvent() throws FrankException {
        String[] descStartEnd = new String[3];
        Scanner input = new Scanner(System.in);
        System.out.println("Boleh! What is the event?");
        descStartEnd[0] = input.nextLine();

        // Start Date
        System.out.println("When does it start? (In 11PM/DD/MM/YY Format)");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Now the time is " + now.format(DateTimeFormatter.ofPattern("ha E, d MMM yy")));
        LocalDateTime formattedStartDate = parseUserDate("ha/d/MM/yy"); // 02/10/23 for example
        descStartEnd[1] = formattedStartDate.format(DateTimeFormatter.ofPattern("ha E, d MMM yy")); // 11PM Thu, 08 Mar 23

        // End Date
        System.out.println("When does it end?");
        LocalDateTime formattedEndDate = parseUserDate("ha/d/MM/yy"); // 02/10/23 for example
        descStartEnd[2] = formattedEndDate.format(DateTimeFormatter.ofPattern("ha E, d MMM yy"));  // 11PM Thu, 08 Mar 23

        if(descStartEnd[0].isEmpty() || descStartEnd[1].isEmpty() || descStartEnd[2].isEmpty()) {
            throw new FrankException("Brough you forgot to fill in something!");
        }

        // Time between
        long hoursBetween = ChronoUnit.HOURS.between(formattedStartDate, formattedEndDate);
        descStartEnd[2] = descStartEnd[2] + ", " + hoursBetween + " hours long.";

        return descStartEnd;
    }
    public void showWelcome() {
        System.out.println(SOLIDLINE + LOGO + SOLIDLINE);
        System.out.println("Hello user, I'm FRANK! Nice to meet you!\n" + SOLIDLINE);
    }
    public void showError(String error) {
        System.out.println(SOLIDLINE + error + SOLIDLINE);
    }

    public LocalDateTime parseUserDate(String format) {
        Scanner input = new Scanner(System.in);
        LocalDateTime formattedDate = null;
        boolean valid = true;
        do{
            try {
                String unformattedDate = input.nextLine().toUpperCase();
                formattedDate = LocalDateTime.parse(unformattedDate, DateTimeFormatter.ofPattern(format));
                valid = true;
            } catch(DateTimeParseException e) {
                System.out.println("Frank says: you need to enter in the right format.");
                valid = false;
            }
        } while (!valid);
        return formattedDate;
    }
}