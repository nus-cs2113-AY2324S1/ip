/* Class that check Format of input*/
public class FormatChecker {

    /* simple function that returns if the string is numeric */
    public static boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /* function for check input format */
    public static void checkInputFormat(String[] input) throws InputFormatException{
        if(input.length !=2){
            throw new InputFormatException();
        }
    }

    /* function for check deadline input format */
    public static void checkDeadlineFormat(String[] schedules) throws InputFormatException{
        if(schedules.length !=2 || !schedules[1].trim().startsWith("by")){
            throw new InputFormatException();
        }
    }

    public static void checkEventFormat(String[] schedules) throws InputFormatException{
        if(schedules.length !=3 ||
                !schedules[1].trim().startsWith("from") ||
                !schedules[2].trim().startsWith("to")){
            throw new InputFormatException();
        }
    }
}
