package cf.scenecho.algorithm.impl.array.sort;

import com.github.suloginscene.algorithm.helper.array.sort.Sort;

import static com.github.suloginscene.algorithm.helper.array.sort.support.SortUtil.swap;
import static java.lang.System.arraycopy;


public class HeapSort implements Sort {

    @Override
    public void execute(int[] array) {
        int n = array.length;
        int head = 0;

        int[] heap = new int[n + 1];
        int root = 1;

        arraycopy(array, head, heap, root, n);

        buildMaxHeap(heap, root, n);
        heapSort(heap);

        arraycopy(heap, root, array, head, n);
    }


    private void buildMaxHeap(int[] heap, int root, int n) {
        if (root > (n / 2)) return;

        buildMaxHeap(heap, (root * 2), n);
        buildMaxHeap(heap, (root * 2) + 1, n);

        maxHeapify(heap, root, n);
    }

    private void maxHeapify(int[] heap, int root, int n) {
        if (root > (n / 2)) return;

        int bigger = biggerChild(heap, root, n);
        if (heap[root] >= heap[bigger]) return;

        swap(heap, root, bigger);
        maxHeapify(heap, bigger, n);
    }

    private int biggerChild(int[] heap, int root, int n) {
        int left = root * 2;
        int right = left + 1;

        if (right > n) return left;
        return (heap[left] > heap[right]) ? left : right;
    }


    private void heapSort(int[] heap) {
        int first = 1;
        int last = heap.length - 1;

        for (int i = last; i > first; i--) {
            swap(heap, first, i);
            maxHeapify(heap, first, i - 1);
        }
    }

}
