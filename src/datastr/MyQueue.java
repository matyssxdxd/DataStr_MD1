package datastr;

public class MyQueue<T> {

    protected MyNode<T> frontNode;
    protected MyNode<T> rearNode;
    protected int length;

    public MyQueue() {
        frontNode = null;
        rearNode = null;
        length = 0;
    }

    public boolean isFull() {
        try {
            MyNode<T> temp = new MyNode<>(null);
            return false;
        } catch (OutOfMemoryError e) {
            return true;
        }
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public void enqueue(T value) throws Exception {
        if (isFull()) throw new Exception("Queue is full");

        MyNode<T> newNode = new MyNode<>(value);
        if (!isEmpty()) {
            rearNode.setNext(newNode);
            newNode.setPrevious(rearNode);
        } else {
            frontNode = newNode;
        }

        rearNode = newNode;
        length++;
    }

    public T dequeue() throws Exception {
        if (isEmpty()) throw new Exception("Queue is empty");

        T result = frontNode.getValue();
        frontNode = frontNode.getNext();

        if (frontNode != null) {
            frontNode.setPrevious(null);
        } else {
            rearNode = null;
        }

        length--;
        System.gc();
        return result;
    }


    public void print() throws Exception {
        if (isEmpty()) throw new Exception("Queue is empty");

        MyNode<T>  currentNode = frontNode;
        while (currentNode != null) {
            System.out.println(currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        System.out.println();
    }

    public void makeEmpty() throws Exception {
        if (isEmpty()) throw new Exception("Queue is already empty");

        frontNode = null;
        rearNode = null;
        length = 0;
        System.gc();
    }
}
