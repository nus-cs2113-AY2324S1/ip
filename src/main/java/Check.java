public class Check {
     //Function to check if an input has mark as the first word
     public static boolean isMark(String input){
        if(input.length()>=4){
            if("mark".equalsIgnoreCase(input.substring(0,4))){
                return true;
            }
        }
        return false;
    }

    //Function to check if an input has unmark as the first word
    public static boolean isUnmark(String input){
        if(input.length()>=6){
            if("unmark".equalsIgnoreCase(input.substring(0,6))){
                return true;
            }
        }
        return false;
    }

    //Function to check if an input has deadline as the first word
    public static boolean isDeadline(String input){
        if(input.length()>=8){
            if("deadline".equalsIgnoreCase(input.substring(0,8))){
                return true;
            }
        }
        return false;
    }

    //Function to check if an input has event as the first word
    public static boolean isEvent(String input){
        if(input.length()>=5){
            if("event".equalsIgnoreCase(input.substring(0,5))){
                return true;
            }
        }
        return false;
    }

    //Function to check if an input has todo as the first word
    public static boolean isTodo(String input){
        if(input.length()>=4){
            if("todo".equalsIgnoreCase(input.substring(0,4))){
                return true;
            }
        }
        return false;
    }

    //Function to check if an input has list as the first word
    public static boolean isList(String input){
        if(input.length()>=4){
            if("list".equalsIgnoreCase(input.substring(0,4))){
                return true;
            }
        }
        return false;
    }

    //Function to check if an input has bye as the first word
    public static boolean isBye(String input){
        if(input.length()>=3){
            if("bye".equalsIgnoreCase(input.substring(0,3))){
                return true;
            }
        }
        return false;
    }

    //Function to check if an input has delete as the first word
    public static boolean isDelete(String input){
        if(input.length()>=6){
            if("delete".equalsIgnoreCase(input.substring(0,6))){
                return true;
            }
        }
        return false;
    }

}
