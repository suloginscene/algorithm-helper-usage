package cf.scenecho.algorithm.sort;

import com.github.suloginscene.algorithmhelper.core.sort.Sort;
import com.github.suloginscene.algorithmhelper.core.sort.SortContainer;


public class SortContainerImpl implements SortContainer {

    private final Sort bubble = new BubbleSort();
    private final Sort selection = new SelectionSort();
    private final Sort insertion = new InsertionSort();
    private final Sort merge = new MergeSort();
    private final Sort heap = new HeapSort();
    private final Sort quick = new QuickSort();


    @Override
    public Sort bubble() {
        return bubble;
    }

    @Override
    public Sort selection() {
        return selection;
    }

    @Override
    public Sort insertion() {
        return insertion;
    }

    @Override
    public Sort merge() {
        return merge;
    }

    @Override
    public Sort heap() {
        return heap;
    }

    @Override
    public Sort quick() {
        return quick;
    }

}
