package queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class EagerPriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T>
{
    private List<T> keys;

    public EagerPriorityQueue()
    {
        this(Comparator.naturalOrder());
    }

    public EagerPriorityQueue(Comparator<T> comparator)
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
        // binary search the element in the list (if not found, index < 0)
        int index = Collections.binarySearch(keys, key, comparator);

        // if element not found, the appropriate insert index = -(index +1)
        if (index < 0) index = -(index + 1);

        keys.add(index, key);
    }

    @Override
    protected T removeAux()
    {
        return keys.remove(keys.size()-1);
    }
}