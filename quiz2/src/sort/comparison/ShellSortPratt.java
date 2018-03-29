package sort.comparison;

import java.util.Collections;
import java.util.Comparator;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class ShellSortPratt<T extends Comparable<T>> extends ShellSort<T>
{
    public ShellSortPratt()
    {
        this(Comparator.naturalOrder());
    }

    public ShellSortPratt(Comparator<T> comparator)
    {
        this(comparator, 1000);
    }

    public ShellSortPratt(Comparator<T> comparator, int n)
    {
        super(comparator, n);
    }

    @Override
                protected void populateSequence(int n)
                {
                    if (sequence.isEmpty()) sequence.add(1);
                    n /= 3;
                    int p = 0, q = 0, vp, vq;
                    while (true) {
                        vp = sequence.get(p) * 2;
                        vq = sequence.get(q) * 3;
                        if (Math.min(vp, vq) > n)
                            break;
                        if (vp < vq) {
                            sequence.add(vp);
                            p++;
                        } else if (vp > vq) {
                            sequence.add(vq);
                            q++;
                        } else if (p < q)
                            p++;
                        else q++;
                    }
                     //   P = p; Q = q;
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