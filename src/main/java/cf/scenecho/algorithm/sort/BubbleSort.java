package cf.scenecho.algorithm.sort;

import com.github.suloginscene.algorithmhelper.core.sort.Sort;

import static com.github.suloginscene.algorithmhelper.util.SortUtil.swap;


public class BubbleSort extends Sort {

    @Override
    protected void doExecute(int[] array) {
        int n = array.length;
        int first = 0;
        int last = n - 1;

        for (int dest = first; dest < last; dest++) {
            for (int src = last; src > dest; src--) {
                if (array[src - 1] > array[src]) {
                    swap(array, src - 1, src);
                }
            }
        }
    }

}
