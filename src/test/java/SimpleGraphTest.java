import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
        graph.AddEdge(10, 10);
        graph.AddEdge(10, 15);
        graph.AddEdge(10, 20);
        graph.AddEdge(15, 15);
        graph.AddEdge(15, 20);
        graph.AddEdge(20, 20);

        graph.RemoveVertex(10);


        for (int i = 0; i < graph.m_adjacency.length; i++) {
                assertEquals(graph.m_adjacency[0][i], 0);
                assertEquals(graph.m_adjacency[i][0], 0);
        }

        for (int i = 0; i < graph.m_adjacency.length; i++) {
            System.out.println(Arrays.toString(graph.m_adjacency[i]));
        }

    }

    @Test
    void isEdge() {
        graph.AddEdge(10, 20);
        graph.AddEdge(10, 10);
        graph.AddEdge(20, 15);
        assertTrue(graph.IsEdge(10, 20));
        assertTrue(graph.IsEdge(20, 10));
        assertTrue(graph.IsEdge(10, 10));
        assertTrue(graph.IsEdge(20, 15));
        assertTrue(graph.IsEdge(15, 20));
        assertFalse(graph.IsEdge(10, 15));

    }

    @Test
    void addEdge() {
        graph.AddEdge(10, 100);
        graph.AddEdge(10, 20);
        assertEquals(graph.m_adjacency[0][2], 1);
        assertEquals(graph.m_adjacency[2][0], 1);
        graph.AddEdge(10, 10);
        assertEquals(graph.m_adjacency[0][0], 1);
    }

    @Test
    void removeEdge() {
        graph.AddEdge(10, 10);
        graph.AddEdge(10, 15);
        graph.AddEdge(10, 20);
        assertTrue(graph.IsEdge(10, 10));
        assertTrue(graph.IsEdge(10, 15));
        assertTrue(graph.IsEdge(10, 20));
        graph.RemoveEdge(15, 10);
        assertTrue(graph.IsEdge(10, 10));
        assertFalse(graph.IsEdge(10, 15));
        assertFalse(graph.IsEdge(15, 10));
        assertTrue(graph.IsEdge(10, 20));
    }
}