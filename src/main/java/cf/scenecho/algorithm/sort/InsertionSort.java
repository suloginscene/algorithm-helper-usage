package cf.scenecho.algorithm.sort;

import com.github.suloginscene.algorithmhelper.core.sort.Sort;

import static java.lang.System.arraycopy;


public class InsertionSort extends Sort {

    @Override
    protected void doExecute(int[] array) {
        int n = array.length;
        int first = 0;
        int last = n - 1;

        for (int src = first + 1; src <= last; src++) {
            int value = array[src];

            int dest = src;
            for (int altDest = src - 1; altDest >= first; altDest--) {
                if (array[altDest] > value) {
                    dest = altDest;
                }
            }

            arraycopy(array, dest, array, dest + 1, src - dest);
            array[dest] = value;
        }
    }

}
