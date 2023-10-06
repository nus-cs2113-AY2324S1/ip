package ascii;
import java.util.HashMap;

public class AsciiArt {
    public static boolean isEnabled = true;
    public static HashMap<String, String> art = new HashMap<>() {{
        put("sad", "(T_T)");
        put("uwu", "(´UwU`)");
        put("good", "d(*^_^*)b");
        put("okay", "(=^・^=)");
        put("kiss", "(*^3^)/~<3");
        put("cry", "(:_;)");
        put("depress", "(.>m<.)");
        put("ready", "(^_^)/");
    }};

    public static HashMap<String, String> emote = new HashMap<>() {{
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

    public static final String line = "________________________________________________________________________________";
    public static final String word = "  _____  _____           _____     ______  \n" +
            " |  __ \\|_   _|   /\\    / ____|   / /___ \\ \n" +
            " | |__) | | |    /  \\  | (___    / /  __) |\n" +
            " |  _  /  | |   / /\\ \\  \\___ \\  < <  |__ < \n" +
            " | | \\ \\ _| |_ / ____ \\ ____) |  \\ \\ ___) |\n" +
            " |_|  \\_\\_____/_/    \\_\\_____/    \\_\\____/ ";

    public static final String picture = "...........;.':...l..  .;.      ....  ;dd;..,....   ...   ......'o.  ..;....l.... ..........'\n" +
            ".''....''.''',:''l:.  ..;.   .  ...  ,odO'.'c...   ...   ...'...co.  ..;. ..c.... ..........'\n" +
            "''....'''.'.';:'cl..  .,.   . . ... ;od0k..l:...   ...   ......;oo.  ..'. .''.... ...........\n" +
            "''....'''...';l:d'.   .;.   .., .. 'ccxO:.,l;..   .,..   ..'..'ooo. ....  ... ...  ..........\n" +
            "'.....'''...';xx;......... ..;'   ,od0NN,,ol,..   ''..   .....cooo. ....  ... ...  ..........\n" +
            ". ....''....',kl...........;'o'  .oo0NNN'coo:.   .o'..  .....'oooo ....  ..'...;.  ..........\n" +
            "  ...........'x,..........';oo,  ccoxkOO.looc.   'o'..  .....coool . .   ..; ..o'  .. .......\n" +
            "  ............:.......... l::'.        ,.:ccc.   ';,.,   ...'oooo:.      .c. .:o'  .. .......\n" +
            "  ......................  .   .         . .,o'   :l;.:.  ...:oooo'      .c; .,dk' ... .......\n" +
            "  ..................  .  ,. 'o  :.     .NWx. ,.  ,oc.c.  ...loooo.     .:c. .:Ok. ..  .......\n" +
            "  ..................  .. :, o'        ''.:,,: ,  .lo.:;  ...looo'     .oxc .xXO:. ... .......\n" +
            "  ..................  .. lddK. ..      .   lkc ' .xd:'o.  ..ooo,     ,cxo..kNNKl  ..'........\n" +
            "  .... ..... ........ .. l0XW..,.     ''.  NNkclo.:Kd'l:.  .lo'    .cxKd.,KNNNOc  .:........ \n" +
            "  ....  .... ........  . lXNNolkd.  .oo:. kWWX0xxO'kKo;o,  .:.   .;;co;  .''lkd: .lc ....... \n" +
            "  ....  ....  .......  . lNNN0 .cxxdxd::.xWWWNNXOkXo0Kkod'  .  .:c...    ;l. .o.,oo, ......  \n" +
            "  ....   ....   ...      cNNNKO;,.,,...:KWWNNNNNNX0NXNXK0d. .'loo;  ''  l:kl:, ;koo....... . \n" +
            " ......  ....   ...   .. 'WWWNNNNXNKKkxxdONNNNNNNNNXNNNNk..'.'kKK.         'xo 'xo, ..... .. \n" +
            " ......   ....    ..   c. ONNNNNNNNNNNNNNNNNNNNNNNNNNN0::xxxl'xN,..    .. cNx;  ol.........  \n" +
            " ..'...    ...         :l.,NNNNNNNNNNNNNNNNNNNNNNNNNNNKNXKNN0XxX'o.   ',.oWWc.l;o. ... ...   \n" +
            " ..''..     ...        .Ko.oNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNl cd,:dlo0WW00XKl.  .. ... .  \n" +
            " ..'''..     ..         :Nx.kNNNNNNNNNNNNNNNNNNNNNNNNNNNXNNNNNNKl;,:;'dWWNNNNN:.    .... ... \n" +
            " ..''..       .          dN0;kNNNNNNNNNNNNNNNNNNNNNNNNN0d0NNNNNNNNKOo;oXNNNNK,     ... ... . \n" +
            " ..''.'.             ..  .kNXdkNNNNNNNNNNNNNNNNNNNNNNNNcoxNNNNNNNNNNNNNNNNNO.     .......... \n" +
            " ..''.''.            .l'  .dNNXXNNNNNNNNNNNNNNNNNNNNNXdckXNNNNNNNNNNNNNNNNd.    ......... .. \n" +
            " ..''.''..           .cxl.  cXNNNNNNNNNNNNNNNNNNNNNNN00XNNNNNNNNNNNNNNNNX:    .......... ... \n" +
            " ..''..''..           lcxOc. '0NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN0. ................ ..\n" +
            " ..''..'''.           ;o:oKkc,.oNNNNNNNNN0KNNNNNNNNNNNNNNNNNNNNNNNNNNNo.  ............ . ....\n" +
            " ..''..'''..          .oo::OXOkol0NNNNNNNNK0OO00OKNNNNNNNNNNNNNNNNNN0,.. ............  ......\n" +
            " ..''..'''...          :ooc;lKXKKXNNNNNNNNNN0OXNNNNNNNNNNNNNNNNNNNKl...  ....................\n" +
            " ..'' ..''...          .looc;;dXNNNNNNNNNNNNNXXNNNNNNNNNNNNNNNXOl'..... .....................\n" +
            " ..'. ..'''..           'oool:;:xXNNNNNNNNNNNNNNNNNNNNNNNNKkl,........  .....................\n" +
            " ..'. ..'''..   ..       ,ooolc;;:kXNNNNNNNNNNNNNNNNNKxl;..... ....... ......................\n" +
            " ..'. ..'''.'.  ..     '. ,cloll:;;:kXNNNNNNNNNN0xc,. .... ... ......  ............ .........\n" +
            " ..'. ..'''.'.  ...    'c,.,ccclll:;,:xKNNX0koc:,.    .... ... ...... ............. .........\n" +
            " ..'.  ..''.'.  ....   .ccc,;ccccccc:;,;lc;,;:;:c;    .... ... ...... ............. .........\n" +
            " ..'.  ..''.... ..'.    ,cccccccc.  .,:::,''::cccc    .... ... .....  .......................\n" +
            " ..'.  ..''..'. ..'..   ..  .,:c.  ..  l;   .c;.'c:.  ....  .. .....  .......................\n" +
            "  .'.   ..'..'. ..''.     ..      .,.  '  .    . .cc. ..... .. ..... ........................\n" +
            "  .'.   ..''.'. ..''..    :cc,                ,c,  ;c     . .. ..... ........................\n" +
            "  ...   ..''.'. ..'''.    .cc'                 ;c;  .          ..... ........................\n" +
            "  ...    ..'.'....'''..    ,,                   'c:.           ..... .................  .....\n" +
            "  ...    ..'.'....''''.                          .;c'           .... ............ ....    ...\n" +
            "  ....   ..'.''..'''''.                            ..           .... ............ .......   .\n" +
            "  ....    ....'.''''''..                                        .... ......................  \n" +
            "  ....    ....'''''''''.                                        ....  .......................\n" +
            "   ...     ...'''''''''.            ..   .                      ..... .......................\n" +
            "   ...     ...'''''''''..           ,.  .,.                      .... .......................\n" +
            "   ....     .............          .,.  .,,                      .... .......................";
}



