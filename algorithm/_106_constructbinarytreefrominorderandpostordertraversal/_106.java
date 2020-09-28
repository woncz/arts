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

package _106_constructbinarytreefrominorderandpostordertraversal;

import java.util.HashMap;
import java.util.Map;

/**
Created by woncz on 9/25/2020.
 */
public class _106 {
    public static void main(String[] args) {
        int[] inorder = new int[] {9,3,15,20,7};
        int[] postorder = new int[] {9,15,7,20,3};

        ISolution solution = new Solution();
        TreeNode root = solution.buildTree(inorder, postorder);
        System.out.println(root);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

interface ISolution {
    TreeNode buildTree(int[] inorder, int[] postorder);
}

/**
 * 与offer07类似
 */
class Solution implements ISolution {
    @Override
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) return null;
        if (inorder.length != postorder.length) throw new IllegalArgumentException("invalid input data");
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            cache.put(inorder[i], i);
        }
        return this._build(0, inorder.length - 1, inorder, 0, postorder.length - 1, postorder, cache);
    }

    TreeNode _build(int il, int ir, int[] inorder, int pl, int pr, int[] postorder, Map<Integer, Integer> cache) {
        // terminator
        TreeNode root = new TreeNode(postorder[pr]);
        if (pl == pr) {
            if (il == ir && inorder[il] == postorder[pr]) {
                return root;
            } else {
                throw new IllegalArgumentException("invalid input data");
            }
        }

        // drill down
        // find root in inorder
        int shift = cache.get(root.val) - il;

        // left sub tree
        if (shift > 0) {
            root.left = _build(il, il + shift - 1, inorder, pl, pl + shift - 1, postorder, cache);
        }
        // right sub tree
        if (shift < pr - pl) {
            root.right = _build(il + shift + 1, ir, inorder, pl + shift, pr - 1, postorder, cache);
        }

        return root;
    }

}