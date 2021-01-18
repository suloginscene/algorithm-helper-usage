package cf.scenecho.algorithm.graph;

import com.github.suloginscene.algorithmhelper.core.graph.Graph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GraphImplTest {

    Graph<Integer> graph;

    @BeforeEach
    void setup() {
        graph = new GraphImpl();
    }

    @AfterEach
    void print() {
        graph.print();
    }


    @Test
    void test() {
    }

}
