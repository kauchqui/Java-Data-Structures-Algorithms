package review.sort.comparison;

/**
 * Copyright 2014, Emory University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


        import org.junit.jupiter.api.Test;
        import review.sort.AbstractSort;
        import review.sort.comparison.HeapSort;
        import review.sort.comparison.InsertionSort;
        import review.sort.comparison.SelectionSort;
        import review.sort.comparison.ShellSortKnuth;
        //import sort.distribution.IntegerBucketSort;
        //import sort.distribution.LSDRadixSort;
        //import sort.divide_conquer.IntroSort;
        //import sort.divide_conquer.MergeSort;
        //import review.sort.divide_conquer.QuickSort;
        import review.sort.comparison.utils;

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

        testAccuracy(ITERATIONS, SIZE, new SelectionSort<>());
        testAccuracy(ITERATIONS, SIZE, new InsertionSort<>());
        testAccuracy(ITERATIONS, SIZE, new HeapSort<>());
        testAccuracy(ITERATIONS, SIZE, new ShellSortKnuth<>());
        /*testAccuracy(ITERATIONS, SIZE, new MergeSort<>());
        testAccuracy(ITERATIONS, SIZE, new QuickSort<>());
        testAccuracy(ITERATIONS, SIZE, new IntroSort<>(new HeapSort<Integer>()));
        testAccuracy(ITERATIONS, SIZE, new IntroSort<>(new ShellSortKnuth<Integer>()));
        testAccuracy(ITERATIONS, SIZE, new IntegerBucketSort(0, SIZE));
        testAccuracy(ITERATIONS, SIZE, new LSDRadixSort());*/
//      testAccuracy(ITERATIONS, SIZE, new MSDRadixSort());
    }

    private void testAccuracy(final int ITERATIONS, final int SIZE, AbstractSort<Integer> engine)
    {
        final Random rand = new Random(0);
        Integer[] original, sorted;

        for (int i=0; i<ITERATIONS; i++)
        {
            original = utils.getRandomIntegerArray(rand, SIZE, SIZE);
            sorted = Arrays.copyOf(original, SIZE);

            engine.sort(original);
            Arrays.sort(sorted);

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
        comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND, new HeapSort<>(), new ShellSortKnuth<>(), new SelectionSort<>(), new InsertionSort<>());
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
        comp.printTotal(ITERATIONS, INIT_SIZE, MAX_SIZE, INCREMENT, OPS, RAND, new HeapSort<>(), new ShellSortKnuth<>(), new SelectionSort<>(), new InsertionSort<>());
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
            final Integer[] KEYS = utils.getRandomIntegerArray(RAND, SIZE, SIZE);
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
            final Integer[] KEYS = utils.getRandomIntegerArray(RAND, SIZE, SIZE);
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
