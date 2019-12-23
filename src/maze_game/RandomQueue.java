package maze_game;

import java.util.LinkedList;

/**
 * Created by CunjunWang on 2019-12-23.
 */
public class RandomQueue<E> {

    private LinkedList<E> queue;

    public RandomQueue() {
        queue = new LinkedList<>();
    }

    /**
     * add an element to front or end of queue randomly;
     *
     * @param e element
     */
    public void add(E e) {
        if (Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    /**
     * randomly remove an element from queue
     *
     * @return removed element
     */
    public E remove() {
        if (queue.isEmpty())
            throw new IllegalArgumentException("Queue is already empty");

        // randomly pick an element
        if (Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
    }

    /**
     * size of queue
     *
     * @return size of queue
     */
    public int size() {
        return queue.size();
    }

    /**
     * is queue empty or not
     *
     * @return is queue empty or not
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
