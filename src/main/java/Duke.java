import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void printLine() {
        final String line = "__________________________________________";
        System.out.println(line);
    }
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Chat0PT");
        System.out.println("What can I do for you?");
        printLine();
        ArrayList<Task> currentTask = new ArrayList<>();
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] splitInput = input.split(" ");
            printLine();
            switch (splitInput[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                return;
            case "list":
                int i = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task tt : currentTask) {
                    System.out.println(i + ". " + tt.toString());
                    i++;
                }
                printLine();
                break;
            case "todo":
                StringBuilder newStr = new StringBuilder();
                System.out.println(splitInput.length);
                if (splitInput.length == 1) {
                    printLine();
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    printLine();
                    break;
                }
                for (int j = 1; j < splitInput.length; j++) {
                    newStr.append(" ").append(splitInput[j]);
                }
                Task tt = new Todo(newStr.toString());
                currentTask.add(tt);
                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("[T][ ]" + newStr);
                System.out.println("Now you have " + currentTask.size() + " tasks in your list.");
                printLine();
                break;
            case "deadline":
                boolean byreached = false;
                StringBuilder newDeadlineStr = new StringBuilder();
                StringBuilder deadline = new StringBuilder();
                if (splitInput.length == 1) {
                    printLine();
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    printLine();
                    break;
                }
                for (String word : splitInput) {
                    if (!word.equals("/by") && !byreached) {
                        newDeadlineStr.append(" ").append(word);
                    } else {
                        byreached = true;

                    }
                    if (byreached) {
                        if (!word.equals("/by")) {
                            deadline.append(" ").append(word);
                        }
                    }
                }
                Deadline newDeadlineTask = new Deadline(newDeadlineStr.toString(), deadline.toString());
                currentTask.add(newDeadlineTask);
                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println(newDeadlineTask);
                System.out.println("Now you have " + currentTask.size() + " tasks in your list.");
                printLine();
                break;
            case "event":
                int k = 1;
                String currentWord = "";
                if (splitInput.length == 1) {
                    printLine();
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    printLine();
                    break;
                }
                StringBuilder event = new StringBuilder();
                StringBuilder from = new StringBuilder();
                String to = "";
                while (k < splitInput.length) {
                    currentWord = splitInput[k];
                    if (currentWord.equals("/from")) {
                        while (!currentWord.equals("/to")) {
                            k++;
                            currentWord = splitInput[k];
                            if (currentWord.equals("/to")) {
                                continue;
                            }
                            from.append(" ").append(currentWord);
                        }
                        k++;
                        to = splitInput[k];
                    } else {
                        event.append(" ").append(currentWord);
                        k++;
                    }
                }
                Event e = new Event(event.toString(), from.toString(), to);
                currentTask.add(e);
                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println(e);
                System.out.println("Now you have " + currentTask.size() + " tasks in your list.");
                printLine();
                break;
            default:
                String[] splitString = input.split(" ");
                if (splitString.length > 1) {
                    if (splitString[0].equals("mark")) {
                        int num = Integer.parseInt(splitString[1]) - 1;
                        if (num < currentTask.size()) {
                            currentTask.get(num).setMarked(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[X] " + currentTask.get(num).getTasks());
                        } else {
                            System.out.println("Are you sure that task exists?");
                        }
                        printLine();
                    } else if (splitString[0].equals("unmark")) {
                        int num = Integer.parseInt(splitString[1]) - 1;
                        if (num < currentTask.size()) {
                            currentTask.get(num).setMarked(false);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[ ] " + currentTask.get(num).getTasks());
                        } else {
                            System.out.println("Are you sure that task exists?");
                        }
                        printLine();
                    } else {
                        printLine();
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        printLine();
                    }
                } else {
                    Task t = new Task(input);
                    currentTask.add(t);
                    System.out.println("added: " + input);
                    printLine();
                    break;
                }

            }


        }
    }
}
