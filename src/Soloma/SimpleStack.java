package Soloma;

public interface SimpleStack<T> {
    void push(T value);

    T pop() throws Exception;

    T peek() throws Exception;

    int count();

    default boolean empty() {
        return count() == 0;
    }
}
