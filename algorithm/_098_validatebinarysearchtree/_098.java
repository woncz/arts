/*
 * Copyright [2020]
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

package _098_validatebinarysearchtree;

public class _098 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        ISolution s = new Solution();
        System.out.println(s.isValidBST(root));


        System.out.println("===================================");

        root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        TreeNode r = root.right;
        r.left = new TreeNode(3);
        r.right = new TreeNode(6);
        System.out.println(s.isValidBST(root));


        System.out.println("===================================");

        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);

        r = root.right;
        r.left = new TreeNode(3);
        r.right = new TreeNode(6);
        System.out.println(s.isValidBST(root));

        System.out.println("===================================");

        root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);

        r = root.left;
        r.left = new TreeNode(0);
        r.right = new TreeNode(2);

        r.right.right = new TreeNode(3);

        r = root.right;
        r.left = new TreeNode(4);
        r.right = new TreeNode(6);
        System.out.println(s.isValidBST(root));
    }
}

interface ISolution {
    boolean isValidBST(TreeNode root);
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution implements ISolution {
    @Override
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean dfs(TreeNode root, long low, long high) {
        // terminator
        if (root == null) return true;

        // logic process
        if (root.val <= low || root.val >= high) return false;

        // drill down
        return dfs(root.left, low, root.val) && dfs(root.right, root.val, high);

        // reset state
    }
}
