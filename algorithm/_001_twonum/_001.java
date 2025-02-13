package _001_twonum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _001 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {3,2,4};
        int target = 6;
        System.out.println(Arrays.toString(s.twoSum(nums, target)));
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no solution");
    }
}
