package sort;


        import sort.AbstractHanoi;
        import sort.DHanoi;
        import sort.RHanoi;
        import jdk.nashorn.internal.ir.annotations.Ignore;
        import org.junit.jupiter.api.Test;

        import java.util.Arrays;
        import java.util.stream.Collectors;

        import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class HanoiTest
{
    @Test
    public void compare()
    {
        final char source       = 'S';
        final char intermediate = 'I';
        final char destination  = 'D';

        AbstractHanoi recursive = new RHanoi();
        AbstractHanoi dynamic   = new DHanoi();

        for (int k=1; k<20; k++)
            assertEquals(recursive.solve(k, source, intermediate, destination), dynamic.solve(k, source, intermediate, destination));
    }

    @Test
    @Ignore
    public void testSpeed()
    {
        final int ITERATIONS = 100;
        final int MAX_K      = 20;

        AbstractHanoi recursive = new RHanoi();
        AbstractHanoi dynamic   = new DHanoi();

        for (int k=2; k<MAX_K; k++)
            System.out.println(testSpeed(ITERATIONS, k, dynamic, recursive));

    }

    String testSpeed(final int iterations, final int k, AbstractHanoi... solver)
    {
        long[] times = new long[solver.length];
        int i, j, len = solver.length;
        long st, et;

        for (i=0; i<len; i++)
        {
            st = System.currentTimeMillis();
            for (j=0; j<iterations; j++) solver[i].solve(k,'S','I','D');
            et = System.currentTimeMillis();
            times[i] = et - st;
        }
        System.out.println("Solve calls: Dynamic: " +"Recursive: ");
        return k+"\t"+ Arrays.stream(times).mapToObj(n -> Long.toString(n)).collect(Collectors.joining("\t"));
    }
}
