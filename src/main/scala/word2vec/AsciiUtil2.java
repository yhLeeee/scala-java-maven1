package word2vec;

/**
 * @author yuhanli
 * @date 2019/10/8 3:45 PM
 */
public class AsciiUtil2 {
    public static final char SBC_SPACE = 12288; // 全角空格 12288

    public static final char DBC_SPACE = 32; //半角空格 32

    // ASCII character 33-126 <-> unicode 65281-65374

    public static final char ASCII_START = 33;

    public static final char ASCII_END = 126;

    public static final char UNICODE_START = 65281;

    public static final char UNICODE_END = 65374;

    public static final char DBC_SBC_STEP = 65248; // 全角半角转换间隔

    private static char sbc2dbc(char src){
        if (src == SBC_SPACE) {
            return DBC_SPACE;
        }

        if (src >= UNICODE_START && src <= UNICODE_END) {
            return (char) (src - DBC_SBC_STEP);
        }

        return src;
    }

    public static String sbc2dbcCase(String src){
        if(src == null)
            return null;
        char[] c = src.toCharArray();
        for(int i=0; i<c.length; i++){
            c[i] = sbc2dbc(c[i]);
        }
        return new String(c);
    }
}
