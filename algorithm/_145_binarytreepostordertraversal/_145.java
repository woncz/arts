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

package _145_binarytreepostordertraversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
Created by woncz on 9/29/2020.
 */
public class _145 {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.right = n2;
        n2.left = n3;

        ISolution solution = new Solution();
        List<Integer> ret = solution.postorderTraversal(n1);
        System.out.println(ret);

        solution = new Solution2();
        ret = solution.postorderTraversal(n1);
        System.out.println(ret);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

interface ISolution {
    List<Integer> postorderTraversal(TreeNode root);
}

class Solution implements ISolution {
    List<Integer> ans = new LinkedList<>();
    @Override
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return ans;
        this._dfs(root);
        return ans;
    }

    void _dfs(TreeNode root) {
        if (root == null) return;

        _dfs(root.left);
        _dfs(root.right);

        ans.add(root.val);
    }
}

class Solution2 implements ISolution {
    @Override
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;

        Stack<TreeNode> s = new Stack<>();
        s.add(root);
        while (!s.isEmpty()) {
            TreeNode p = s.peek();
            if (p.left == null && p.right == null) {
                p = s.pop();
                ans.add(p.val);
                continue;
            }
            if (p.right != null) {
                s.push(p.right);
            }
            if (p.left != null) {
                s.push(p.left);
            }
        }
        return ans;
    }
}
