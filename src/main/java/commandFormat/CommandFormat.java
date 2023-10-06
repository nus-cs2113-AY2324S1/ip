package commandFormat;


public class CommandFormat {

    public static String formattedCommand(String cmd){
        cmd = cmd.trim().toLowerCase(); //small letter and remove front and back space
        cmd = cmd.replaceAll("\\s+", " ");   //remove if consecutive space

        return cmd;
    }

    public static int getTaskNo(String taskNum) {
        //exception: taskNum is not number, or containing non-numerical value
        return Integer.parseInt(taskNum);
    }

    //To tackle cases of invalid input like 'todo', 'event', etc.
    public static boolean missingOrExtraTaskDescription(String[] cmd){
        if (cmd.length == 1){
            if(cmd[0].equals("todo") || cmd[0].equals("event") || cmd[0].equals("deadline")
                    || cmd[0].equals("mark") || cmd[0].equals("unmark") || cmd[0].equals("delete")){
                System.out.println("Please describe your target.");
                return true;
            }
        }
        else if (cmd.length>=2 && cmd[0].equals("list") ){
            System.out.println("Do you mean to see the list? Please try again using 'list'.");
            return true;
        }else if (cmd.length>=2 && cmd[0].equals("save") ){
            System.out.println("Do you mean to save the tasks? Please try again using 'save'.");
            return true;
        }
        return false;
    }


}
