package chat0pt.helper;

import java.util.StringJoiner;

public class FormatString {
    public static String[] format(String mode, String[] tokens){
        int position = 1;
        StringJoiner formattedString = new StringJoiner(" ");
        String[] stringsToReturn;
        switch(mode){
        case "todo":
            for (int i = 1; i < tokens.length; i++) {
                formattedString.add(tokens[i]);
            }
            stringsToReturn = new String[]{formattedString.toString()};
            break;
        case "deadline":
            stringsToReturn = new String[2];
            for (int i = 1; i < tokens.length; i++){
                if(tokens[i].equals("/by")){
                    position = i+1;
                    break;
                }
                else{
                    formattedString.add(tokens[i]);
                }
            }
            stringsToReturn[0] = formattedString.toString();
            formattedString = new StringJoiner(" ");
            for (int i = position; i < tokens.length; i++){
                formattedString.add(tokens[i]);
            }
            stringsToReturn[1] = formattedString.toString();
            break;
        case "event":
            stringsToReturn = new String[3];
            for (int i = 1; i < tokens.length; i++){
                if(tokens[i].equals("/from")){
                    position = i+1;
                    break;
                }
                else{
                    formattedString.add(tokens[i]);
                }
            }
            stringsToReturn[0] = formattedString.toString();
            formattedString = new StringJoiner(" ");
            for (int i = position; i < tokens.length; i++){
                if(tokens[i].equals("/to")){
                    position = i+1;
                    break;
                }
                else{
                    formattedString.add(tokens[i]);
                }
            }
            stringsToReturn[1] = formattedString.toString();
            formattedString = new StringJoiner(" ");
            for (int i = position; i < tokens.length; i++){
                formattedString.add(tokens[i]);
            }
            stringsToReturn[2] = formattedString.toString();
            break;
        default:
            return null;
        }
        return stringsToReturn;
    }
}
