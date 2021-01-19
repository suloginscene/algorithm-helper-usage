package cf.scenecho.algorithm.sort;

import com.github.suloginscene.algorithmhelper.core.sort.Sort;

import static com.github.suloginscene.algorithmhelper.util.SortUtil.swap;


public class QuickSort extends Sort {

    @Override
    protected void doExecute(int[] array) {
        int n = array.length;
        int first = 0;
        int last = n - 1;

        quickSort(array, first, last);
    }

    private void quickSort(int[] array, int first, int last) {
        if (first >= last) return;

        int pivot = partition(array, first, last);

        quickSort(array, first, pivot - 1);
        quickSort(array, pivot + 1, last);
    }

    private int partition(int[] array, int first, int last) {
        int pivot = getPivot(array, first, last);

        int swapPtr = first;
        int destPtr = first;

        while (swapPtr < last) {
            if (array[swapPtr] < pivot) {
                swap(array, swapPtr, destPtr);
                destPtr++;
            }
            swapPtr++;
        }

        swap(array, destPtr, last);
        return destPtr;
    }

    private int getPivot(int[] array, int first, int last) {
        int mid = (first + last) / 2;
        swap(array, mid, last);
        return array[last];
    }

}
