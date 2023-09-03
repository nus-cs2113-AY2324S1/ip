import java.util.Scanner;

    public class Echo {
        private String bye;
        private String text;

    public Echo() {
        bye ="bye";
    }

    toDoList newList  = new toDoList();

    public void echoBack(){

        Scanner in = new Scanner(System.in);
        String text = in.next();



        if(text.equals(bye)){
            System.out.println("Bye. Hope to see you again soon!");
        }
        else if(text.equals(newList.checker)){
            newList.listTask();
            echoBack();
        }
        else if(text.startsWith(newList.marker)){
            in.useDelimiter("  ");
            String part =in.nextLine();
            newList.mark(text+part);

            echoBack();
        }
        else if(text.startsWith(newList.unmarked)){
            in.useDelimiter("  ");
            String part =in.nextLine();

            newList.unmark(text+part);
            echoBack();
        }
        else if (text.equals("todo")){
            in.useDelimiter("  ");
            String part =in.nextLine();

            newList.addToDo(part);
            echoBack();
        }
        else if (text.equals("deadline")){

            String[] task = in.nextLine().split("/by");
            newList.addDeadline(task[0],task[1]);
            echoBack();
        }
        else if (text.equals("event")){

            String[] task = in.nextLine().split("/from | /to");
            newList.addEvent(task[0],task[1],task[2]);
            echoBack();
        }
        else {

           echoBack();

        }
    }
}
