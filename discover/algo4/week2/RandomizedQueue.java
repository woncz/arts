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

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;

    private Item[] data;

    private int head;

    private int tail;

    // construct an empty randomized queue
    public RandomizedQueue() {
        data = (Item[]) new Object[16];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        size++;
        data[tail] = item;
        // if next item meets head, means now is full
        if ((tail = (tail + 1) & (data.length - 1)) == head) {
            doubleCapacity();
        }
    }

    private void doubleCapacity() {
        int p = head;
        int n = data.length;
        int r = n - p;

        int newCapacity = n << 1;
        if (newCapacity < 0) throw new IllegalStateException("Sorry, queue too big");

        Item[] a = (Item[]) new Object[newCapacity];
        System.arraycopy(data, p, a, 0, r);
        System.arraycopy(data, 0, a, r, p);
        data = a;
        head = 0;
        tail = n;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        swap();
        return data[--size];
    }

    private void swap() {
        if (size <= 1) return;
        int r = StdRandom.uniform(size);
        Item e = data[r];
        data[r] = data[size - 1];
        data[size - 1] = data[r];
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException();

        int r = StdRandom.uniform(size);
        return data[(head + r) & (data.length - 1)];
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private int[] index;

        private int current;

        public QueueIterator() {
            index = new int[size];
            for (int i = 0; i < size; i++) {
                index[i] = i;
            }

            for (int i = size - 1; i >= 0; i--) {
                int r = StdRandom.uniform(i + 1);
                int e = index[r];
                index[r] = index[i];
                index[i] = e;
            }
            current = size - 1;
        }

        @Override
        public boolean hasNext() {
            return current >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return data[index[current--]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        String s = rq.dequeue();
        StdOut.println(s);
        rq.enqueue("D");

        StdOut.println(rq.sample());

        Iterator<String> it = rq.iterator();
        while (it.hasNext()) {
            StdOut.print(it.next());
        }

        StdOut.println(rq.size());
        StdOut.println(rq.isEmpty());
    }

}
