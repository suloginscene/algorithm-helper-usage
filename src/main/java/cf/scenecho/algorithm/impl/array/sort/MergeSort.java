package cf.scenecho.algorithm.impl.array.sort;

import com.github.suloginscene.algorithm.helper.array.sort.Sort;

import static java.lang.System.arraycopy;


public class MergeSort implements Sort {

    @Override
    public void execute(int[] array) {
        int n = array.length;
        int first = 0;
        int last = n - 1;

        mergeSort(array, first, last);
    }

    private void mergeSort(int[] array, int head, int tail) {
        if (head >= tail) return;

        int mid = (head + tail) / 2;

        mergeSort(array, head, mid);
        mergeSort(array, mid + 1, tail);

        merge(array, head, mid, mid + 1, tail);
    }

    private void merge(int[] array, int aHead, int aTail, int bHead, int bTail) {
        int n = bTail - aHead + 1;
        int[] arr = new int[n];
        int ptr = 0;

        int aPtr = aHead;
        int bPtr = bHead;

        while ((aPtr <= aTail) && (bPtr <= bTail)) {
            arr[ptr++] = (array[aPtr] < array[bPtr]) ? array[aPtr++] : array[bPtr++];
        }

        while (ptr < n) {
            arr[ptr++] = aPtr <= aTail ? array[aPtr++] : array[bPtr++];
        }

        arraycopy(arr, 0, array, aHead, n);
    }

}
