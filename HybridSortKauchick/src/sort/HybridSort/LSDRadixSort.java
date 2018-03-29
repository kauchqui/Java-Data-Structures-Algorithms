package sort.HybridSort;

import java.util.Comparator;
import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class LSDRadixSort extends RadixSort
{
    public LSDRadixSort()
    {
        this(Comparator.naturalOrder());
    }

    public LSDRadixSort(Comparator<Integer> comparator)
    {
        super(comparator);
    }

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex)
    {
        int maxBit = getMaxBit(array, beginIndex, endIndex);

        for (int bit=0; bit<maxBit; bit++)
            sort(array, beginIndex, endIndex, bit);
    }

    private void sort(Integer[] array, int beginIndex, int endIndex, int bit)
    {
        int div = (int)Math.pow(10, bit);
        int idx;

        for (int i=beginIndex; i<endIndex; i++)
            buckets.get(getBucketIndex(array[i], div)).add(array[i]);

        for (List<Integer> bucket : buckets)
        {
            idx = beginIndex = beginIndex + bucket.size();
            while (bucket.size() > 0) array[--idx] = bucket.remove(bucket.size()-1);
        }
    }
}