/*
 * Copyright [2017]
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

package _102_binarytreelevelordertraversal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);

        TreeNode node_1_1 = new TreeNode(4);
        TreeNode node_1_2 = new TreeNode(5);

        root.left = node_1_1;
        root.right = node_1_2;

        node_1_1.left = new TreeNode(6);
        node_1_1.right = new TreeNode(7);

        node_1_2.left = new TreeNode(8);
        node_1_2.right = new TreeNode(9);

        Solution2 s2 = new Solution2();
        System.out.println(s2.levelOrder(root));

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

interface ISolution {
    List<List<Integer>> levelOrder(TreeNode root);
}

/**
 * DFS
 */
class Solution1 implements ISolution {

    private Set<TreeNode> visited = new HashSet<>();

    @Override
    public List<List<Integer>> levelOrder(TreeNode root) {
        return null;
    }

    private void dfs(int level, TreeNode node) {

    }
}

/**
 * BFS
 * time complexity : O(N)
 * space complexity : O(N)
 */
class Solution2 implements ISolution {

    @Override
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        bfs(ans, root, 0);
        return ans;
    }

    private void bfs(List<List<Integer>> ans, TreeNode node, int level) {
        // terminator
        if (node == null) return;

        // process logic

        if (ans.size() == level) {
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(node.val);

        // drill down
        bfs(ans, node.left, level + 1);
        bfs(ans, node.right, level + 1);

        // revert state
    }
}