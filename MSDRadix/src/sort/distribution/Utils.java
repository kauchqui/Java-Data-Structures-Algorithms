package sort.distribution;


import java.util.Random;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Utils
{
    /**
     * @param beginIndex the beginning index (inclusive).
     * @param endIndex the ending index (inclusive).
     * @return the middle index between the beginning and ending indices.
     */
    static public int getMiddleIndex(int beginIndex, int endIndex)
    {
        return beginIndex + (endIndex - beginIndex) / 2;
    }

    static public void main(String[] args)
    {
        System.out.println(getMiddleIndex(0, 10));
    }

    static public int[] getRandomIntArray(Random rand, int size)
    {
        int[] array = new int[size];

        for (int i=0; i<size; i++)
            array[i] = rand.nextInt();

        return array;
    }

    static public Integer[] getRandomIntegerArray(Random rand, int size, int range)
    {
        Integer[] array = new Integer[size];

        for (int i=0; i<size; i++)
            array[i] = rand.nextInt(range);

        return array;
    }
}