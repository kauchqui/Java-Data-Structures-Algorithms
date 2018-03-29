
package quiz1;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main <T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private List<T> keys;
    public Main(Comparator<T> comparator)
    {
        super(comparator);
        keys = new ArrayList<>();

    }
    public int size()
    {
        return keys.size();
    }
    private int getRootIndex()
    {
        return 0;
    }
    private int getParentIndex(int k)
    {
        return (k-1)/3;
    }
    private int getLastIndex(){
        return size()-1;
    }
    private int getLeftmostChildIndex(int k){
        return k*3+1;
    }
    private int getMaxIndex(int leftmostIndex)
    {
        int max = leftmostIndex;

        for(int i = leftmostIndex+1; i <size() && i < leftmostIndex+3;i++){
            if(comparator.compare(keys.get(max),keys.get(i))<0)
                max = i;
        }
        return max;
    }

    @Override
    public void add(T key)
    {
    keys.add(key);
    swim(getLastIndex());
    }
    private void swim(int k)
    {
        while (getRootIndex() < k && comparator.compare(keys.get(getParentIndex(k)),keys.get(k))<0)
        {
            Collections.swap(keys,getParentIndex(k),k);
            k = getParentIndex(k);
        }
    }
    @Override
    protected T removeAux()
    {
        Collections.swap(keys,getRootIndex(),getLastIndex());
        T max = keys.remove(getLastIndex());
        sink(getRootIndex());
        return max;
    }
    private void sink(int k)
    {
        int i;
        int max;
        while ((i=getLeftmostChildIndex(k)) < size())
        {
            max = getMaxIndex(i);
            if (comparator.compare(keys.get(k), keys.get(max)) >= 0) break;
            Collections.swap(keys, k, max);
            k = max;
        }
    }
}

