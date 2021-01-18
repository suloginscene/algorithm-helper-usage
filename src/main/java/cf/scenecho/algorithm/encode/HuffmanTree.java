package cf.scenecho.algorithm.encode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;


public class HuffmanTree {

    private final Map<Run, Run> runMap;


    public HuffmanTree(List<String> tokens) {
        List<Run> runs = tokens.stream().map(Run::new).collect(Collectors.toList());
        runMap = buildRunMap(runs);
        Run root = buildMinHeap();
        assignCode(root, "");
    }

    private Map<Run, Run> buildRunMap(List<Run> runs) {
        Map<Run, Run> map = new HashMap<>();
        for (Run run : runs) {
            if (map.containsKey(run)) {
                map.get(run).increaseFrequency();
            } else {
                map.put(run, run);
            }
        }
        return map;
    }

    private Run buildMinHeap() {
        PriorityQueue<Run> runHeap = new PriorityQueue<>(runMap.keySet());
        while (runHeap.size() > 1) {
            Run first = runHeap.poll();
            Run second = runHeap.poll();
            Run node = new Run(first, second);
            runHeap.add(node);
        }
        return runHeap.peek();
    }

    private void assignCode(Run run, String code) {
        if (run.getLeft() == null && run.getRight() == null) {
            run.setCode(code);
            return;
        }

        assignCode(run.getLeft(), code + "0");
        assignCode(run.getRight(), code + "1");
    }


    public Map<String, String> getMetadata() {
        Map<String, String> meta = new HashMap<>();
        runMap.forEach((k, v) -> meta.put(k.getRun(), k.getCode()));
        return meta;
    }

}
