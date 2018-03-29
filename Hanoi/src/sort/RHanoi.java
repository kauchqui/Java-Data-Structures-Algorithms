package sort;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class RHanoi extends AbstractHanoi
{
    @Override
    public List<String> solve(int n, char source, char intermediate, char destination)
    {
        List<String> list = new ArrayList<>();
        solve(list, n, source, intermediate, destination);
        return list;
    }

    private void solve(List<String> list, int n, char source, char intermediate, char destination)
    {
        if (n == 0) return;

        //Move all plates from 'source' to 'intermediate' via 'destination' as medium
        solve(list, n-1, source, destination, intermediate);

        //Record the step
        list.add(getKey(n, source, destination));

        //Move all plates from 'intermediate' to 'destination' via 'source' as medium
        solve(list, n-1, intermediate, source, destination);
    }
}
