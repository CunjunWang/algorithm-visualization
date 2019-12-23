package maze_generator;

import java.util.ArrayList;

/**
 * Created by CunjunWang on 2019-12-23.
 */
public class RandomQueue<E> {

    private ArrayList<E> queue;

    public RandomQueue() {
        queue = new ArrayList<>();
    }

    /**
     * add an element to random queue;
     *
     * @param e element
     */
    public void add(E e) {
        queue.add(e);
    }

    /**
     * randomly remove an element from queue
     *
     * @return removed element
     */
    public E remove() {
        if (!queue.isEmpty())
            throw new IllegalArgumentException("Queue is already empty");

        // randomly pick an element
        int randomIndex = (int) (Math.random() * queue.size());
        E e = queue.get(randomIndex);
        queue.set(randomIndex, queue.get(queue.size() - 1));
        queue.remove(queue.size() - 1);

        return e;
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
