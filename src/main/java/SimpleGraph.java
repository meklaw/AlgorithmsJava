class Vertex {
    public int Value;

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
        int position = -1;
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null) {
                position = i;
                break;
            }
        }
        if (position == -1)
            return;
        vertex[position] = new Vertex(value);
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        // ваш код удаления вершины со всеми её рёбрами
        int index = indexOf(v);
        if (index == -1)
            return;
        for (int i = 0; i < m_adjacency.length; i++) {
            m_adjacency[index][i] = 0;
            m_adjacency[i][index] = 0;
        }
        vertex[index] = null;
    }

    public boolean IsEdge(int v1, int v2) {
        // true если есть ребро между вершинами v1 и v2
        int firstIndex = indexOf(v1);
        int secondIndex = indexOf(v2);
        if (firstIndex == -1 || secondIndex == -1)
            return false;
        return m_adjacency[firstIndex][secondIndex] == 1;
    }

    public void AddEdge(int v1, int v2) {
        // добавление ребра между вершинами v1 и v2
        int firstIndex = indexOf(v1);
        int secondIndex = indexOf(v2);
        if (firstIndex == -1 || secondIndex == -1)
            return;
        m_adjacency[firstIndex][secondIndex] = 1;
        m_adjacency[secondIndex][firstIndex] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        // удаление ребра между вершинами v1 и v2
        int firstIndex = indexOf(v1);
        int secondIndex = indexOf(v2);
        if (firstIndex == -1 || secondIndex == -1)
            return;
        m_adjacency[firstIndex][secondIndex] = 0;
        m_adjacency[secondIndex][firstIndex] = 0;
    }

    public int indexOf(int value) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null && vertex[i].Value == value)
                return i;
        }
        return -1;
    }
}