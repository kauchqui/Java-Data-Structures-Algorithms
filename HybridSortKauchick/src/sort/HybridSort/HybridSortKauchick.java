package sort.HybridSort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import sort.AbstractSort;
import sort.HybridSort.HybridSort;
import sort.HybridSort.QuickSort;

/**
 * @author Quintin J. Kauchick ({@code qkauchi@emory.edu})
 */
public class HybridSortKauchick<T extends Comparable<T>> implements HybridSort<T>
{
    private AbstractSort<T> engine;


    public HybridSortKauchick()
    {
        engine = new QuickSort<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] sort(T[][] input)
    {
        int size = Arrays.stream(input).mapToInt(t -> t.length).sum();
        T[] output = (T[])Array.newInstance(input[0][0].getClass(), size);
        int beginIndex = 0;
        for (T[] t : input)
        {

            int x = (t[0].compareTo( t[t.length-1]));
            if(x == 1){
                T temp;
                for (int i = 0; i < t.length/2; i++)
                {
                    temp = t[i];

                    t[i] = t[t.length-1-i];

                    t[t.length-1-i] = temp;
                }
            }

            else if(x == -1){

                for (int j = 1; j < t.length; j++) {
                    T key = t[j];
                    int i = j-1;
                    while ( (i > -1) && ( t[i].compareTo(t[j]) > 0  ) ) {
                        t [i+1] = t [i];
                        i--;
                    }
                    t[i+1] = key;
                }
            }

            System.arraycopy(t, 0, output, beginIndex, t.length);//arraycopy(Object src, int srcPos, Object, dest, int destPos, int length)
            beginIndex += t.length;
        }

        engine.sort(output);

        return output;

    }
}