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

package _226_invertbinarytree;

/**
Created by woncz on 9/16/2020.
 */
public class _226 {
    public static void main(String[] args) {

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

interface ISolution {
    TreeNode invertTree(TreeNode root);
}

class Solution implements ISolution {
    @Override
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        _process(root);
        return root;
    }

    void _process(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        _process(root.left);
        _process(root.right);
    }
}
