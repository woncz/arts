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

package _404_sumofleftleaves;

/**
Created by woncz on 9/20/2020.
 */
public class _404 {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);

        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        ISolution solution = new Solution();
        System.out.println(solution.sumOfLeftLeaves(n1));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

interface ISolution {
    int sumOfLeftLeaves(TreeNode root);
}

class Solution implements ISolution {

    @Override
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        int s = 0;
        TreeNode left = root.left, right = root.right;
        if (left != null) {
            // leaf node
            if (left.left == null && left.right == null) {
                s += left.val;
            } else {
                s += this.sumOfLeftLeaves(left);
            }
        }
        if (right != null) {
            s += this.sumOfLeftLeaves(right);
        }
        return s;
    }
}