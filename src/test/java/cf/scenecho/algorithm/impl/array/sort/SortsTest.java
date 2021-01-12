package cf.scenecho.algorithm.impl.array.sort;

import com.github.suloginscene.algorithm.helper.array.sort.Sorts;
import com.github.suloginscene.algorithm.helper.array.sort.support.SortsProfiler;
import com.github.suloginscene.algorithm.helper.integers.IntegersFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.github.suloginscene.algorithm.helper.array.sort.Strategy.BUBBLE;
import static com.github.suloginscene.algorithm.helper.array.sort.Strategy.HEAP;
import static com.github.suloginscene.algorithm.helper.array.sort.Strategy.INSERTION;
import static com.github.suloginscene.algorithm.helper.array.sort.Strategy.MERGE;
import static com.github.suloginscene.algorithm.helper.array.sort.Strategy.QUICK;
import static com.github.suloginscene.algorithm.helper.array.sort.Strategy.SELECTION;
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

        array = IntegersFactory.stablyShuffled(n).toArray();
        before = array.clone();
        expected = Arrays.stream(array).sorted().toArray();
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
