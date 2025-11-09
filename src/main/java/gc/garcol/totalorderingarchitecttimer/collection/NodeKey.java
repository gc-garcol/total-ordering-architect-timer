package gc.garcol.totalorderingarchitecttimer.collection;

public record NodeKey(long id, long timeout) implements Comparable<NodeKey> {
    @Override
    public int compareTo(NodeKey thatNode) {
        if (this.id == thatNode.id) {
            return 0;
        }

        if (this.timeout == thatNode.timeout) {
            return Long.compare(this.id, thatNode.id);
        }
        return Long.compare(this.timeout, thatNode.timeout);
    }
}
