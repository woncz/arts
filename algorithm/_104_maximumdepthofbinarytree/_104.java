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

package _104_maximumdepthofbinarytree;

import lib.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by woncz on 7/28/2020.
 */
public class _104 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        ISolution bfs = new SolutionBFS();
        System.out.println(bfs.maxDepth(root));

        ISolution dfs = new SolutionDFS();
        System.out.println(dfs.maxDepth(root));
    }

}

interface ISolution {
    int maxDepth(TreeNode root);
}


class SolutionBFS implements ISolution {
    @Override
    public int maxDepth(TreeNode root) {
        int height = 0;
        if (root == null) return height;

        Queue<TreeNode> q = new ArrayDeque<>(), sub = new ArrayDeque<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) sub.add(node.left);
            if (node.right != null) sub.add(node.right);
            if (q.isEmpty()) {
                height++;
                Queue tmp = q;
                q = sub;
                sub = tmp;
            }
        }
        return height;
    }
}

class SolutionDFS implements ISolution {
    @Override
    public int maxDepth(TreeNode root) {
        int height = 0;
        if (root == null) return height;

        return _dfs(root, 1);
    }

    int _dfs(TreeNode root, int level) {
        int h = level;
        if (root.left != null) {
            h = Math.max(h, _dfs(root.left, level + 1));
        }
        if (root.right != null) {
            h = Math.max(h, _dfs(root.right, level + 1));
        }
        return h;
    }
}