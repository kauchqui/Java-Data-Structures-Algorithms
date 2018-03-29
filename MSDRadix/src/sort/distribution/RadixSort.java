package sort.distribution;

import java.util.Comparator;
import java.util.List;

import sort.distribution.AbstractSort;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class RadixSort extends AbstractSort<Integer>
{
    protected List<List<Integer>> buckets;

    public RadixSort(Comparator<Integer> comparator)
    {
        super(comparator);
        buckets = BucketSort.createBuckets(10);
    }

    protected int getMaxBit(Integer[] array, int beginIndex, int endIndex)
    {
        int max = array[beginIndex];

        for (int i=endIndex-1; i>beginIndex; i--)
            max = Math.max(max, array[i]);

        return Integer.toString(max).length();
    }

    protected int getBucketIndex(Integer key, int div)
    {
        return (key / div) % 10;
    }
}