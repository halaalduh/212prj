/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj;

/**
 *
 * @author randd
 */
public class Node<T> {
        public T data;
    public Node<T> next;
    public Node<T> previous;

    public Node() {
        data = null;
        next = null;
        previous = null;
    }

    public Node(T val) {
        data = val;
        next = null;
        previous = null;
    }
}
