package sort.distribution;

import org.junit.jupiter.api.Test;
import sort.distribution.AbstractSort;
import sort.distribution.LSDRadixSort;
import sort.distribution.Utils;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class SortTest
{
    @Test
    void testAccuracy()
    {
        final int ITERATIONS = 100;
        final int SIZE = 100;


       testAccuracy(ITERATIONS, SIZE, new LSDRadixSort());
       //testAccuracy(ITERATIONS, SIZE, new MSDRadixSort());
    }

    private void testAccuracy(final int ITERATIONS, final int SIZE, AbstractSort<Integer> engine)
    {
        final Random rand = new Random(0);
        Integer[] original, sorted;

        for (int i=0; i<ITERATIONS; i++)
        {
            original = Utils.getRandomIntegerArray(rand, SIZE, SIZE);
            sorted = Arrays.copyOf(original, SIZE);

            engine.sort(original);
            Arrays.sort(sorted);
            boolean isEqual = true;
            for(int b = 0; b < original.length; b++){
                if(original[b] != sorted[b]){
                    isEqual = false;
                    break;
                }
            }
            if(!isEqual){
                for(int b = 0; b < original.length; b++){
                    System.out.print( original[b]);
                    System.out.print(" ");

                }
                System.out.println("");
                for(int b = 0; b < sorted.length; b++){
                    System.out.print( sorted[b]);
                    System.out.print(" ");

                }
                System.out.println("");
                System.out.println(i);
            }
            assertArrayEquals(original, sorted);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSpeed()
    {
        final int ITERATIONS = 1000;
        final int INIT_SIZE  = 100;
        final int MAX_SIZE   = 3000;
        final int INCREMENT  = 100;
        final int OPS        = 1;
        final Random RAND    = new Random(0);

        SortSpeed comp = new SortSpeed();
        comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND,new LSDRadixSort()/*,new MSDRadixSort()*/);
//      comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND, new HeapSort<>(), new ShellSortKnuth<>(), new MergeSort<>(), new QuickSort<>(), new IntroSort<>(new HeapSort<Integer>()), new IntroSort<>(new ShellSortKnuth<Integer>()));
//      comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND, new IntegerBucketSort(0, MAX_SIZE), new LSDRadixSort(), new QuickSort<>(), new HeapSort<>(), new ShellSortKnuth<>(), new MergeSort<>());
//      comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND, new IntegerBucketSort(0, MAX_SIZE), new LSDRadixSort(), new QuickSort<>());
//      comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND, new ShellSortKnuth<>(), new ShellSortHibbard<>(), new ShellSortPratt<>());
//      comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND, new QuickSort<>(), new LSDRadixSort(), new MSDRadixSort());
    }

    //    @Test
    @SuppressWarnings("unchecked")
    public void testOperation()
    {
        final int ITERATIONS = 1000;
        final int INIT_SIZE  = 100;
        final int MAX_SIZE   = 1000;
        final int INCREMENT  = 100;
        final int OPS        = 2;
        final Random RAND    = new Random(0);

        SortOperation comp = new SortOperation();
        comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND, new LSDRadixSort()/*, new MSDRadixSort()*/);
//      comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND, new HeapSort<>(), new ShellSortKnuth<>(), new MergeSort<>(), new QuickSort<>(), new IntroSort<>(new HeapSort<Integer>()), new IntroSort<>(new ShellSortKnuth<Integer>()));
    }

    abstract class AbstractEngineComparator<EngineType>
    {
        @SuppressWarnings("unchecked")
        void printTotal(final int ITERATIONS, final int INIT_SIZE, final int MAX_SIZE, final int INCREMENT, final int OPS, final Random RAND, EngineType... engines)
        {
            long[][] times = new long[engines.length][OPS];
            StringBuilder build = new StringBuilder();
            int i, j, size;

            for (size=INIT_SIZE; size<=MAX_SIZE; size+=INCREMENT)
            {
                for (i=0; i<ITERATIONS; i++)
                    add(RAND, size, times, engines);

                build.append(size);

                for (j=0; j<OPS; j++)
                {
                    for (i=0; i<engines.length; i++)
                    {
                        build.append("\t");
                        build.append(times[i][j]);
                    }
                }

                build.append("\n");
            }

            System.out.println(build.toString());
        }

        @SuppressWarnings("unchecked")
        public abstract void add(final Random RAND, final int SIZE, long[][] times, EngineType... engines);
    }

    class SortSpeed extends AbstractEngineComparator<AbstractSort<Integer>>
    {
        @Override
        @SuppressWarnings("unchecked")
        public void add(final Random RAND, final int SIZE, long[][] times, AbstractSort<Integer>... engines)
        {
            final Integer[] KEYS = Utils.getRandomIntegerArray(RAND, SIZE, SIZE);
            final int LEN = engines.length;
            AbstractSort<Integer> engine;
            Integer[] temp;
            long st, et;
            int i;

            for (i=0; i<LEN; i++)
            {
                temp = Arrays.copyOf(KEYS, SIZE);
                engine = engines[i];
                st = System.currentTimeMillis();
                engine.sort(temp);
                et = System.currentTimeMillis();
                times[i][0] += (et - st);
            }
        }
    }

    class SortOperation extends AbstractEngineComparator<AbstractSort<Integer>>
    {
        @Override
        @SuppressWarnings("unchecked")
        public void add(final Random RAND, final int SIZE, long[][] times, AbstractSort<Integer>... engines)
        {
            final Integer[] KEYS = Utils.getRandomIntegerArray(RAND, SIZE, SIZE);
            final int LEN = engines.length;
            Integer[] temp;
            int i;

            for (i=0; i<LEN; i++)
                engines[i].resetCounts();

            for (i=0; i<LEN; i++)
            {
                temp = Arrays.copyOf(KEYS, SIZE);
                engines[i].sort(temp);
            }
            for (i=0; i<LEN; i++)
            {
                times[i][0] += engines[i].getComparisonCount();
                times[i][1] += engines[i].getAssignmentCount();
            }
        }
    }
}