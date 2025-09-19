package _105_constructbinarytreefrompreorderandinordertraversal;

import java.util.HashMap;
import java.util.Map;

public class _105 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode treeNode = solution.buildTree(preorder, inorder);
        System.out.println(treeNode);
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
    TreeNode buildTree(int[] preorder, int[] inorder);
}

class Solution implements ISolution {

    Map<Integer, Integer> stat = new HashMap<>();

    @Override
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            stat.put(inorder[i], i);
        }
        return dfs(preorder, 0, n, 0, n);
    }

    TreeNode dfs(int[] preorder, int pl, int pr, int il, int ir) {
        if (pl >= pr) {
            return null;
        }
        int leftSize = stat.get(preorder[pl]) - il;
        TreeNode left  = dfs(preorder, pl + 1, pl + 1 + leftSize, il, il + leftSize);
        TreeNode right = dfs(preorder, pl + 1 + leftSize, pr, il + leftSize + 1, ir);
        return new TreeNode(preorder[pl], left, right);
    }
}
