import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

class Vertex {
    public int Value;
    public boolean hit = false;
    public int from = -1;
    public boolean isTriangle = false;

    public Vertex(int val) {
        Value = val;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        // ваш код добавления новой вершины
        // с значением value
        // в незанятую позицию vertex
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null) {
                vertex[i] = new Vertex(value);
                return;
            }
        }
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        // ваш код удаления вершины со всеми её рёбрами
        if (v < 0 || vertex.length <= v)
            return;
        for (int i = 0; i < m_adjacency.length; i++) {
            m_adjacency[v][i] = 0;
            m_adjacency[i][v] = 0;
        }
        vertex[v] = null;
    }

    public boolean IsEdge(int v1, int v2) {
        // true если есть ребро между вершинами v1 и v2
        if (v1 < 0 || vertex.length <= v1 || v2 < 0 || vertex.length <= v2)
            return false;
        return m_adjacency[v1][v2] == 1;
    }

    public void AddEdge(int v1, int v2) {
        // добавление ребра между вершинами v1 и v2
        if (v1 < 0 || vertex.length <= v1 || v2 < 0 || vertex.length <= v2)
            return;
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        // удаление ребра между вершинами v1 и v2
        if (v1 < 0 || vertex.length <= v1 || v2 < 0 || vertex.length <= v2)
            return;
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        Stack<Integer> route = new Stack<>();
        clearHits();
        route.push(VFrom);

        for (int currentVert = VFrom;
             route.size() != 0 && currentVert != VTo;
             currentVert = route.peek()) {
            vertex[currentVert].hit = true;
            int nextVertex = findNextVertexDFS(currentVert, VTo);

            if (nextVertex == -1 && route.size() == 1) {
                route.pop();
                break;
            }

            if (nextVertex == -1) {
                route.pop();
                continue;
            }

            route.push(nextVertex);
        }

        return (ArrayList<Vertex>) route.stream().map(index -> vertex[index]).collect(Collectors.toList());
    }

    private int findNextVertexDFS(int currentVert, int target) {
        int nextVert = -1;
        for (int i = 0; i < m_adjacency.length; i++) {
            if (m_adjacency[currentVert][i] == 1 && i == target)
                return i;
            if (m_adjacency[currentVert][i] == 1 && !vertex[i].hit && currentVert != i)
                nextVert = i;
        }
        return nextVert;
    }

    private void clearHits() {
        for (Vertex ver : vertex) {
            ver.hit = false;
            ver.from = -1;
        }
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        if (VTo < 0 || max_vertex <= VTo || VFrom < 0 || max_vertex <= VFrom)
            return new ArrayList<>();

        clearHits();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.push(VFrom);
        for (int currentVert = deque.pop(); deque.size() != 0 || !vertex[VFrom].hit; currentVert = deque.pop()) {
            if (currentVert == VTo)
                break;
            vertex[currentVert].hit = true;
            addEdgesToDeque(deque, currentVert);
            if (deque.size() == 0)
                break;
        }

        return makeRouteBFS(VFrom, VTo);
    }

    private void addEdgesToDeque(ArrayDeque<Integer> deque, int currentVert) {
        for (int i = 0; i < m_adjacency.length; i++) {
            boolean isCorrectNextVert = IsEdge(currentVert, i) && !vertex[i].hit && currentVert != i;
            if (isCorrectNextVert) {
                deque.add(i);
            }
            if (isCorrectNextVert && vertex[i].from == -1) {
                vertex[i].from = currentVert;
            }
        }
    }

    private ArrayList<Vertex> makeRouteBFS(int VFrom, int VTo) {
        ArrayList<Vertex> route = new ArrayList<>();
        if (vertex[VTo].from == -1)
            return route;
        for (int currentVert = VTo; currentVert != -1; currentVert = vertex[currentVert].from) {
            route.add(vertex[currentVert]);
            if (currentVert == VFrom)
                break;
        }
        Collections.reverse(route);
        return route;
    }

    public ArrayList<Vertex> WeakVertices() {
        // возвращает список узлов вне треугольников
        cleanTriangles();
        checkTriangles();
        return makeNonTriangleList();
    }

    private void cleanTriangles() {
        for (int currentVert = 0; currentVert < max_vertex; currentVert++) {
            if (vertex[currentVert] != null)
                vertex[currentVert].isTriangle = false;
        }
    }

    private void checkTriangles() {
        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] != null && !vertex[i].isTriangle)
                isTriangle(i);
        }
    }

    private void isTriangle(int meinIndex) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] != null && IsEdge(meinIndex, i))
                neighbors.add(i);
        }
        for (int firstNeighbor = 0; firstNeighbor < neighbors.size(); firstNeighbor++) {
            for (int secondNeighbor = firstNeighbor + 1; secondNeighbor < neighbors.size(); secondNeighbor++) {
                if (IsEdge(neighbors.get(firstNeighbor), neighbors.get(secondNeighbor))) {
                    vertex[meinIndex].isTriangle = true;
                    vertex[firstNeighbor].isTriangle = true;
                    vertex[secondNeighbor].isTriangle = true;
                }
            }
        }
    }

    private ArrayList<Vertex> makeNonTriangleList() {
        ArrayList<Vertex> nonTriangles = new ArrayList<>();
        for (int currentVert = 0; currentVert < max_vertex; currentVert++) {
            if (vertex[currentVert] != null && !vertex[currentVert].isTriangle)
                nonTriangles.add(vertex[currentVert]);
        }
        return nonTriangles;
    }
}