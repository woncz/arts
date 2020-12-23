/*
 * Copyright [2020]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package _316_removeduplicateletters;

import java.util.LinkedList;

/**
 * Created by woncz on 12/23/2020.
 */
public class _316 {
    public static void main(String[] args) {
        String s = "bcabc";
        ISolution solution = new Solution2();
        System.out.println(solution.removeDuplicateLetters(s));

        s = "cbacdcbc";
        System.out.println(solution.removeDuplicateLetters(s));

        s = "acbac";
        System.out.println(solution.removeDuplicateLetters(s));

        s = "bbcaac";
        System.out.println(solution.removeDuplicateLetters(s));
    }
}

interface ISolution {
    String removeDuplicateLetters(String s);
}

class Solution implements ISolution {
    @Override
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;
        // 统计
        int[] stat = new int[26];
        boolean[] visited = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            stat[s.charAt(i) - 'a']++;
        }

        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            stat[s.charAt(i) - 'a']--;
            if (visited[s.charAt(i) - 'a']) continue;

            while (!stack.isEmpty() && stat[stack.peek() - 'a'] > 0 && s.charAt(i) < stack.peek()) {
                visited[stack.pop() - 'a'] = false;
            }
            stack.push(s.charAt(i));
            visited[s.charAt(i) - 'a'] = true;
        }

        StringBuffer sb = new StringBuffer();
        for (Character c : stack) {
            sb.append(c);
        }

        return sb.reverse().toString();
    }
}

class Solution2 implements ISolution {
    @Override
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;
        // 统计
        int[] stat = new int[26];
        boolean[] visited = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            stat[s.charAt(i) - 'a']++;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stat[c - 'a']--;
            if (visited[c - 'a']) continue;
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c && stat[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                visited[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(c);
            visited[c - 'a'] = true;
        }
        return sb.toString();
    }
}
