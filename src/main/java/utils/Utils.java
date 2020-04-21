package utils;

public class Utils {

    public static String trimByOne(String str) {
        if (str.length() < 2)
            return str;
        return str.substring(1, str.length() - 1);
    }
}
