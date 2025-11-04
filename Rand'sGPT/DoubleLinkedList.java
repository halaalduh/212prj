/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj;

/**
 *
 * @author randd
 */
public class DoubleLinkedList<T> {
    private Node<T> head;
    private Node<T> current;

    public DoubleLinkedList() {
        head = current = null;
    }

    public boolean empty() { return head == null; }
    public boolean full() { return false; }

    public void findFirst() { current = head; }
    public void findNext() { current = current.next; }
    public void findPrevious() { current = current.previous; }

    public T retrieve() { return current.data; }
    public void update(T val) { current.data = val; }

    public void insert(T val) {
        Node<T> tmp = new Node<>(val);
        if (empty()) {
            current = head = tmp;
        } else {
            tmp.next = current.next;
            tmp.previous = current;
            if (current.next != null)
                current.next.previous = tmp;
            current.next = tmp;
            current = tmp;
        }
    }

    public void remove() {
        if (current == head) {
            head = head.next;
            if (head != null) head.previous = null;
        } else {
            current.previous.next = current.next;
            if (current.next != null)
                current.next.previous = current.previous;
        }
        if (current.next == null)
            current = head;
        else
            current = current.next;
    }

    // Helper to print all nodes
    public void printList() {
        Node<T> tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    boolean last() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
