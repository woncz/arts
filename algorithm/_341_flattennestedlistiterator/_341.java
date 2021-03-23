/*
 * Copyright [2021]
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

package _341_flattennestedlistiterator;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by woncz on 3/23/2021.
 */
public class _341 {
    public static void main(String[] args) {
        List<NestedInteger> data = new ArrayList<>();
        data.add(new Ni());
        data.add(new Ni());
        data.add(new Ni());
        data.add(new Ni());

        NestedIterator i = new NestedIterator(data);
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}

interface NestedInteger {

    boolean isInteger();

    Integer getInteger();

    List<NestedInteger> getList();

}

class NestedIterator implements Iterator<Integer> {

    List<Integer> data;

    Iterator<Integer> current;

    public NestedIterator(List<NestedInteger> nestedList) {
        data = new ArrayList<>();
        dfs(nestedList);
        current = data.iterator();
    }

    void dfs(List<NestedInteger> list) {
        for (NestedInteger ni : list) {
            if (ni.isInteger()) {
                data.add(ni.getInteger());
            } else {
                dfs(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return current.next();
    }

    @Override
    public boolean hasNext() {
        return current.hasNext();
    }
}

class NestedIterator2 implements Iterator<Integer> {

    private Deque<Iterator<NestedInteger>> stack;

    public NestedIterator2(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> it = stack.peek();
            if (!it.hasNext()) {
                stack.pop();
                continue;
            }
            NestedInteger ni = it.next();
            if (ni.isInteger()) {
                List<NestedInteger> data = new ArrayList<>();
                data.add(ni);
                stack.push(data.iterator());
                return true;
            }
            stack.push(ni.getList().iterator());
        }
        return false;
    }

    @Override
    public Integer next() {
        return stack.peek().next().getInteger();
    }
}

class Ni implements NestedInteger {
    @Override
    public boolean isInteger() {
        return true;
    }

    @Override
    public Integer getInteger() {
        return (int) System.currentTimeMillis();
    }

    @Override
    public List<NestedInteger> getList() {
        return null;
    }
}