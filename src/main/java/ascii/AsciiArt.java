package ascii;
import java.util.HashMap;

public class AsciiArt {
    public static boolean isEnabled = true;
    public static HashMap<String, String> art = new HashMap<>() {{
        put("line", "❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤");
        put("sad", "(つT . T)つ");
        put("uwu", "(´UωU`)");
        put("good", "d(⏜⍢⏜)b");
        put("okay", "╭( ･ㅂ･)و✩");
        put("kiss", "( ˘ ³˘)❤");
        put("cry", "｡･ﾟﾟ･(>д<)･ﾟﾟ･｡");
        put("depress", "(.>ෆ<.)");
        put("ready", "(^-^)ゝ");
    }};

    public static HashMap<String, String> emote = new HashMap<>() {{
        put("line", "________________________________________");
        put("sad", ":9");
        put("uwu", "UwU");
        put("good", ":>");
        put("okay", ":)");
        put("kiss", "<3");
        put("cry", ":'(");
        put("depress", ":<");
        put("ready", "<:a");
    }};

    public static String getArt(String str){
        if(isEnabled){
            return art.get(str);
        }
        return emote.get(str);
    }

    public static void setEnabled(boolean enable){
        isEnabled = enable;
        System.out.println("ascii art is now " + enable + "!");
    }
}
