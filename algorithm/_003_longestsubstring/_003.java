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

package _003_longestsubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author woncz
 * @date 7/30/2019.
 */
public class _003 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstring("abba"));
    }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int max = 1;
        Map<Character, Integer> prefix = new HashMap<>(s.length());
        for (int i = 0, repeatIndex = 0; i < s.length(); i++) {
            Character current = s.charAt(i);
            if (prefix.containsKey(current)) {
                repeatIndex = Math.max(prefix.get(current), repeatIndex);
            }
            max = Math.max(max, i - repeatIndex + 1);
            prefix.put(current, i + 1);

            System.out.println("current=" + current + ", repeatIndex=" + repeatIndex + ", max=" + max);
        }

        return max;
    }
}
