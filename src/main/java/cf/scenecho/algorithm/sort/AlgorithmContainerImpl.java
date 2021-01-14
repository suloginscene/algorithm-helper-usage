package cf.scenecho.algorithm.sort;

import com.github.suloginscene.algorithmhelper.core.sort.Algorithm;
import com.github.suloginscene.algorithmhelper.core.sort.AlgorithmContainer;


public class AlgorithmContainerImpl implements AlgorithmContainer {

    private final Algorithm bubble = new BubbleSort();
    private final Algorithm selection = new SelectionSort();
    private final Algorithm insertion = new InsertionSort();
    private final Algorithm merge = new MergeSort();
    private final Algorithm heap = new HeapSort();
    private final Algorithm quick = new QuickSort();


    @Override
    public Algorithm bubble() {
        return bubble;
    }

    @Override
    public Algorithm selection() {
        return selection;
    }

    @Override
    public Algorithm insertion() {
        return insertion;
    }

    @Override
    public Algorithm merge() {
        return merge;
    }

    @Override
    public Algorithm heap() {
        return heap;
    }

    @Override
    public Algorithm quick() {
        return quick;
    }

}
