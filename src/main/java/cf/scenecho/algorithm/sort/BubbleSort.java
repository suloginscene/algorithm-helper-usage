package cf.scenecho.algorithm.sort;

import com.github.suloginscene.algorithmhelper.core.sort.Sort;

import static com.github.suloginscene.algorithmhelper.util.library.SortUtil.swap;


public class BubbleSort implements Sort {

    @Override
    public void execute(int[] array) {
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
