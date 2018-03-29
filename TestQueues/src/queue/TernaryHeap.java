package queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class TernaryHeap <T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private List<T> keys;

    public TernaryHeap(Comparator<T> comparator) {
        super(comparator);
        keys = new ArrayList<>();

    }

    public TernaryHeap()
    {
        this(Comparator.naturalOrder());
    }

    public int size() {
        return keys.size();
    }

    private int Max(int lChild) {
        int max = lChild;

        for (int i = lChild + 1; i < size() && i < lChild + 3; i++) {
            if (comparator.compare(keys.get(max), keys.get(i)) < 0)
                max = i;
        }
        return max;
    }

    @Override
    public void add(T key) {
        keys.add(key);
        swim(size()-1);
    }

    private void swim(int k) {
        while (0 < k && comparator.compare(keys.get((k - 1) / 3), keys.get(k)) < 0) {
            Collections.swap(keys, (k - 1) / 3, k);
            k = (k - 1) / 3;
        }
    }

    @Override
    protected T removeAux() {
        Collections.swap(keys, 0, size()-1);
        T max = keys.remove(size()-1);
        sink(0);
        return max;
    }

    private void sink(int k) {
        int i;
        int max;
        while ((i = k*3+1) < size()) {
            max = Max(i);
            if (comparator.compare(keys.get(k), keys.get(max)) >= 0) break;
            Collections.swap(keys, k, max);
            k = max;
        }
    }
}


