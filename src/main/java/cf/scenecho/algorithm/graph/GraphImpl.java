package cf.scenecho.algorithm.graph;

import com.github.suloginscene.algorithmhelper.core.graph.Alphabet;
import com.github.suloginscene.algorithmhelper.core.graph.Course;
import com.github.suloginscene.algorithmhelper.core.graph.Graph;
import com.github.suloginscene.algorithmhelper.core.graph.Path;
import com.github.suloginscene.algorithmhelper.core.graph.SetSet;
import lombok.NonNull;

import java.util.*;
import java.util.function.Consumer;


public class GraphImpl extends Graph<Alphabet> {

    private final Map<Alphabet, Set<Alphabet>> vertexMap = new HashMap<>();
    private final Map<Path<Alphabet>, Path<Alphabet>> pathMap = new HashMap<>();


    @Override
    protected Map<Alphabet, Set<Alphabet>> getVertexMap() {
        return vertexMap;
    }

    @Override
    protected Map<Path<Alphabet>, Path<Alphabet>> getPathMap() {
        return pathMap;
    }


    @Override
    public void bfs(@NonNull Alphabet start, Consumer<Alphabet> consumer) {
        Set<Alphabet> visited = new HashSet<>();

        visited.add(start);
        consumer.accept(start);

        Set<Alphabet> neighbors = vertexMap.get(start);
        Queue<Alphabet> queue = new LinkedList<>(neighbors);

        while (!queue.isEmpty()) {
            Alphabet neighbor = queue.poll();
            visited.add(neighbor);
            consumer.accept(neighbor);
            if (vertexMap.containsKey(neighbor)) {
                for (Alphabet subNeighbor : vertexMap.get(neighbor)) {
                    if (!visited.contains(subNeighbor) && !queue.contains(subNeighbor)) {
                        queue.add(subNeighbor);
                    }
                }
            }
        }
    }

    @Override
    public void dfs(@NonNull Alphabet start, Consumer<Alphabet> consumer) {
        Set<Alphabet> visited = new HashSet<>();
        recursiveDfs(start, consumer, visited);
    }

    private void recursiveDfs(Alphabet vertex, Consumer<Alphabet> consumer, Set<Alphabet> visited) {
        if (visited.contains(vertex)) return;

        visited.add(vertex);
        consumer.accept(vertex);

        if (vertexMap.containsKey(vertex)) {
            for (Alphabet neighbor : vertexMap.get(vertex)) {
                recursiveDfs(neighbor, consumer, visited);
            }
        }
    }

    @Override
    protected void doTopologicalSort(Consumer<Alphabet> consumer) {
        Set<Alphabet> visited = new HashSet<>();
        Stack<Alphabet> stack = new Stack<>();

        for (Alphabet key : vertexMap.keySet()) {
            if (!visited.contains(key)) {
                stackDfs(key, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            consumer.accept(stack.pop());
        }
    }

    private void stackDfs(Alphabet vertex, Set<Alphabet> visited, Stack<Alphabet> stack) {
        if (visited.contains(vertex)) return;

        visited.add(vertex);
        if (vertexMap.containsKey(vertex)) {
            for (Alphabet neighbor : vertexMap.get(vertex)) {
                stackDfs(neighbor, visited, stack);
            }
        }
        stack.push(vertex);
    }


    @Override
    protected Map<Alphabet, Course<Alphabet>> doShortestCourses(@NonNull Alphabet start) {
        Set<Alphabet> vertices = allVertices();

        Map<Alphabet, Integer> distances = new HashMap<>();
        Map<Alphabet, Alphabet> predecessor = new HashMap<>();
        for (Alphabet vertex : vertices) {
            distances.put(vertex, Integer.MAX_VALUE);
            predecessor.put(vertex, null);
        }
        distances.put(start, 0);

        Set<Alphabet> visited = new HashSet<>();
        while (visited.size() < vertices.size()) {
            Alphabet current = null;
            int currDist = Integer.MAX_VALUE;
            for (Map.Entry<Alphabet, Integer> e : distances.entrySet()) {
                Alphabet tempVertex = e.getKey();
                Integer tempDist = e.getValue();
                if (!visited.contains(tempVertex) && (tempDist < currDist)) {
                    current = tempVertex;
                    currDist = tempDist;
                }
            }
            visited.add(current);
            if (vertexMap.containsKey(current)) {
                for (Alphabet neighbor : vertexMap.get(current)) {
                    int knownDist = distances.get(neighbor);
                    int altDist = currDist + pathMap.get(new Path<>(current, neighbor, 0)).getWeight();
                    if (altDist < knownDist) {
                        distances.put(neighbor, altDist);
                        predecessor.put(neighbor, current);
                    }
                }
            }
        }

        Map<Alphabet, Course<Alphabet>> courses = new HashMap<>();
        for (Alphabet dest : vertices) {
            Stack<Alphabet> stack = new Stack<>();
            Alphabet ptr = dest;
            while (ptr != null) {
                stack.add(ptr);
                ptr = predecessor.get(ptr);
            }
            List<Alphabet> list = new ArrayList<>();
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
            courses.put(dest, new Course<>(list, distances.get(dest)));
        }
        return courses;
    }


    @Override
    protected Graph<Alphabet> doMinimumSpanningTree() {
        SetSet<Alphabet> sets = new SetSet<>();
        for (Alphabet vertex : allVertices()) {
            Set<Alphabet> set = new HashSet<>();
            set.add(vertex);
            sets.add(set);
        }

        PriorityQueue<Path<Alphabet>> pq = new PriorityQueue<>(pathMap.keySet());

        GraphImpl graph = new GraphImpl();
        while (sets.size() > 1 && !pq.isEmpty()) {
            Path<Alphabet> shortest = pq.poll();
            Alphabet from = shortest.getFrom();
            Alphabet to = shortest.getTo();
            Set<Alphabet> setA = sets.findSet(from);
            Set<Alphabet> setB = sets.findSet(to);
            if (setA != setB) {
                graph.addEdge(from, to, shortest.getWeight());
                sets.union(setA, setB);
            }
        }
        return graph;
    }

}
