package cf.scenecho.algorithm.sort;

import com.github.suloginscene.algorithmhelper.core.sort.Sorts;
import com.github.suloginscene.algorithmhelper.util.profiler.SortsProfiler;
import com.github.suloginscene.algorithmhelper.util.numbergenerator.IntegersFactory;
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
class SortsTest {

    Sorts sorts;

    int n = 32;
    int[] array;
    int[] before;
    int[] expected;


    @BeforeEach
    void setup() {
        sorts = new SortsProfiler(new SortFactoryImpl());

        array = IntegersFactory.stablyShuffled(n).toIntArray();
        before = array.clone();
        expected = IntegersFactory.increasingFromOne(n).toIntArray();
    }

    @AfterEach
    void print() {
        System.out.println();
    }


    @Test
    void bubble() {
        sorts.setStrategy(BUBBLE);
        sorts.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void selection() {
        sorts.setStrategy(SELECTION);
        sorts.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void insertion() {
        sorts.setStrategy(INSERTION);
        sorts.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void heap() {
        sorts.setStrategy(HEAP);
        sorts.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void merge() {
        sorts.setStrategy(MERGE);
        sorts.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void quick() {
        sorts.setStrategy(QUICK);
        sorts.sort(array);
        assertArrayEquals(expected, array);
    }

}
