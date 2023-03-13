package Soloma;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws Exception {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.addFirst(20);
        list.addFirst(10);
        list.addLast(30);
        list.addLast(40);

        for (int i = 0; i < list.size(); i++) {
            System.out.print((i > 0 ? ", " : "") + list.get(i));
        }
        System.out.println();

        System.out.println(list.remove(2) + " Был удалён и показан нам тут, в консоли");
        list.removeFirst();
        int i = 0;
        for (Integer v : list) {
            System.out.print((i > 0 ? ", " : "") + v);
            i++;
        }
        /*
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer v = it.next();
            System.out.print((i > 0 ? ", " : "") + v);
            i++;
        }
         */
        System.out.println();

        System.out.println();

        System.out.println("SimpleLinkedListStack:");
        SimpleStack<String> stack = new SimpleLinkedListStack<>();
        stack.push("one");
        stack.push("two");
        System.out.println(stack.pop());
        stack.push("three");
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println();

        System.out.println("SimpleLinkedListQueue:");
        SimpleQueue<String> queue = new SimpleLinkedListQueue2<>();
        queue.add("one");
        queue.add("two");
        System.out.println(queue.remove());
        queue.add("three");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}
