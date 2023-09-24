package CommandFormat;


public class Command {

    public static String formattedCommand(String cmd){
        cmd = cmd.trim().toLowerCase(); //small letter and remove front and back space
        cmd = cmd.replaceAll("\\s+", " ");   //remove if consecutive space

        return cmd;
    }


}
