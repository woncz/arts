/*
 * Copyright [2017]
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

package _015_3sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        Solution1 s1 = new Solution1();
        System.out.println(s1.threeSum(nums));

        Solution2 s2 = new Solution2();
        System.out.println(s2.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(s2.threeSum(new int[]{0, 0, 0}));

        Solution3 s3 = new Solution3();
        System.out.println(s3.threeSum(new int[] {-2,0,1,1,2}));
    }
}

// O(N^3)
class Solution1 {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<String> cache = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] == -nums[k]) {
                        int max = nums[i];
                        int min = nums[i];
                        if (nums[j] > max) {
                            max = nums[j];
                        } else if (nums[j] < min) {
                            min = nums[j];
                        }
                        if (nums[k] > max) {
                            max = nums[k];
                        } else if (nums[k] < min) {
                            min = nums[k];
                        }
                        String key = max + ":" + min;
                        if (!cache.contains(key)) {
                            cache.add(key);
                            res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        }
                    }
                }
            }
        }
        return res;
    }
}

// O(N^2)
class Solution2 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return Collections.emptyList();
        Map<Integer, List<Integer>> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (cache.containsKey(nums[i])) {
                cache.get(nums[i]).add(i);
            } else {
                List<Integer> indexs = new LinkedList<>();
                indexs.add(i);
                cache.put(nums[i], indexs);
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        Set<String> repeat = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int c = - nums[i] - nums[j];
                List<Integer> idxs = cache.get(c);
                if (idxs != null && idxs.get(idxs.size() - 1) > j && !repeated(repeat, nums[i], nums[j], c)) {
                    res.add(Arrays.asList(nums[i], nums[j], c));
                }
            }
        }

        return res;
    }

    private boolean repeated(Set<String> cache, int a, int b, int c) {
        int max = a;
        int min = a;
        if (b > max) {
            max = b;
        } else if (b < min) {
            min = b;
        }
        if (c > max) {
            max = c;
        } else if (c < min) {
            min = c;
        }
        String key = max + ":" + min;
        if (cache.contains(key)) {
            return true;
        }
        cache.add(key);
        return false;
    }
}

// O(N^2)
class Solution3 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return Collections.emptyList();

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // tuning
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] > 0) {
                    break;
                }
                // process same value for avoiding repeat
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    j++; continue;
                }
                long sum = 0L + nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++; k--;
                }
            }
        }
        return res;
    }
}