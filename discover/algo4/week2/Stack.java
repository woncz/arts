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

package algo4.week2;

public class Stack<T extends Comparable<T>> {

    private Object[] objs;

    private int size;

    private int cap;

    private T max;

    public Stack() {
        cap = 1 << 4;
        objs = new Object[cap];
    }

    public void push(T t) {
        if (t == null) throw new IllegalArgumentException();

        if (max == null) {
            max = t;
        } else {
            max = max.compareTo(t) < 0 ? t : max;
        }
        if (size < cap) {
            objs[size++] = t;
        } else {
            Object[] elder = objs;
            objs = new Object[size * 2];
            System.arraycopy(elder, 0, objs, 0, size);
        }
    }

    public T pop() {
        if (size == 0) throw new IllegalStateException();
        T t = (T) objs[--size];
        // GC
        objs[size] = null;

        if (size > 0) {
            if (max.compareTo(t) <= 0) {
                // update the max
                max = (T) objs[0];
                for (int i = 1; i < size; i++) {
                    T item = (T) objs[i];
                    if (max.compareTo(item) < 0) {
                        max = item;
                    }
                }
            }
        } else {
            max = null;
        }

        return t;
    }

    public T max() {
        return max;
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();

        s.push(2);
        s.push(3);
        s.push(1);

        System.out.println(s.max());

        s.pop();
        System.out.println(s.max());

        s.pop();
        System.out.println(s.max());

        s.pop();
        System.out.println(s.max());
    }
}
