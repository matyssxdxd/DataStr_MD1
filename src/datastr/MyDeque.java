package datastr;

public class MyDeque<T> extends MyQueue<T> {

    public void insertFront(T value) throws Exception {
        if (isFull()) throw new Exception("Deque is full.");

        MyNode<T> newNode = new MyNode<>(value);

        if (isEmpty()) {
            rearNode = newNode;
        } else {
            newNode.setNext(frontNode);
            frontNode.setPrevious(newNode);
        }

        frontNode = newNode;
        length++;
    }

    public void insertRear(T value) throws Exception {
        super.enqueue(value);
    }

    public void removeFront() throws Exception {
        super.dequeue();
    }

    public T removeRear() throws Exception {
        if (isEmpty()) throw new Exception("Deque is empty.");

        T result = rearNode.getValue();
        rearNode = rearNode.getPrevious();

        if (rearNode == null) {
            frontNode = null;
        } else {
            rearNode.setNext(null);
        }

        length--;
        System.gc();
        return result;
    }

    public void printReverse() throws Exception {
        if (isEmpty()) throw new Exception("Deque is empty.");

        MyNode<T> currentNode = rearNode;
        while (currentNode != null) {
            System.out.println(currentNode.getValue());
            currentNode = currentNode.getPrevious();
        }
        System.out.println();
    }

    public void makeEmpty() throws Exception {
        if (isEmpty()) throw new Exception("Deque is empty.");

        frontNode = null;
        rearNode = null;
        length = 0;
        System.gc();
    }
}
