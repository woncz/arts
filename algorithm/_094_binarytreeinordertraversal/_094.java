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

package _094_binarytreeinordertraversal;

import _06_consisthash.exam.Tree;

import javax.swing.plaf.ProgressBarUI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
Created by woncz on 9/14/2020.
 */
public class _094 {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.right = n2;
        n2.left = n3;

        ISolution solution = new Solution();
        System.out.println(solution.inorderTraversal(n1));

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;

        solution = new Solution();
        System.out.println(solution.inorderTraversal(t1));

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

interface ISolution {
    List<Integer> inorderTraversal(TreeNode root);
}

class Solution implements ISolution {
    @Override
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        return this._process(root);
    }

    LinkedList<Integer> _process(TreeNode root) {
        if (root == null) return null;

        LinkedList<Integer> left = this._process(root.left);
        LinkedList<Integer> right = this._process(root.right);

        LinkedList<Integer> ans;
        if (left == null && right == null) {
            ans = new LinkedList<>();
            ans.add(root.val);
        } else if (left == null) {
            ans = right;
            ans.addFirst(root.val);
        } else if (right == null) {
            ans = left;
            ans.add(root.val);
        } else {
            ans = left;
            ans.add(root.val);
            ans.addAll(right);
        }
        return ans;
    }
}

