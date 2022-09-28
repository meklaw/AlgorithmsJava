import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {
    SimpleGraph graph;

    @BeforeEach
    void setUp() {
        graph = new SimpleGraph(4);
        graph.AddVertex(10);
        graph.AddVertex(15);
        graph.AddVertex(20);
    }

    @Test
    void addVertex() {
        graph = new SimpleGraph(4);
        graph.AddVertex(10);
        graph.AddVertex(15);
        graph.AddVertex(20);
        assertEquals(graph.vertex[0].Value, 10);
        assertEquals(graph.vertex[1].Value, 15);
        assertEquals(graph.vertex[2].Value, 20);
        for (int i = 0; i < graph.m_adjacency.length; i++) {
            for (int j = 0; j < graph.m_adjacency.length; j++) {
                assertEquals(graph.m_adjacency[i][j], 0);
            }
        }
    }

    @Test
    void removeVertex() {
        graph.AddEdge(0, 0);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 2);

        graph.RemoveVertex(0);


        for (int i = 0; i < graph.m_adjacency.length; i++) {
            assertEquals(graph.m_adjacency[0][i], 0);
            assertEquals(graph.m_adjacency[i][0], 0);
        }

    }

    @Test
    void isEdge() {
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 0);
        graph.AddEdge(2, 1);
        assertTrue(graph.IsEdge(0, 2));
        assertTrue(graph.IsEdge(2, 0));
        assertTrue(graph.IsEdge(0, 0));
        assertTrue(graph.IsEdge(2, 1));
        assertTrue(graph.IsEdge(1, 2));
        assertFalse(graph.IsEdge(0, 1));

    }

    @Test
    void addEdge() {
        graph.AddEdge(0, 100);
        graph.AddEdge(0, 2);
        assertEquals(graph.m_adjacency[0][2], 1);
        assertEquals(graph.m_adjacency[2][0], 1);
        graph.AddEdge(0, 0);
        assertEquals(graph.m_adjacency[0][0], 1);
    }

    @Test
    void removeEdge() {
        graph.AddEdge(0, 0);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        assertTrue(graph.IsEdge(0, 0));
        assertTrue(graph.IsEdge(0, 1));
        assertTrue(graph.IsEdge(0, 2));
        graph.RemoveEdge(1, 0);
        assertTrue(graph.IsEdge(0, 0));
        assertFalse(graph.IsEdge(0, 1));
        assertFalse(graph.IsEdge(1, 0));
        assertTrue(graph.IsEdge(0, 2));
    }

    //    10, 15, 20, 30
    @Test
    void DepthFirstSearch() {
        graph.AddVertex(30);
        assertEquals(graph.DepthFirstSearch(0, 0).get(0).Value, 10);
        graph.AddEdge(0, 2);
        assertEquals(graph.DepthFirstSearch(0, 3).size(), 0);
        assertEquals(graph.DepthFirstSearch(0, 1).size(), 0);
        assertEquals(graph.DepthFirstSearch(2, 1).size(), 0);
        assertEquals(graph.DepthFirstSearch(2, 3).size(), 0);

        assertEquals(graph.DepthFirstSearch(0, 2).get(0).Value, 10);
        assertEquals(graph.DepthFirstSearch(0, 2).get(1).Value, 20);
        assertEquals(graph.DepthFirstSearch(2, 0).get(0).Value, 20);
        assertEquals(graph.DepthFirstSearch(2, 0).get(1).Value, 10);
        graph.AddEdge(1, 2);
        assertEquals(graph.DepthFirstSearch(0, 1).get(0).Value, 10);
        assertEquals(graph.DepthFirstSearch(0, 1).get(1).Value, 20);
        assertEquals(graph.DepthFirstSearch(0, 1).get(2).Value, 15);
        graph.AddEdge(1, 3);
        assertEquals(graph.DepthFirstSearch(0, 3).get(0).Value, 10);
        assertEquals(graph.DepthFirstSearch(0, 3).get(1).Value, 20);
        assertEquals(graph.DepthFirstSearch(0, 3).get(2).Value, 15);
        assertEquals(graph.DepthFirstSearch(0, 3).get(3).Value, 30);
    }
}