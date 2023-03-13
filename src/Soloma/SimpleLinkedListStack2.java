package Soloma;

public class SimpleLinkedListStack2<T> implements SimpleStack<T> {

    private static class SimpleLinkedListNode<T> {
        public T value;
        public SimpleLinkedListNode<T> next;

        public SimpleLinkedListNode(T value, SimpleLinkedListNode<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private SimpleLinkedListNode<T> head = null;  // first, top
    private int count = 0;

    @Override
    public void push(T value) {
        head = new SimpleLinkedListNode<T>(value, head);
        count++;
    }

    @Override
    public T pop() throws Exception {
        T result = this.peek();
        head = head.next;
        count--;
        return result;
    }

    @Override
    public T peek() throws Exception {
        if (count == 0) {
            throw new Exception("Stack is empty");
        }
        return head.value;
    }

    @Override
    public int count() {
        return count;
    }
}
