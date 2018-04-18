package com.xpp.algorithm.string;

/**
 * Created by xp on 2018/4/18.
 */
public class KMP {

    public static void main(String[] args) {
        int kmp = KMP("adfaygauhiakhayahdradtfg", "dfa");
        System.out.println("" + kmp);
    }


    public static int KMP(String ts, String ps) { //找出子串出现在主串中的位置并且返回，如果不存在，返回-1

        //首先求出子串的next数组
        int[] next = getNext(ps);

        //定义主串的指针i与子串的指针j，当出现不匹配的时候，i不回退，只有j回退
        int i, j;
        i = j = 0;
        while (i < ts.length() && j < ps.length()) {

            if (ts.charAt(i) == ps.charAt(j)) {
                ++i;
                ++j;
            } else {
                if (next[j] == -1) {
                    i++;
                } else
                    j = next[j];
            }
            if (j >= ps.length())
                return i - j;
        }
        return -1;
    }

    public static int[] getNext(String str) {

        int len = str.length();
        int[] next = new int[len];
        next[0] = -1;
        int j, k;  //其中j表示字符的位置，k表示此事如果j字符跟主串不匹配的时候，j应该回退的位置（也即就是next[j]的值）
        j = 0;
        k = -1;
        while (j < len - 1) { //此时已知道next[j] = k,通过讨论str[j] 与str[k]的关系，求出next[j+1]的值

            if (k == -1 || str.charAt(j) == str.charAt(k))
                next[++j] = ++k;
            else
                k = next[k];
        }
        return next;
    }


}
