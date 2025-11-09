package gc.garcol.totalorderingarchitecttimer.collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

@Slf4j
@RequiredArgsConstructor
public class TreeTimer {
    final String           name;
    final TreeSet<NodeKey> timeoutTree = new TreeSet<>();

    public void add(long id, long timeout) {
        NodeKey key = new NodeKey(id, timeout);
        timeoutTree.add(key);
    }

    public List<NodeKey> pollTimeoutNodes(long timeout) {
        NodeKey pivot = new NodeKey(Long.MAX_VALUE, timeout);
        NavigableSet<NodeKey> timeoutNodes = timeoutTree.headSet(pivot, true);
        var result = timeoutNodes.stream().toList();
        timeoutNodes.clear();
        return result;
    }
}
