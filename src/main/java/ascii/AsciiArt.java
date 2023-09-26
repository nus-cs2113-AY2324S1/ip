package ascii;

import commands.*;

import java.util.HashMap;

public class AsciiArt {
    public static boolean isEnabled = false;
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
        put("picture", picture);
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
        put("picture", word);
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

    public static final String word = "  _____  _____           _____     ______  \n" +
            " |  __ \\|_   _|   /\\    / ____|   / /___ \\ \n" +
            " | |__) | | |    /  \\  | (___    / /  __) |\n" +
            " |  _  /  | |   / /\\ \\  \\___ \\  < <  |__ < \n" +
            " | | \\ \\ _| |_ / ____ \\ ____) |  \\ \\ ___) |\n" +
            " |_|  \\_\\_____/_/    \\_\\_____/    \\_\\____/ ";

    public static final String picture = "　　　　 　 ,／　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ‐…--､,＿_　　　　　　　　　　　　　　　　　　　　　　　　　　　＼_``=､_　　　　 　 ＼\n" +
            "　　　　 ／　　　　　,,,．--　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　｀`-､_　　　　　　　　　　　　　　　　　　　　　　　　 ＼_ ｀＼__　　　　  ＼　　　　　　　　\n" +
            "　　　 ,/　　　　 ,,／　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ＼　　　　 ＼_　　　　　　　　　　　　　　　　　　　＼_　 `＼　　　  　  ＼　　　　　　\n" +
            "　　,,/　　　　 ,/′　　　　___,,,..-----　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 　 　 ＼　　　　  `＼　　　　　　　　　　　　　　　　　`＼　 ＼_　    　　　＼　　　　　\n" +
            "　,/ﾞ _,,.　 　 /　　 ,-‐‐¨¨´　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ＼　　 　 　 ＼　　　　　　　　　　　　　　　　　　＼   ＼    　　　  ＼　　　　\n" +
            ",,/／´　 　 /　 　 |　　　　　　　　　　　  ,,-‐-　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ＼　　　　　　＼　　　　　　　　　 、　　　　　　　 ＼　　＼　　　      ＼　　　\n" +
            "|'''　　　　 / 　 　 ||　　　　　　　　　 　 /　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ＼　　　　　　＼　　　　　　　　 ＼　　　　　　　＼   　 ＼　　      V　　\n" +
            "　　 　　  ′　 　 |　　　　　　　　　　 /′　　　　　　　　　,　　 ,　　　　　　　　　　　　　　　　　　 ,　　　　　 　 ﾏ＼、　　　　 ＼　　　　　　　　 ＼　　　　　　　　＼　    　＼　　     ∨\n" +
            "　　　　 ,j′　　　|　　　　　　　 　 　 j′　　　　　　　 　 /　 　 |　　　　　　 i|　　　　　　　　　　　||　　　　　　　∨　＼　　　　_}、　　　　　　　　　＼　　　　　　　＼    　  ＼　     V\n" +
            "　 　 　 ||　　　　 ||　　　　　　 　 　 /　　　　　　　 　 　 |　　　 ｛　　　 　 　 ||　　　　　　　　　　　|　　　　　　 　 | 　　＼,-'¨￣ ＼　　　　　　　　　‘＼　　　i、　　　　　∨　 　 ＼　\n" +
            "　　　　||　　　　　|　　　　　　 　 　 |　　　　　　　 　　　j|　　　　|　　　　　　| ｝　　　　　　　　　　 ｛､　　　　　　　||　 ,／＼　　　  V　　　　　　　　　 ＼＼　　Ⅵ　　　　　　∨　　　＼\n" +
            "　　 　 |　　　　　 ||　　　　　　　　 ,|　　　　　　　　　　 ||　　　　 | 　　　　　 || ﾞＶ　　　　　　　　 　 ||＼　　　　　  ]| '′　　＼　　　∨､　　　　　　　　　＼＼　 Ⅵ､　　　　　∨　　　 `\n" +
            "　　　 ,|　　　　 　 |　　　　　 　 　 ||　　　　　　　　　　 ||　　　 　 }_　　 　 　 |　 ∨　　 　　　　　　 ｛　 ＼　　　 ,',''|　　　　＼　 　 ||　＼　　　　　　  　　∨＼　        ∨＼    | 　　　　\n" +
            "　　　 |　　　 　 　 |　　　　　　　　||　　　　　　　　　 　 |　　 　　　｝　　　　　||　 ＼　　 }、　　 　 　 ||　 ＼　　 '′|　　　　　　  ∨　 | 　 ∨　　　　　　　  ∨ ＼　∨　＼　　　 ｛　 　 |　\n" +
            "　　　 |　　　　　　 ||　　　　　　　 :|　　　　　,,　　　　　 |　　　　　　}|　　　 　 ||　　 ＼　 ‘i　　　　　  |　　 ∨　 　 |　　　　　　　}, 　　||　　∨　　　　　　　∨　  ∨ ||　　  ＼  　 |　　　　\n" +
            "　　　 |　　　　　 　 |　　　　　　　｜　　　　 ||　　　　　 ||　　　　 　 |､　　　　  { 　　 ＼　 ‘、　　　　  |　　 ﾞi 　 　 |　　　　　　　 {|　　||　　 ﾟ%　　　　　　| 　 ∨　 |　　  ∨    |　　　　\n" +
            "　　　:|　　　　 　 　 |　　　　 　 　 |　　　 　 |　　　　　　｛　　　　　 :| ＼　　　　∨　___,ﾐx=‐ﾍ　　　　　∨　  } 　　| 　　　　　　　　   ﾐ   |　　　l|＼　　　　　　| 　  v  |　　　∨  ｛　　　\n" +
            "　　   |　　　　 　　　|　　　　 　 　 |　　　　｜　　　 　 　 } 　　　 　 |　 ＼　 __,,斗'¨´　　＼　＼　　　　 ∨　 |ｌ　 |　　　　　　　　　  }}　|| 　　　|  ＼　　　　||   　‘_ | 　  ∨　 |　　　\n" +
            "　　   |　　　　　　　  |　　　　　 　 |　　　　 ｛　　　　　　　ﾞi　　　　｜　 ,,)'r''|'　　 {、　　 　 ＼ ＼　＼　　 ,　　　　　   _,,.-‐‐―[ミ ＼∨    ∨　　　 　    ||     Ⅵ|　    ∨  ｜　　　\n" +
            "　　　 |　　　、　　　　｝　　　 　 　 |　　　 　 |　　　　 　 　 |i_　　 　 |／´　＼　　　＼　　　　　＼_＼　　　 ＼ |'′　  _ __''_'_=ﾆニ三||||- |∨ 　 | 　 ∨　　　 　 |　　　Ⅵ　　 　 |　|　　　\n" +
            "　　　 | 　　　|　　　　 ｛　　　　　　｛　　　 　 |　　　　　　　| ∨　,／｛　　　　＼　　　＼　　　　　  ＼＼_　　 ＼　　  '''´,,r'´／´　　　　  　{| ＼　 ﾄ､　 ＼　 　   |　　|||　　　  | ||　　　\n" +
            "　　　 || 　 　 |　　　　  |　　　　　　｛　　　 　 |　　　　　 　 |　〉''′　｛　　　　　＼　　 ＼　　　　　　　 ｀¨¨`'‐-＼　 ,'/|／　　　　　　　 ||　∨　||＼　∨　　　  ||　　||　　  　　|||　　　\n" +
            "　　 　 | 　 　 |　　　　　｛　　　　 　 ||　　　　 ｛　　　 　　  ,|'′＼　　{|　　　　　　＼、　＼　　　　　　　　　　　　　  /′　　　　　　　　　 ∨  l 　 |　＼ }|　　 　|　　　　　 　　　||　　　　\n" +
            "　　 　 | 　 　 |　　　　　 ‘ 　　　　　 { 　　　　 ｛　　　　 ／ |　　 ＼　 ｝　　　　　　　 ｀＼　＼　　　　　　　　 　　　jj′　　　　　　　　　  | 　 l_　|　　 ＼ 　　　|　　　　　　　　　　　　　\n" +
            "　　　　||　　 :|　　　　　　 } 　　 　 　 ﾞi　　　　　∨　 ／　　｛ 　　　＼  V　　　　　　　　  `＼＼_　　　　　　　　　 ′　　　　　　　　　　 　 ||_　 i  |　　　|　 　 |　　　　　　　　　　　　　\n" +
            "　 　 　 |　　 j 　　　　　　 ‘_　　 　 　 %　　　　　∨　 　　　 ∨　　　 ＼＼　　　　　　　　　　 ￣'　　　　　　　　　　　　　　　　　　　　　　　||`＼|| |　　　　|_　　 |　　　　　　　　　　　　　\n" +
            "　 　 　 | 　 /　　　　　　　　∨　　　　  %　　　　　∨　　　　　%　　　　  ＼_　　　_,,,,-━'¨¨￣三　　　　　　　　　　　　　　　　　　　　　　　 ||　　`|||､_　　　l| 　 |＼_　　　　　　　　　　　\n" +
            "　　　　 |　 |′　　　　　　　　} 　　　　　 V　　　　　∨　　　　  { 　　　　　 ,ｭx≦¨,,=-‐―ニ三=--　　　　　　　　　　　　  -､___　　　　　　 　 |　　  l|　｀`ｰ-ミ}|_ | _　 `'=､_　　　　　　　　\n" +
            "　　 　 |  |　　　　　　　　　　‘ 　　　　　 ＼　　　　  ＼　　　　 ∨　　 r'''´ ,,''''＾',,'''¨￣　　　￣¨　　　　　　　　　　　|　　　　　　 　 |、　　|　　　　　 |||'`ミミx,,__｀`'ｰ-､_　　　　\n" +
            "！　　　||/　　　　　　　　　　　‘_　　　　　　∨　　　　 ＼　　　　 %　　　,,,'''｢_,,´'´　　　　　　　　　　　　　　　　　　　　　 |　　　　　　　 :| ＼_　　　　　 　 ||　　　　　￣¨¨¨¨¨　　　\n" +
            "　｛　　 ,j′　　　　　　　　,,　　　∨　　　　　 ＼　　　　　 ＼　　　 ＼  ''//／　　　　　　　　　　　　　　　　　　　　　　　　   |　　　　 　 　 |　　  ＼　　　　　　　　　　　　　　　　　　　　\n" +
            "　  |　,/　　　　　　　 　 ,j{′　　　∨　　　　　 ＼　　　　 　 ＼　　　 ∨//　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 　 　  ﾄ､　　   ＼_　　　　　　　　　　　　　　　　　　\n" +
            "　　∨　　　　　　　　 ,,''/　　　　　  ∨　　　　　 ＼　　　 　 　 ＼_　　 |{{　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ,/｀`ｰ-､､__＼__　　　　　　　　　　　　　　　\n" +
            "　 ,/　　　　　 　　　,/ /　　 ,j　　　　∨　　　　　　＼　　　　　　　｀＼´　＼　　　　　　　　　　　　　　　　　　　　　　　　　　　,,{、　 　　　/　　　　　　         　　　　　　　　　　　　　\n" +
            ", /　　　　　　 　 ,,''′/　　,j}|　　　　　＼　　　 　　　＼　　　　　　　  ＼_　＼　　　　　　　　　　　　　　　　　　　　  ,,,イ 　'|　　　　,j′　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "′　　　 　 　 ／　 /′ ,/′　　　 　 r'＾＼　　　　　　＼　　　　　 　 　 ＼=､_＼　　　　　　　　　　　　　　　　　　_,,_-_''´　　    | 　 　 ,{　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　　　　　,／　　,/ 　,/′　　　 　 　 |　  ＼　　　　　　＼　　　　　　　　 ∨ ｀`ミﾐ,_　　　　　　　　　　　　　　　￣¨¨`ｰ､__　　 ,|　 　 ,| |　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　　　,,／　　　,/　,,/　/′　　 　 　 ,||　　|＼　　　　　　 ＼　　　　　　　 ＼ 　　　｀　　　　　　　　　　　　　　　　　　｀¨¨¨　   　　/　|　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　  ,,イ{|　　 　 ,/ ／　 /　　　　　　 ,,八|　　  ＼　　　　　 　 ＼　　　　　　　 ＼　　　　　　　　　　　　　　　　　　　　　　　　　　　　j′ |　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "／´　| |　 　 ,/,／　　/　　　 　　　/ 　 |_　　 ｰﾐl、　　　　　　　 ＼　　　　　　 ＼　　　　　　　　　　　　　　　　　　　　　　　　　 　 |′｜　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　  |  |　 ,,/'''′　 /　　　 　 　 / 　　　＼　　　 ＼　　　　　　　　`＼　　　　　　＼__　　　　　　　　　　　　　　　　　　　　　　 　 /　　 |　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　 ||　|_,,l''´　　　/　　　　　　,,'′　　 　 ｝＼　 ､、＼　　　　　　　 　 ＼_　　　　　 ＼¨`ｰ--､.,＿＿_　　　　　　　　　　＿___,,,イ{|　　  |　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　 |　:|′ 　 　 /　　　　　 ／|　　 　 　 |′ ＼､_ `ｰ＼、　　　　　　　　 `ｰ､_　　　　 ＼　　　　　　￣￣厂￣￣￣￣￣￣　 　 |　　｜　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　|　 ||　　　 ,/′　　　 ／　 |　　　 　 ,|　　　　 ｀`ｰ---＼　　　　　　　　　 ｀＼､_　　　 ＼_　　　　 　 ,′　　　　　　　　　　 　 |　 　 |　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　 j′,/　　 ,,/　　　 　 ,/´　　|　　　 　 |　　　　　　　　　　 ﾄ、　　　　　　　　　　　｀＼,,__,,,='~''¨¨¨¨¨¨''ト-､,,___　　　　　　　　　｜　   |　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　j′〈　　,,/　　　 　 ,/′　　j|　　　　 ,|　　　　　　　　　　  | ＼、　　　　　　 __,,,,-‐''¨´　　　　　　　　　　　　｀｀`ｰ､､　　　 　 　 |　　｜　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            ".,|　　　 ,/′ 　 　 ／　　　　 |　 　 　 /　　　　　　　　　　　||　　 ＼__,,,,,=ｧｧ'¨´　　　　　　　　　　　　　　　　　　　　  `＼_　　 　 |　　｜　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "j|　　 ／　　 　 ,／　　 　 　 j′　　 ∧　　　　　　　　 　 　 |-‐‐''¨¨´　 ,/′　　　　　　　　　　　　　　　　　　　　　　　　  ＼　　 ||　　||　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　 ,／　　　 ,／　　　　　 　 |　　　 /′|　　　　　　　　　 　 |　　　　 　 /　　　　　　　　　　　　　　　　　　　　　　　　　　　 　 ＼　||　  |　　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "／　　 _,,.イ　　　　/　　　 /　　　,/　　||　　　　　　　　　　 ||　　　 　 /　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 　 ＼| 　 |　　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　,,,''´　　　　 　 ,| 　 　 ,′　  /　　　 ﾞ、　　　　　　 　 　 |　　　　 /　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　＼ ||　　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　/′　　　　　　 j| 　 　 |　　 ,/　　　　　|　　　　　　　　 　 |　　　 /　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　  Ⅵ　　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            ",/　,/′　　　 　 | 　 　 ,| 　 /　　　 　　　|_　　　　　　　　　||　 　 |′　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　∨　　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　/′　　　　　 /　　　,/ ,／　　　　　　 　 |　　　　　　　　　|　　 /　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　    ∨　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            ",/　　　　 　 　 { 　　,/ ／　　　　　　　　　 ｛ 　　　　　 　 　 | 　/　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　  V　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "′　　　 　 　 /　  /,／　　　　　　　　 　 　 | 　　　　　 　 　 |  |　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　}|　　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　　　 　 　 /　,,,,／　　　　　　　　　　　　　}|　　　　　 　 　 ||′　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ﾞ}　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　　　　 　 ||,,''ﾞ´　　　　　　　　　　　　 　 　 |　　　　　 　 　 ||　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 　| 　　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　　　 　 ,,''′　　　　　　　　　　　　　　　　　{|　　　　　　　　| 　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ||　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　 　　 ,,{′　　　　　　　　　　　　　　　　　　  Ｖ　　　　 　 　 ||　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ｛　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　 　 　 /|　　　　　　　　　　　　　　　　　　　　　||　　　　　　　 ｛　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 　 |,　　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　　 //　　　　　　　　　　　　　　　　　　　　　　ﾞi 　　　　　　　｛　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 　 | 　　　　　　　　　　　　　　　　　　　　　　\n" +
            "　　　'ﾞ ′　　　　　　　　　　　　　　　　　　　　　　ﾞ　　　　　　　 ‘　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　|　　　　　　　　　　　　　　　　　　　　　　\n";
}



