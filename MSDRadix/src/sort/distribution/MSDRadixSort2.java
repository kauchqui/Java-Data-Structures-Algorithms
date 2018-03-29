package sort.distribution;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class MSDRadixSort2 extends RadixSort {
    public MSDRadixSort2() {
        this(Comparator.naturalOrder());
    }

    public MSDRadixSort2(Comparator<Integer> comparator) {
        super(comparator);
    }

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);

        // for (int bit=maxBit-1; bit>=0; bit--)
        sort(array, beginIndex, endIndex, maxBit - 1); //todo change back
    }

    private void sort(Integer[] array, int beginIndex, int endIndex, int bit) {


    }

    private void depthBucketSort(Integer[] array, int beginIndex, int endIndex, int bit, int bucketIndex) {

    }
}