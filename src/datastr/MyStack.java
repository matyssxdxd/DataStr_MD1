package datastr;

public class MyStack<T> {

    private MyNode<T> topNode = null;
    private int length = 0;

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

    public void push(T value) throws Exception {
        if (isFull()) throw new Exception("Stack is full.");

        MyNode<T> newNode = new MyNode<>(value);
        if (!isEmpty()) topNode.setPrevious(newNode);
        newNode.setNext(topNode);
        topNode = newNode;
        length++;
    }

    public void pop() throws Exception {
        if (isEmpty()) throw new Exception("Stack is empty.");

        topNode = topNode.getNext();
        if (topNode != null) topNode.setPrevious(null);
        length--;
        System.gc();
    }

    public T top() throws Exception {
        if (isEmpty()) throw new Exception("Stack is empty.");

        return topNode.getValue();
    }

    public void print() throws Exception {
        if (isEmpty()) throw new Exception("Stack is empty.");

        MyNode<T>  currentNode = topNode;
        while (currentNode != null) {
            System.out.print(currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        System.out.println();
    }

    public void makeEmpty() throws Exception {
        if (isEmpty()) throw new Exception("Stack is already empty.");

        topNode = null;
        length = 0;
        System.gc();
    }
}
