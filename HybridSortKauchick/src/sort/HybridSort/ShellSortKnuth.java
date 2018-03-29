package sort.HybridSort;

import java.util.Collections;
import java.util.Comparator;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class ShellSortKnuth<T extends Comparable<T>> extends ShellSort<T>
{
    public ShellSortKnuth()
    {
        this(Comparator.naturalOrder());
    }

    public ShellSortKnuth(Comparator<T> comparator)
    {
        this(comparator, 1000);
    }

    public ShellSortKnuth(Comparator<T> comparator, int n)
    {
        super(comparator, n);
    }

    @Override
    protected void populateSequence(int n)
    {
        n /= 3;

        for (int t=sequence.size()+1; ; t++)
        {
            int h = (int)((Math.pow(3, t) - 1) / 2);
            if (h <= n) sequence.add(h);
            else break;
        }
    }

    @Override
    protected int getSequenceStartIndex(int n)
    {
        int index = Collections.binarySearch(sequence, n/3);
        if (index < 0) index = -(index+1);
        if (index == sequence.size()) index--;
        return index;
    }
}