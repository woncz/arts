/*
Copyright [2017]
 *
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 *
  http://www.apache.org/licenses/LICENSE-2.0
 *
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package _242_validanagram;

/**
 * @author woncz
 * @date 2019/10/23
 */
public class _242 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isAnagram("abc", "bac"));
        System.out.println(s.isAnagram("abc", "bat"));
    }
}

/**
 * 数组下标映射法
 * asymptotic time complexity : O(N)
 * asymptotic space complexity : O(1)
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return s == t;
        }
        int[] cache = new int[26];
        for(char c : s.toCharArray()) {
            cache[(c - 'a')]++;
        }
        for (char c : t.toCharArray()) {
            cache[(c - 'a')]--;
        }
        for (int i = 0; i < cache.length; i++) {
            if (cache[i] != 0) return false;
        }
        return true;
    }
}

/**
 * sort-compare
 * time complexity : O(NlogN)
 */
