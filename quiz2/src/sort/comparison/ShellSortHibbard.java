package sort.comparison;

import java.util.Collections;
import java.util.Comparator;


public class ShellSortHibbard<T extends Comparable<T>> extends ShellSort<T>
{
    public ShellSortHibbard()
    {
        this(Comparator.naturalOrder());
    }

    public ShellSortHibbard(Comparator<T> comparator)
    {
        this(comparator, 1000);
    }

    public ShellSortHibbard(Comparator<T> comparator, int n)
    {
        super(comparator, n);
    }

    @Override
    protected void populateSequence(int n)
    {

        n /= 2;

        for (int t=sequence.size()+1; ; t++)
        {
            int h = (int)((Math.pow(2, t) - 1));
            if (h <= n) sequence.add(h);
            else break;
        }
    }

    @Override
    protected int getSequenceStartIndex(int n)
    {
        int index = Collections.binarySearch(sequence, n/2);
        if (index < 0) index = -(index+1);
        if (index == sequence.size()) index--;
        return index;
    }
}

/*
Pseudo Code for Hibbard:

find k0 such that 2^k0 - 1 < size()   // pretty sure this is in getSequenceStartIndex

in populateSequence
for (int i = k0; k > 0; k--)
{
int sequenceHolder = 2^k - 1;
if h < sequence size /2 add
break if not

}



 */