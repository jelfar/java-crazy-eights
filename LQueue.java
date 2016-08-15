/** LQueueDriver.java
  *
  * A Queue represented as a list of nodes.
  *
  * @author Jonathon Elfar 
  * @author Justin Cellona
  */
import java.lang.RuntimeException;
public class LQueue<T> {
    private class Node {
        public T val;
        public Node next;
    }

    public static class MyException extends RuntimeException {
        public MyException() {
        }

        public MyException(String s) {
            super(s);
        }
    }

    private Node front, end;

    public LQueue() {
        front = end = null;
    }

    public void enqueue(T element) {
        Node newNode = new Node();
        newNode.val = element;
        if(front == null)
            front = end = newNode;
       
        end.next = newNode;
        end = end.next;
    }

    public T dequeue() {
        if(front == null)
            throw new MyException("Queue is empty.");

        T value = front.val;
        if(front == end)
            front = null;
        else
            front = front.next;

        return value;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
