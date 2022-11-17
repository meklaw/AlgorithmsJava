package school.graph;

public class Vertex {
    public int Value;
    public boolean hit = false;
    public int from = -1;
    public boolean isTriangle = false;

    public Vertex(int val) {
        Value = val;
    }
}
