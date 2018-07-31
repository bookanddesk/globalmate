package com.globalmate.uitl;

import com.google.common.base.Joiner;

/**
 * @author XingJiajun
 * @Date 2018/7/12 14:52
 * @Description
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static <E> String join_ (E... els) {
        return Joiner.on("_").join(els);
    }

    public static float matchRatio(String str, String target) {
        return 1 - (float) compare(str, target) / Math.max(str.length(), target.length());
    }

    public static int compare(String str, String target) {
        int d[][];
        int n = str.length();
        int m = target.length();
        int i;
        int j;
        char ch1;
        char ch2;
        int temp;
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) {
            ch1 = str.charAt(i - 1);
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    public static boolean isMobileDevice(String userAgent) {
        if (StringUtils.isBlank(userAgent)) {
            return false;
        }
        boolean isMobileDev = false;
        String[] deviceArray = new String[]{"android","mac os","windows phone"};
        for(int i=0;i<deviceArray.length;i++){
            if(userAgent.indexOf(deviceArray[i])>0){
                isMobileDev = true;
                break;
            }
        }
        return isMobileDev;
    }

}
