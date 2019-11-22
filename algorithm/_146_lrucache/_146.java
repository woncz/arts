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

package _146_lrucache;

import java.util.HashMap;
import java.util.Map;

public class _146 {
    public static void main(String[] args) {
        int capacity = 8;
        LRUCache obj = new LRUCache(capacity);
        int key = 7;
        int param_1 = obj.get(key);
        int value = 11;
        obj.put(key, value);

        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
    }
}

class LRUCache {

    private int capacity;

    private int size = 0;

    class Node {
        private int key;
        private int value;
        private Node previous;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return this.key;
        }
    }

    private Map<Integer, Node> bucket;

    private Node head;

    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.bucket = new HashMap<>(capacity << 1);
    }

    public int get(int key) {
        if (bucket.containsKey(key)) {
            Node current = bucket.get(key);
            if (tail == current) {
                return current.value;
            }
            if (head == current) {
                head = current.next;
                head.previous = null;
            } else {
                current.previous.next = current.next;
                current.next.previous = current.previous;
            }
            tail.next = current;
            current.previous = tail;
            tail = current;
            tail.next = null;
            return current.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (this.get(key) != -1) {
            Node current = bucket.get(key);
            current.value = value;
            return;
        }
        Node current = new Node(key, value);
        bucket.put(key, current);
        if (size < capacity) {
            if (size == 0) {
                head = current;
                tail = current;
            } else {
                tail.next = current;
                current.previous = tail;
                tail = current;
            }
            size++;
        } else {
            bucket.remove(head.key);
            Node second = head.next;
            if (second == null) {
                head = current;
                tail = current;
            } else {
                // delete first
                second.previous = null;
                head.next = null;
                head = second;
                // add to tail
                tail.next = current;
                current.previous = tail;
                tail = current;
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


