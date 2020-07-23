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

package _095_uniquebinarysearchtreesii;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woncz on 7/21/2020.
 */
public class _095 {
    public static void main(String[] args) {
        ISolution solution = new Solution();
        List<TreeNode> ans = solution.generateTrees(3);
        System.out.println(ans);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

interface ISolution {
    List<TreeNode> generateTrees(int n);
}

/**
 * 关键点是对问题的分解，能够将问题分解为最近子问题，并且取值空间变小，小到能够直接求解
 * 本例中[start, end] -> [start, i - 1]、[i]、[i + 1, end] 左、中、右
 */
class Solution implements ISolution {
    @Override
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return _process(1, n);
    }

    List<TreeNode> _process(int start, int end) {
        // terminator
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null); // leaf node
            return res;
        }
        if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }

        // drill down
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = _process(start, i - 1);

            List<TreeNode> right = _process(i + 1, end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
