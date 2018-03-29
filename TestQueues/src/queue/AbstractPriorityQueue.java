package queue;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class AbstractPriorityQueue<T extends Comparable<T>>
{
    protected Comparator<T> comparator;

    public AbstractPriorityQueue(Comparator<T> comparator)
    {
        this.comparator = comparator;
    }

    /** @param key a comparable key to be added. */
    abstract public void add(T key);

    /**
     * @return the key with the highest priority.
     * @throws NoSuchElementException if the queue is empty.
     */
    abstract protected T removeAux();

    /** @return the size of this queue. */
    abstract public int size();

    /** @return {@code true} if the queue is empty; otherwise, {@code false}. */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    public T remove()
    {
        if (isEmpty()) throw new NoSuchElementException("No key exists.");
        return removeAux();
    }
}