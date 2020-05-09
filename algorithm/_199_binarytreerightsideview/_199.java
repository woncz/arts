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

package _199_binarytreerightsideview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按层遍历
 */
public class _199 {
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
     List<Integer> rightSideView(TreeNode root);
 }

 class Solution implements ISolution {
     @Override
     public List<Integer> rightSideView(TreeNode root) {
         if (root == null) return Collections.emptyList();

         List<Integer> res = new LinkedList<>();

         Queue<TreeNode> queue = new LinkedList<>();
         Queue<TreeNode> substitute = new LinkedList<>();
         queue.add(root);
         while (!queue.isEmpty()) {
             while (!queue.isEmpty()) {
                 TreeNode node = queue.poll();
                 if (queue.isEmpty()) {
                     res.add(node.val);
                 }
                 if (node.left != null) substitute.add(node.left);
                 if (node.right != null) substitute.add(node.right);
             }
             if (!substitute.isEmpty()) {
                 Queue<TreeNode> t = queue;
                 queue = substitute;
                 substitute = t;
             }
         }

         return res;
     }
 }

 class Solution2 implements ISolution {
     @Override
     public List<Integer> rightSideView(TreeNode root) {
         List<Integer> result = new ArrayList<Integer>();
         rightView(root, result, 0);
         return result;
     }

     public void rightView(TreeNode curr, List<Integer> result, int currDepth){
         if(curr == null){
             return;
         }
         if(currDepth == result.size()){
             result.add(curr.val);
         }

         // key point : right before left
         rightView(curr.right, result, currDepth + 1);
         rightView(curr.left, result, currDepth + 1);

     }
 }

 class Solution3 implements ISolution {
    @Override
    public List<Integer> rightSideView(TreeNode root) {
        // reverse level traversal
        List<Integer> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        if (root == null) return result;

        queue.offer(root);
        while (queue.size() != 0) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) result.add(cur.val);
                if (cur.right != null) queue.offer(cur.right);
                if (cur.left != null) queue.offer(cur.left);
            }
        }
        return result;
    }
}
