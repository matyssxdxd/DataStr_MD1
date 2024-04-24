package datastr;

// Izveidoju tikai vienu node, jo likās, ka 3 veidot ir bezjēdzīgi

public class MyNode<T> {

    private T value;
    private MyNode<T> next = null;
    private MyNode<T> previous = null;

    public MyNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public MyNode<T> getNext() {
        return next;
    }

    public MyNode<T> getPrevious() {
        return previous;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setNext(MyNode<T> next) {
        this.next = next;
    }

    public void setPrevious(MyNode<T> previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
