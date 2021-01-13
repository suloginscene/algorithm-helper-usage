package cf.scenecho.algorithm.sort;

import com.github.suloginscene.algorithmhelper.core.sort.Sort;

import static com.github.suloginscene.algorithmhelper.util.library.SortUtil.swap;


public class SelectionSort implements Sort {

    @Override
    public void execute(int[] array) {
        int n = array.length;
        int first = 0;
        int last = n - 1;

        for (int dest = first; dest < last; dest++) {
            int src = dest;

            for (int altSrc = dest + 1; altSrc <= last; altSrc++) {
                if (array[altSrc] < array[src]) {
                    src = altSrc;
                }
            }
            swap(array, src, dest);
        }
    }

}
