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

package _637_averageoflevelsinbinarytree;

import _06_consisthash.exam.Tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
Created by woncz on 9/12/2020.
 */
public class _637 {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        ISolution solution = new Solution();
        System.out.println(solution.averageOfLevels(n1));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

interface ISolution {
    List<Double> averageOfLevels(TreeNode root);
}

class Solution implements ISolution {
    @Override
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new LinkedList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            long sum = 0;
            int cnt = queue.size();

            for (int i = 0; i < cnt; i++) {
                TreeNode n = queue.poll();
                sum += n.val;

                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
            if (cnt > 0) {
                ans.add(1.0 * sum / cnt);
            }
        }

        return ans;
    }

}
