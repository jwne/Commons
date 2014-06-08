package in.parapengu.commons.utils;

public class StringUtils {
    
    public static String phoneticStringArray(String[] a) {
        String s = "";
        int done = 0;
        for (String m : a) {
            done++;
            if (done == a.length && done != 1) {
                s = s + " and " + m;
            } else if (done == 1) {
                s = m;
            } else {
                s = s + ", " + m;
            }
        }
        return s;
    }
    
}
