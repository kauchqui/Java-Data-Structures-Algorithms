package sort.comparison;


        import java.util.ArrayList;
        import java.util.Comparator;
        import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class ShellSort<T extends Comparable<T>> extends InsertionSort<T>
{
    protected List<Integer> sequence;

    /** @param n the max-number of items in the list to be sorted. */
    public ShellSort(Comparator<T> comparator, int n)
    {
        super(comparator);
        sequence = new ArrayList<>();
        populateSequence(n);
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex)
    {
        int n = endIndex - beginIndex;
        populateSequence(n);

        for (int i=getSequenceStartIndex(n); i>=0; i--)
            sort(array, beginIndex, endIndex, sequence.get(i));
    }

    /**
     * Populates the gap sequence with respect to the size of the list.
     * @param n the size of the list to be sorted.
     */
    protected abstract void populateSequence(int n);

    /**
     * @param n the size of the list to be sorted.
     * @return the starting index of the sequence with respect to the size of the list.
     */
    protected abstract int getSequenceStartIndex(int n);
}
