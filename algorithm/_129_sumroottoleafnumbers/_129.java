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

package _129_sumroottoleafnumbers;

/**
Created by woncz on 10/30/2020.
 */
public class _129 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;

        ISolution solution = new Solution();
        int ans = solution.sumNumbers(t1);
        System.out.println(ans);

        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(0);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;

        ans = solution.sumNumbers(n1);
        System.out.println(ans );
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

interface ISolution {
    int sumNumbers(TreeNode root);
}

class Solution implements ISolution {
    int ans = 0;
    @Override
    public int sumNumbers(TreeNode root) {
        ans = 0;
        if (root == null) return 0;
        this._dfs(root, root.val);
        return ans;
    }

    void _dfs(TreeNode root, int sum) {
        // terminator
        if (root.left == null && root.right == null) {
            ans += sum;
            return;
        }

        // drill down
        if (root.left != null) {
            _dfs(root.left, sum * 10 + root.left.val);
        }
        if (root.right != null) {
            _dfs(root.right, sum * 10 + root.right.val);
        }
    }
}