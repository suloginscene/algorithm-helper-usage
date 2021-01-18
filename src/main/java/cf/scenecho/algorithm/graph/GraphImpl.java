package cf.scenecho.algorithm.graph;

import com.github.suloginscene.algorithmhelper.core.graph.Course;
import com.github.suloginscene.algorithmhelper.core.graph.Graph;
import com.github.suloginscene.algorithmhelper.core.graph.Path;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


public class GraphImpl extends Graph<Integer> {

    private final Map<Integer, Set<Integer>> vertexMap = new HashMap<>();
    private final Map<Path<Integer>, Path<Integer>> pathMap = new HashMap<>();


    @Override
    protected Map<Integer, Set<Integer>> getVertexMap() {
        return vertexMap;
    }

    @Override
    protected Map<Path<Integer>, Path<Integer>> getPathMap() {
        return pathMap;
    }


    @Override
    public void bfs(@NonNull Integer start, Consumer<Integer> consumer) {

    }

    @Override
    public void dfs(@NonNull Integer start, Consumer<Integer> consumer) {

    }

    @Override
    protected void doTopologicalSort(Consumer<Integer> consumer) {

    }

    @Override
    public Map<Integer, Course<Integer>> shortestCourses(@NonNull Integer start) {
        return null;
    }

    @Override
    public Graph<Integer> minimumSpanningTree() {
        return null;
    }

}
