package cf.scenecho.algorithm.graph;

import com.github.suloginscene.algorithmhelper.util.Alphabet;
import com.github.suloginscene.algorithmhelper.core.graph.Course;
import com.github.suloginscene.algorithmhelper.core.graph.Graph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.suloginscene.algorithmhelper.util.Alphabet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class GraphImplTest {

    Graph<Alphabet> graph;


    @BeforeEach
    void setup() {
        graph = new GraphImpl();
    }

    void initUnWeighted() {
        graph.addEdge(A, B, 0);
        graph.addEdge(B, C, 0);
        graph.addEdge(C, D, 0);
        graph.addEdge(D, E, 0);

        graph.addEdge(A, D, 0);
    }

    void initWeighted() {
        graph.addEdge(A, B, 1);
        graph.addEdge(B, C, 2);
        graph.addEdge(C, D, 3);
        graph.addEdge(D, E, 4);

        graph.addEdge(A, D, 5);
    }

    @AfterEach
    void print() {
        graph.print();
    }


    @Test
    void bfs() {
        initUnWeighted();

        List<Alphabet> result = new ArrayList<>();
        graph.bfs(A, result::add);

        System.out.println(result);
    }

    @Test
    void dfs() {
        initUnWeighted();

        List<Alphabet> result = new ArrayList<>();
        graph.dfs(A, result::add);

        System.out.println(result);
    }

    @Test
    void topologicalSort() {
        initUnWeighted();

        List<Alphabet> result = new ArrayList<>();
        graph.topologicalSort(result::add);

        System.out.println(result);
    }

    @Test
    void shortest() {
        initWeighted();

        Map<Alphabet, Course<Alphabet>> courses = graph.shortestCourses(A);
        Course<Alphabet> toE = courses.get(E);
        toE.print();

        assertEquals(9, toE.getDistance());
    }

    @Test
    void mst() {
        initWeighted();

        graph = graph.minimumSpanningTree();

        assertFalse(graph.hasCycle());
        assertEquals(graph.allVertices().size() - 1, graph.allEdges().size());
    }

}
