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

package _222_countcompletetreenodes;

/**
Created by woncz on 11/24/2020.
 */
public class _222 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        ISolution solution = new Solution();
        System.out.println(solution.countNodes(root));

        solution = new Solution2();
        System.out.println(solution.countNodes(root));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

interface ISolution {
    int countNodes(TreeNode root);
}

class Solution implements ISolution {
    @Override
    public int countNodes(TreeNode root) {
        // terminator
        if (root == null) return 0;

        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

class Solution2 implements ISolution {
    @Override
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int left = level(root.left);
        int right = level(root.right);
        if (left == right) {
            return countNodes(root.right) + (1 << left);
        } else  {
            return countNodes(root.left) + (1 << right);
        }
    }

    int level(TreeNode root) {
        int level = 0;
        TreeNode p = root;
        while (p != null) {
            level++;
            p = p.left;
        }
        return level;
    }
}
