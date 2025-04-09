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
import java.util.LinkedHashMap;
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
                head.prev = null;
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
            tail.next = current;
            current.prev = tail;
            tail = current;
            tail.next = null;
            return current.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        // put时同样更新位置(通过调用get()方法)(refer:LinkedHashMap#accessOrder = true/false)
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
                current.prev = tail;
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
                second.prev = null;
                head.next = null;
                head = second;
                // add to tail
                tail.next = current;
                current.prev = tail;
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
class Node {
    public int key, value;
    public Node prev, next;
    public Node(int k, int v) {
        this.key = k;
        this.value = v;
    }
}

class DoubleList {
    private Node head, tail;

    private int size;

    // 头尾两节点为哨兵节点
    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0 );
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addLast(Node x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    // 节点x的prev和next是否需要处理？
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    public Node removeFirst() {
        // 空集时
        if (head.next == tail) {
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }

    public int size() {
        return size;
    }
}

/**
 * 哈希表 + 双向链表
 */
class LRUCache2 {
    private HashMap<Integer, Node> bucket;

    private DoubleList cache;

    private int cap;

    public LRUCache2(int capacity) {
        this.cap = capacity;
        bucket = new HashMap<>(capacity);
        cache = new DoubleList();
    }

    private void makeRecently(int key) {
        Node x = bucket.get(key);
        cache.remove(x);
        cache.addLast(x);
    }

    private void addRecently(int key, int val) {
        Node x = new Node(key, val);
        cache.addLast(x);
        bucket.put(key, x);
    }

    private void deleteKey(int key) {
        Node x = bucket.get(key);
        cache.remove(x);
        bucket.remove(key);
    }

    private void removeLeastRecently() {
        Node deletedNode = cache.removeFirst();
        int deletedKey = deletedNode.key;
        bucket.remove(deletedKey);
    }

    public int get(int key) {
        if (!bucket.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return bucket.get(key).value;
    }

    public void put(int key, int value) {
        if (bucket.containsKey(key)) {
            deleteKey(key);
            addRecently(key, value);
            return;
        }
        if (cap == cache.size()) {
            removeLeastRecently();
        }
        addRecently(key, value);
    }
}

class LRUCache3 {

    private int cap;

    private Map<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache3(int cap) {
        this.cap = cap;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            cache.put(key, val);
            makeRecently(key);
            return;
        }
        if (cache.size() >= this.cap) {
            int eldestKey = cache.keySet().iterator().next();
            cache.remove(eldestKey);
        }
        cache.put(key, val);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }
}

class LRUCache4 {

    private int cap;

    private LinkedHashMap<Integer, Integer> bucket;

    public LRUCache4(int cap) {
        this.cap = cap;
        this.bucket = new LinkedHashMap<>(cap, 0.75f, true);
    }

    public int get(int key) {
        if (!bucket.containsKey(key)) {
            return -1;
        }
        return bucket.get(key);
    }

    public void put(int key, int val) {
        if (bucket.containsKey(key)) {
            bucket.put(key, val);
            return;
        }
        if (bucket.size() >= this.cap) {
            int eldestKey = bucket.keySet().iterator().next();
            bucket.remove(eldestKey);
        }
        bucket.put(key, val);
    }
}

class LRUCache5 {

    private int cap;

    private final LinkedHashMap<Integer, Integer> bucket;

    public LRUCache5(int cap) {
        this.cap = cap;
        bucket = new LinkedHashMap<>(cap, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > cap;
            }
        };
    }

    // Get the value associated with the key
    public Integer get(int key) {
        return bucket.getOrDefault(key, -1);
    }

    // Add a key-value pair to the cache
    public void put(int key, int value) {
        bucket.put(key, value);
    }
}


