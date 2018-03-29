package sort.HybridSort;

import sort.AbstractSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class BucketSort<T extends Comparable<T>> extends AbstractSort<T>
{
    /** The list of buckets. */
    protected List<List<T>> buckets;
    /** if {@code true}, sort each bucket. */
    private final boolean sort_bucket;

    /** @param bucketSize the total number of buckets. */
    public BucketSort(int bucketSize, boolean sort, Comparator<T> comparator)
    {
        super(comparator);
        sort_bucket = sort;
        buckets = createBuckets(bucketSize);
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex)
    {
        // add each element to its corresponding bucket
        for (int i=beginIndex; i<endIndex; i++)
            buckets.get(getBucketIndex(array[i])).add(array[i]);

        // spit out all bucket elements and reassign array elements
        for (List<T> bucket : buckets)
        {
            // sort each bucket if b_sort == true
            if (sort_bucket) bucket.sort(comparator);
            for (T key : bucket) array[beginIndex++] = key;
            bucket.clear();
        }
    }

    /**
     * @param key a comparable key.
     * @return the index of the bucket that the key should be inserted.
     */
    abstract protected int getBucketIndex(T key);

    static public <T>List<List<T>> createBuckets(int size)
    {
        List<List<T>> buckets = new ArrayList<>(size);
        for (int i=0; i<size; i++) buckets.add(new ArrayList<>());
        return buckets;
    }
}