package chat0pt.helper;

public class FormatCheck {
    public static boolean deadlineFormat(String[] tokens){
        int position = 0;
        boolean byExists = false;
        for(String str:tokens){
            if(str.equals("/by")){
                position += 1;
                byExists = true;
                break;
            }
            position += 1;
        }
        System.out.println(byExists);
        System.out.println(position <= tokens.length);
        return byExists && (position <= tokens.length);
    }
    public static boolean eventFormat(String[] tokens){
        int fromPosition = 0;
        int toPosition = 0;
        int position = 0;
        boolean fromExists = false;
        boolean toExists = false;
        for(String str:tokens){
            if(str.equals("/from")){
                fromPosition = position + 1;
                fromExists = true;
            }else if(str.equals("/to")){
                toPosition = position;
                toExists = true;
            }
            position += 1;
        }
        return fromExists && toExists && fromPosition != toPosition && toPosition+1 <= tokens.length;
    }

}
