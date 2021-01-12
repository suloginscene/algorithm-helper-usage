package cf.scenecho.algorithm.impl.array.sort;

import com.github.suloginscene.algorithm.helper.array.sort.Sort;

import static java.lang.System.arraycopy;


public class InsertionSort implements Sort {

    @Override
    public void execute(int[] array) {
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
