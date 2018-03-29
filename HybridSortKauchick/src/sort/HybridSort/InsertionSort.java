package sort.HybridSort;

import sort.AbstractSort;

import java.util.Comparator;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T>
{
    public InsertionSort()
    {
        this(Comparator.naturalOrder());
    }

    public InsertionSort(Comparator<T> comparator)
    {
        super(comparator);
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex)
    {
        sort(array, beginIndex, endIndex, 1);
    }

    protected void sort(T[] array, int beginIndex, int endIndex, final int h)
    {
        int beginH = beginIndex + h;

        for (int i=beginH; i<endIndex; i++)
            for (int j=i; j>=beginH && compareTo(array, j, j-h) < 0; j-=h)
                swap(array, j, j-h);
    }
}