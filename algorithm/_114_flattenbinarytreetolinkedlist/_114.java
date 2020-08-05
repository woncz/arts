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

package _114_flattenbinarytreetolinkedlist;

import java.util.Stack;

/**
 Created by woncz on 8/2/2020.
 */
public class _114 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t2.left = t3;
        t2.right = t4;
        t5.right = t6;
        t1.left = t2;
        t1.right = t5;

        ISolution solution = new Solution();
        solution.flatten(t1);
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
    void flatten(TreeNode root);
}

class Solution implements ISolution {
    @Override
    public void flatten(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode last = root;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur != root) {
                last.right = cur;
                last = cur;
            }
            cur.left = null;
        }
    }
}

/**
 * key point: the adjusting node is not the current node, however transverse to the end will correct all nodes
 * be care whether keep the left child or the right child
 */
class Solution2 implements ISolution {
    @Override
    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode next = cur.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = cur.right;
                cur.left = null;
                cur.right = next;
            }
            cur = cur.right;
        }
    }
}
