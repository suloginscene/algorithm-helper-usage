package cf.scenecho.algorithm.sort;

import com.github.suloginscene.algorithmhelper.core.sort.Sorter;
import com.github.suloginscene.algorithmhelper.core.sort.SorterProfiler;
import com.github.suloginscene.algorithmhelper.util.IntegersFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.suloginscene.algorithmhelper.core.sort.Strategy.BUBBLE;
import static com.github.suloginscene.algorithmhelper.core.sort.Strategy.HEAP;
import static com.github.suloginscene.algorithmhelper.core.sort.Strategy.INSERTION;
import static com.github.suloginscene.algorithmhelper.core.sort.Strategy.MERGE;
import static com.github.suloginscene.algorithmhelper.core.sort.Strategy.QUICK;
import static com.github.suloginscene.algorithmhelper.core.sort.Strategy.SELECTION;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@Slf4j
class SorterTest {

    Sorter sorter;

    int n = 32;
    int[] array;
    int[] before;
    int[] expected;


    @BeforeEach
    void setup() {
        sorter = new SorterProfiler(new SortContainerImpl());

        array = IntegersFactory.stablyShuffled(n, true).toIntArray();
        before = array.clone();
        expected = IntegersFactory.increasingFromOne(n).toIntArray();
    }

    @AfterEach
    void print() {
        System.out.println();
    }


    @Test
    void bubble() {
        sorter.setStrategy(BUBBLE);
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void selection() {
        sorter.setStrategy(SELECTION);
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void insertion() {
        sorter.setStrategy(INSERTION);
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void heap() {
        sorter.setStrategy(HEAP);
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void merge() {
        sorter.setStrategy(MERGE);
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void quick() {
        sorter.setStrategy(QUICK);
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

}
