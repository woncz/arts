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

package _530_minimumabsolutedifferenceinbst;

/**
Created by woncz on 10/12/2020.
 */
public class _530 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(33);
        TreeNode t3 = new TreeNode(20);

        t1.right = t2;
        t2.left = t3;

        ISolution solution = new Solution();
        System.out.println(solution.getMinimumDifference(t1));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

interface ISolution {
    int getMinimumDifference(TreeNode root);
}

/**
 * 通过中序遍历，挨个比对
 */
class Solution implements ISolution {

    int diff = Integer.MAX_VALUE;

    int pre = -1;

    @Override
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return diff;
    }

    void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        int cur = root.val;
        if (pre >= 0) {
            diff = Math.min(diff, Math.abs(cur - pre));
        }
        pre = root.val;

        inorder(root.right);
    }
}