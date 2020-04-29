/*
 * Copyright (C) 2014-2020 Nuhtech Technology(Beijing) Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of Nuhtech Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with Nuhtech inc.
 *
 */

package _005_longestpalindrome;

/**
 * 5. Longest Palindromic Substring
 * 最长回文子串
 *
 * 回文：轴对称、中心对称
 *
 * @author woncz
 * @date 8/14/2019.
 */
public class _005 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String ts = "bbb";
        System.out.println(ts + " vs " + s.longestPalindrome(ts));
        ts = "babad";
        System.out.println(ts + " vs " + s.longestPalindrome(ts));
        ts = "cbbd";
        System.out.println(ts + " vs " + s.longestPalindrome(ts));
        ts = "a";
        System.out.println(ts + " vs " + s.longestPalindrome(ts));
        ts = "abb";
        System.out.println(ts + " vs " + s.longestPalindrome(ts));
        ts = "abcdedcba";
        System.out.println(ts + " vs " + s.longestPalindrome(ts));

        ts = "abcdedcba";
        System.out.println(ts + " vs " + s.longestPalindrome(ts));

        String w = "Distributed locks are a very useful primitive in many environments where different processes must operate with shared resources in a mutually exclusive way";
        for (String t : w.split(" ")) {
            System.out.println(t + " vs " + s.longestPalindrome(t));
        }
    }
}

interface ISolution {
    String longestPalindrome(String s);
}

/**
 * 选中一个pivot, 判断是否基于pivot轴对称
 * time complexity : O(N^2)
 */
class Solution implements ISolution {
    @Override
    public String longestPalindrome(String s) {
        int max = 0;
        int cut = s.length() * 2 + 1;

        String r = "";

        for (int mid = 2; mid < cut; mid++) {
            String tmp = "";
            int side = mid / 2;
            int len = 0;
            // even (mid points to a character not a gap)
            if (mid % 2 == 0) {
                len = 1;
                for (int i = 0; i < side; i++) {
                    int left = (mid - i * 2) / 2 - 1;
                    int right = (mid + i * 2) / 2 - 1;
                    if (left < 0 || right >= s.length()|| s.charAt(left) != s.charAt(right)) {
                        break;
                    }
                    if (left != right) {
                        len += 2;
                    }
                    tmp = s.substring(left, right + 1);
                }
            } else {
                len = 0;
                for (int i = 0; i < side; i++) {
                    int left = (mid - 1 - i * 2) / 2 - 1;
                    int right = (mid + 1 + i * 2) / 2 - 1;
                    if (left < 0 || right >= s.length() || s.charAt(left) != s.charAt(right)) {
                        break;
                    }
                    len += 2;
                    tmp = s.substring(left, right + 1);
                }
            }
             /**
            for (int i = 0; i < side; i++) {
                int left = (mid - i * 2) / 2 - 1;
                int right = (mid + i * 2) / 2 - 1;
                if (mid % 2 == 1) {
                    left = (mid - 1 - i * 2) / 2 - 1;
                    right = (mid - 1 + i * 2) / 2 - 1;
                }
                if (left < 0 || right >= s.length()|| s.charAt(left) != s.charAt(right)) {
                    break;
                }
                if (left != right) {
                    len += 2;
                } else {
                    len += 1;
                }
                tmp = s.substring(left, right + 1);
            }
             */
            if (len > max) {
                max = len;
                r = tmp;
            }
        }
        return r;
    }
}

/**
 * DP
 */
class Solution2 implements ISolution {
    @Override
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;

        int N = s.length();
        int max = 1;
        int left = 0;
        boolean[][] dp = new boolean[N][N];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (dp[i - 1][j + 1] || i - j <= 2) {
                        dp[i][j] = true;
                        if (i - j + 1 > max) {
                            left = j;
                            max = i - j + 1;
                        }
                    }
                }
            }
        }

        return s.substring(left, left + max);
    }

    public static void main(String[] args) {
        String s = "cbbd";
        ISolution ss = new Solution2();
        String ans = ss.longestPalindrome(s);
        System.out.println(ans);

        s = "babad";
        ans = ss.longestPalindrome(s);
        System.out.println(ans);
    }
}