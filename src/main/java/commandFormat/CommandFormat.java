package commandFormat;


public class CommandFormat {
    /**
     * transform to smaller letter, removing leading and ending space, and contract any consecutive space
     * @param cmd user command
     * @return updated command
     */
    public static String formattedCommand(String cmd){
        cmd = cmd.trim().toLowerCase();
        cmd = cmd.replaceAll("\\s+", " ");

        return cmd;
    }

    /**
     * raises exception when taskNum is not number, or containing non-numerical value
     */
    public static int getTaskNo(String taskNum) {
        return Integer.parseInt(taskNum);
    }

    /**
     * use to tackle cases with valid starting input like "todo", "event", "list",
     * but lacking in index or having extra index
     */
    public static boolean missingOrExtraTaskDescription(String[] cmd){
        if (cmd.length == 1){
            if(cmd[0].equals("todo") || cmd[0].equals("event") || cmd[0].equals("deadline")
                    || cmd[0].equals("mark") || cmd[0].equals("unmark") || cmd[0].equals("delete")
                    || cmd[0].equals("find")){
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
