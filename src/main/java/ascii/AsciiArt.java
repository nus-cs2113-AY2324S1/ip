package ascii;

import commands.*;

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

    public static String getArt(String str){
        if(isEnabled){
            return art.get(str);
        }
        return "";
    }

    public static void setEnabled(boolean enable){
        isEnabled = enable;
        System.out.println("ascii art is now " + enable + "!");
    }
}
