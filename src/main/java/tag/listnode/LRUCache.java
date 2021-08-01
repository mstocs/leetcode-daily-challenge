package tag.listnode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Constant
 * @Date 2021/8/1 18:32
 * @Description 手写LRU缓存实现，通过哈希表加双向链表
 **/
public class LRUCache {
    private int capacity;
    private DLinkedNode dLinkedNode;
    private Map<Integer, DLinkedNode> cache;
    private DLinkedNode tail;
    private DLinkedNode head;
    private int size;

    LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.cache = new HashMap<>();
        tail = new DLinkedNode();
        head = new DLinkedNode();
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node == null) {
            return -1;
        }
            moveToHead(node);
            return node.val;

    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if(node != null) {
            moveToHead(node);
            node.val = value;
        } else {
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if(size > capacity) {
                DLinkedNode oldNode = removeLast();
                cache.remove(oldNode.key);
                size--;
            }
        }
    }

    private DLinkedNode removeLast() {
        DLinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }


    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void printAll() {
        DLinkedNode dummy = head.next;
        while(dummy.next != null) {
            System.out.print(dummy.val+",");
            dummy = dummy.next;

        }
        System.out.println("");
    }

    class DLinkedNode {
        DLinkedNode prev;
        DLinkedNode next;
        int key;
        int val;
        DLinkedNode(){};
        DLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.printAll(); //3,2,1
        lruCache.get(2);
        lruCache.printAll(); //2,3,1
        lruCache.put(4, 4); //4,2,3
        lruCache.printAll();
        lruCache.put(5, 5);
        lruCache.printAll();
        System.out.println(lruCache.get(1));
    }
}
