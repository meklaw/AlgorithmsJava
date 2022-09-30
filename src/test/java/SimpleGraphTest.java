import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Test
    void BreadthFirstSearch() {
        SimpleGraph graph = new SimpleGraph(8);
        ArrayList<Vertex> route;
        for (int i = 0; i < 8; i++) {
            graph.AddVertex(i);
        }
        route = graph.BreadthFirstSearch(0, 5);
        assertEquals(route.size(), 0);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(2, 5);
        graph.AddEdge(2, 6);
        graph.AddEdge(3, 7);
        route = graph.BreadthFirstSearch(0, 5);
        assertEquals(route.get(0).Value, 0);
        assertEquals(route.get(1).Value, 2);
        assertEquals(route.get(2).Value, 5);
        assertEquals(route.size(), 3);

        route = graph.BreadthFirstSearch(0, 7);
        assertEquals(route.get(0).Value, 0);
        assertEquals(route.get(1).Value, 1);
        assertEquals(route.get(2).Value, 3);
        assertEquals(route.get(3).Value, 7);
        assertEquals(route.size(), 4);

        graph.AddEdge(0, 7);
        route = graph.BreadthFirstSearch(0, 7);
        assertEquals(route.get(0).Value, 0);
        assertEquals(route.get(1).Value, 7);
        assertEquals(route.size(), 2);

        route = graph.BreadthFirstSearch(0, 100);
        assertEquals(route.size(), 0);

        graph = new SimpleGraph(7);
        IntStream.range(0, 8).forEach(graph::AddVertex);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(4, 5);

        graph.AddEdge(0, 2);
        graph.AddEdge(2, 6);
        graph.AddEdge(6, 5);
        route = graph.BreadthFirstSearch(0, 5);
        assertEquals(route.get(0).Value, 0);
        assertEquals(route.get(1).Value, 2);
        assertEquals(route.get(2).Value, 6);
        assertEquals(route.get(3).Value, 5);
        assertEquals(route.size(), 4);
    }

    @Test
    void WeakVertices() {
        SimpleGraph graph = new SimpleGraph(9);
        ArrayList<Vertex> triangles;
        IntStream.range(0, 7).forEach(graph::AddVertex);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 2);
        graph.AddEdge(3, 1);
        graph.AddEdge(3, 2);
        graph.AddEdge(2, 5);
        graph.AddEdge(4, 5);
        graph.AddEdge(4, 6);
        graph.AddEdge(5, 6);
        triangles = graph.WeakVertices();
        assertEquals(triangles.size(), 0);

        graph.AddVertex(7);
        graph.AddVertex(8);
        graph.AddEdge(7, 0);
        graph.AddEdge(7, 5);
        graph.AddEdge(8, 6);
        triangles = graph.WeakVertices();
        assertEquals(triangles.size(), 2);
        List<Integer> ints = triangles.stream().map(vertex -> vertex.Value).sorted().collect(Collectors.toList());
        assertEquals(ints.get(0), 7);
        assertEquals(ints.get(1), 8);

    }
}