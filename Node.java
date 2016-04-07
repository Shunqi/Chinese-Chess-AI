
public class Node {
    public String piece;
    public int[] from;
    public int[] to;
    public int value;

    public Node(String piece, int[] from, int[] to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }
}