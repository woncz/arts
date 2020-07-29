/*
 Copyright [2020]
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

package _108_convertsortedarraytobinarysearchtree;

/**
 Created by woncz on 7/23/2020.
 */
public class _108 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        ISolution solution = new Solution();
        System.out.println(solution.sortedArrayToBST(nums));

        nums = new int[] {-10,-3,0,5,9};
        System.out.println(solution.sortedArrayToBST(nums));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

interface ISolution {
    TreeNode sortedArrayToBST(int[] nums);
}

class Solution implements ISolution {
    @Override
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return _process(nums, 0, nums.length - 1);
    }

    TreeNode _process(int[] nums, int start, int end) {
        // terminator
        if (start == end) {
            return new TreeNode(nums[start]);
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (mid > start) {
            root.left = _process(nums, start, mid - 1);
        }
        if (mid < end) {
            root.right = _process(nums, mid + 1, end);
        }
        return root;
    }
}
