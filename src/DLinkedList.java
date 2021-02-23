/**
 * A class implementing iterable doubly linked list
 */
import java.util.*;
import java.io.*;

public class DLinkedList implements Iterable<String>{

    /**
     * An inner class node stores each element of linked list
     */
    public static class Node{
        String data;
        Node prev;
        Node next;

        /**
         * Constructor method 1, initiate with data, prev, next to be null
         * @param  data stores the string value
         */
        public Node (String data) {
            this.data = data;
            prev = null;
            next = null;
        }

        /**
         * Constructor method 2 for node, initiate prev, next to be null
         * @param  data stores the string value, prev and next are nodes
         */
        public Node(String data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return (" "+data);
        }
    }

    //Class fields: head and tail
    Node head, tail;

    /**
     * Constructor method 1 for linked list, initiate head, tail to be null
     */
    public DLinkedList(){
        head = null;
        tail = null;
    }

    /**
     * Constructor method 2 for linked list, initiate head and tail
     */
    public DLinkedList(Node head, Node tail){
        this.head = head;
        this.tail = tail;
    }

    /**
     * An inner class to generate iterator for linked list
     */
    private class DListIterator implements Iterator<String>{

        private Node nextNode = head;

        /**
         * Checks for whether the iterable item has a next element
         */
        @Override
        public boolean hasNext() { return nextNode!= null; }

        /**
         * Returns the data value of the next node, and count +1
         */
        @Override
        public String next() {
            if (!hasNext())
                throw new NoSuchElementException("Item exceeded");
            String data = nextNode.data;
            nextNode = nextNode.next;
            return data;
        }
    }

    /**
     * Create an iterator of the linked list
     */
    @Override
    public Iterator<String> iterator() { return new DListIterator(); }

    /**
     * Checks for whether linked list is empty
     */
    public boolean Isempty(){
        return head == null && tail == null;
    }

    /**
     * Create a node from the string data
     * @param data
     * and adds it to the front of linked list
     */
    public void addFirst(String data){
        Node node = new Node(data, null, null);
        if (head == null)
            tail = head = node;
        else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    /**
     * Create a node from the string data
     * @param data
     * and adds it to the end of the linked list
     */
    public void addLast(String data){
        Node node = new Node(data, null, null);
        if (tail == null)
            tail = head = node;
        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    /**
     * Check whether a node with string segment passed by
     * @param data
     * is contained in the linked list or not
     * @return true if it exists
     */
    boolean contains(String data){
        Iterator iter = this.iterator();
        while (iter.hasNext()){
            String next_item = (String) iter.next();
            if(next_item.equals(data)){
                return true;
            }
        }
        return false;
    }

    /**
     * @return the size of the linked list
     */
    public int size(){
        Node count = head;
        int i = 1;
        while (count.next != null){
            i++;
            count = count.next;
        }
        return i;
    }

    /**
     * Return the index of a node with data segment by
     * @param data
     * @return the index or -1 if node doesn't exist in list
     */
    int indexOf(String data){
        int i = 0;      /* count the index */
        Iterator it = this.iterator();
        while (it.hasNext()){
            String next_item = (String) it.next();
            if(next_item.equals(data)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Swap the first two nodes in the linked list
     */
    public void swap(){
        Node first = head, second = head.next;
        if (first == null | head.next == null) {
            throw new NoSuchElementException("List size is too small");
        }
        if (second == tail){
            tail = head;
        }
        head = second;
        first.prev = second;
        first.next = second.next;
        second.next = first;
        second.prev = null;
    }
}