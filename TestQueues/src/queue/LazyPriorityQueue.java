package queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class LazyPriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T>
{
    private List<T> keys;

    public LazyPriorityQueue()
    {
        this(Comparator.naturalOrder());
    }

    public LazyPriorityQueue(Comparator<T> comparator)
    {
        super(comparator);
        keys = new ArrayList<>();
    }

    @Override
    public int size()
    {
        return keys.size();
    }

    @Override
    public void add(T key)
    {
        keys.add(key);
    }

    @Override
    protected T removeAux()
    {
        // linear search for the element with the highest priority
        T max = Collections.max(keys, comparator);
        keys.remove(max);
        return max;
    }
}
