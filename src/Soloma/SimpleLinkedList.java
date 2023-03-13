package Soloma;

import java.util.Iterator;

/*
класс для списка целиком - те тут будут те методы, которые мы хотим видеть операции в списке целиком
 (деламе кста ОДНОСВЯЗАННЫЙ список)
 двусвязанный ещё + ссылка в каждой ячейке на предыдущем эл тоже
 Там ещё если реализовать перебор итератор с итератором то можно и тестить класс
 */

public class SimpleLinkedList<T> implements Iterable<T> {

    public static class SimpleLinkedListException extends Exception { // написали ИМЕННО НАШЕ исключение, которое наследуется вообще от всех исключений
        public SimpleLinkedListException(String message) { //наш единственный конструктор тут
            super(message); //super - вызов конструктора предка
        }
    }

    private class SimpleLinkedListNode<T> {  //внутренний класс (типо хранит по одному значению и ссыль на следующее)
        public T value;// паблик чтобы не реализовать методы гет и сет, но тк всё равно к классу обращаться будем чисто из верхнего класса то инкапсуляция сохраняется
        //  value- это ЗНАЧЕНИЕ
        public SimpleLinkedListNode<T> next;

        public SimpleLinkedListNode(T value, SimpleLinkedListNode<T> next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListNode(T value) {//этот конструктор отправляет нас к конструктору выше
            this(value, null);
        }
    }

    private SimpleLinkedListNode<T> head = null; // тк класс без конструктора, то автоматом переменным присвоятся значения при инициализаци
    private SimpleLinkedListNode<T> tail = null;
    private int count = 0;


    public void addFirst(T value) { // value - перевод: значение
        // операция добавления эл в начало списка
        head = new SimpleLinkedListNode<>(value, head);
        if (count == 0) { // те если список был пустой
            tail = head;
        }
        count++;
    }

    public void addLast(T value) {  // операция добавления эл в конец списка
        SimpleLinkedListNode<T> temp = new SimpleLinkedListNode<>(value);
        if (count == 0) { //если не было эл в списке
            head = tail = temp;
        } else {
            tail.next = temp; // обновили типо ссыль на след объект в классе node
            tail = temp; // теперь в хвосте лежит переменная
        }
        count++;
    }

    private void checkEmpty() throws SimpleLinkedListException { // если список пустой то выкидываем исключение, а метод этот специально для этого
        if (count == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    private SimpleLinkedListNode<T> getNode(int index) { //по индексу будет возвращать элемент
        int i = 0; // считаем индексы элементов нашего списка
        for (SimpleLinkedListNode<T> curr = head; curr != null; curr = curr.next, i++) {
            if (i == index) {
                return curr;
            }
        }
        return null; //по идее до этой операции никогда не дойдём
    }

    public T removeFirst() throws SimpleLinkedListException { //удаление эл из начала списка(+ нужно исключение если вообще эл нет в списке)
        checkEmpty();

        T value = head.value;
        head = head.next;
        if (count == 1) {
            tail = null;
        }
        count--;
        return value;
    }

    public T removeLast() throws SimpleLinkedListException { // удалить конец
//        checkEmpty();
//
//        T value = tail.value;  // запоминаем значение, которое мы будем удалять
//        if (count == 1) {
//            head = tail = null;
//        } else {
//            SimpleLinkedListNode<T> prev = getNode(count - 2); // получили предпоследний эл
//            prev.next = null;  // нехт у прдпоследего эл равен null
//            tail = prev;
//        }
//        count--;
//        return value;
        // или можно и так:
        return remove(count - 1);
    }

    public T remove(int index) throws SimpleLinkedListException {
        checkEmpty();
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }

        T value;
        if (index == 0) {
            value = head.value;
            head = head.next;
        } else {
            SimpleLinkedListNode<T> prev = getNode(index - 1); // нашли предыдущий эл
            SimpleLinkedListNode<T> curr = prev.next;
            value = curr.value;
            prev.next = curr.next;
            if (index == count - 1) {  //если это был последний эл, что мы удаляли
                tail = prev;
            }
            // а если бы prev.next = prev.next.next;  то: (можно было и так)
            //                              |  (эл, который нам надо удалить)
            //                              V
            // [           next]--->[           next]--->[           next]--->
            //   ^                                              ^
            //   |  (prev)                                      | (а ссыдка next должна быть на next.next по отношению к prev)
        }
        count--;
        return value; //возвращаем удолённый эл
    }

    public void insert(int index, T value) throws SimpleLinkedListException { // вставить эл
        if (index < 0 || index > count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {   // вставление эл в начало списка
            addFirst(value);
        } else {
            SimpleLinkedListNode<T> prev = getNode(index - 1);
            prev.next = new SimpleLinkedListNode<>(value, prev.next);
            if (index == count) {
                tail = prev.next;
            }
        }
        count++;
    }

    public int size() {
        return count;
    }

    public T getFirst() throws SimpleLinkedListException {
        checkEmpty();

        return head.value;
    }

    public T getLast() throws SimpleLinkedListException {
        checkEmpty();

        return tail.value;
    }

    public T get(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        return getNode(index).value;
    }


    @Override
    public Iterator<T> iterator() {
        class SimpleLinkedListIterator implements Iterator<T> {
            SimpleLinkedListNode<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new SimpleLinkedListIterator();
    }
}
