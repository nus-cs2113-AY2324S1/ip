package main;

import ascii.AsciiArt;
import data.LoadData;
import data.StoreData;
import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        System.out.println("Hello I'm Rias-chan!");
        System.out.println(picture);
        System.out.println("Welcome back goshujin-sama, what can I do for you?");
        System.out.println();
        ResponseProcessor processor = new ResponseProcessor();
        LoadData.load(processor);
        waitForResponse(processor);
        StoreData.store(processor);
        System.out.println("Bye masta! " + AsciiArt.getArt("kiss"));
    }
    public static void waitForResponse(ResponseProcessor processor) {
        Scanner scanner = new Scanner(System.in);
        String response;
        do {
            response = scanner.nextLine();
            if (!"bye".equalsIgnoreCase(response)) {
                System.out.println(AsciiArt.getArt("line"));
                processor.process(response);
                System.out.println(AsciiArt.getArt("line"));
            }
        } while (!"bye".equalsIgnoreCase(response));

        scanner.close();
    }

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
