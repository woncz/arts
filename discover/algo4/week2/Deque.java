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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;

    private Node tail;

    private int size;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        size++;
        if (head == null) {
            head = new Node(item);
            tail = head;
        } else {
            head.prev = new Node(item);
            head.prev.next = head;
            head = head.prev;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        size++;
        if (head == null) {
            head = new Node(item);
            tail = head;
        } else {
            tail.next = new Node(item);
            tail.next.prev = tail;
            tail = tail.next;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();

        size--;
        Item item = head.item;
        if (size == 0) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev.next = null;
            head.prev = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();

        size--;
        Item item = tail.item;
        if (size == 0) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next.prev = null;
            tail.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator(head);
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current;

        private int remaining = size();

        public DequeIterator(Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item e = current.item;
            current = current.next;
            remaining--;
            return e;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private Node prev;

        private Node next;

        private Item item;

        public Node(Item item) {
            this.item = item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(0);

        Iterator<Integer> iter = deque.iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next());
        }

        deque.removeFirst();
        iter = deque.iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next());
        }

        deque.removeFirst();
        iter = deque.iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next());
            deque.removeLast();
        }

        StdOut.println(deque.iterator().hasNext());

        StdOut.println(deque.isEmpty());

        StdOut.println(deque.size());
    }
}
