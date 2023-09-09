import java.util.Arrays;
import java.util.Scanner;
public class Torchie {

    private static Task[] taskStore = new Task[100];
    private static int numOfTasks = 0; // keep track of next null space in array

    public static Task[] getTaskStore() {
        return taskStore;
    }

    public static void setTaskStore(Task t) {
        taskStore[numOfTasks] = t;
        numOfTasks += 1;
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i=0; i<taskStore.length; i++){
            if (taskStore[i] == null){
                // only print non-null tasks
                break;
            }
            System.out.print( (i+1) + ".");
            taskStore[i].printTask(taskStore[i].toString());
        }
    }

    public static void announceListSize() {
        System.out.println("Now you have " + numOfTasks + " task(s) in the list.");
    }

    public static String getContent(String s) {
        // split sentence into 2 parts, first word and everything else
        String[] words = s.split(" ",2);

        // making sure content stops before the key characters such as /
        String content = words[1];

        if (content.indexOf('/') != -1) {
            int keyWordIndex = content.indexOf('/');
            content = content.substring(0,keyWordIndex-1);
        }

        return content;
    }

    public static String getDeadlineDate(String s) {
        int SIZE_OF_BUFFER = 4;
        int keyWordIndex = s.indexOf('/');
        return s.substring(keyWordIndex + SIZE_OF_BUFFER);
    }

    public static String getEventStart(String s) {
        int SIZE_OF_BUFFER = 6;

        // first occurrence of '/' character
        int startTimeIndex = s.indexOf('/');

        // second occurrence of '/' character
        int endTimeIndex = s.indexOf('/', startTimeIndex+1);
        return s.substring(startTimeIndex + SIZE_OF_BUFFER, endTimeIndex-1);
    }

    public static String getEventEnd(String s) {
        int SIZE_OF_BUFFER = 4;

        // first occurrence of '/' character
        int startTimeIndex = s.indexOf('/');

        // second occurrence of '/' character
        int endTimeIndex = s.indexOf('/', startTimeIndex+1);
        return s.substring(endTimeIndex + SIZE_OF_BUFFER);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Torchie!");
        System.out.println("What can I do for you?");
        System.out.println("Let's play storetorchie today! You say something and I ll store it!");

        String input;
        int itemNum;

        do {
            input = scanner.nextLine();
            String firstWord = input.split(" ")[0];

            switch(firstWord) {
            case "list":
                showList();
                break;
            case "mark":
//                itemNum = Integer.parseInt(input.split(" ")[1]) - 1;
                itemNum = Integer.parseInt(getContent(input)) - 1;
                taskStore[itemNum].markItem();
                break;
            case "unmark":
                itemNum = Integer.parseInt(getContent(input)) - 1;
                taskStore[itemNum].unmarkItem();
                break;
            case "bye":
                System.out.println("Awww bye :( Let's play again soon!");
                break;
            case "todo":
                ToDos td = new ToDos(getContent(input));
                setTaskStore(td);
                td.announceTaskAdd();
                announceListSize();
                break;
            case "deadline":
                Deadlines d = new Deadlines(getContent(input), getDeadlineDate(input));
                setTaskStore(d);
                d.announceTaskAdd();
                announceListSize();
                break;
            case "event":
                Events e = new Events(getContent(input), getEventStart(input), getEventEnd(input));
                setTaskStore(e);
                e.announceTaskAdd();
                announceListSize();
                break;
            default:
                Task t = new Task(input);
                setTaskStore(t);
                System.out.println("added: " + input);
            }

        } while (!input.equals("bye"));

    }


}
