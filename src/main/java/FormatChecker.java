/**
 * Check format of User command
 * If there is a problem, throws exceptions
 */

public class FormatChecker {

    /**
     * Raise exception if the string is not numeric
     *
     * @param s given string that have to be checked if numeric
     * @return parsed integer
     */
    public static boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


    /**
     * Raise exception if command argument is not enough
     *
     * @param input given string to be checked
     * @throws InputFormatException if command doesn't have enough arguments
     */
    public static void checkInputFormat(String[] input) throws InputFormatException{
        if(input.length !=2){
            throw new InputFormatException();
        }
    }


    /**
     * check deadline format
     *
     * @param schedules string in deadline format
     * @throws InputFormatException if command is not in deadline format
     */
    /* function for check deadline input format */
    public static void checkDeadlineFormat(String[] schedules) throws InputFormatException{
        if(schedules.length !=2 || !schedules[1].trim().startsWith("by")){
            throw new InputFormatException();
        }
    }

    /**
     * check event format
     *
     * @param schedules string in event format
     * @throws InputFormatException if command is not in event format
     */
    public static void checkEventFormat(String[] schedules) throws InputFormatException{
        if(schedules.length !=3 ||
                !schedules[1].trim().startsWith("from") ||
                !schedules[2].trim().startsWith("to")){
            throw new InputFormatException();
        }
    }
}
