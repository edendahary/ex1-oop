package ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {

    weighted_graph g = new WGraph_DS();
    @Test
    void getNode() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        int y =g.getNode(0).getKey();
        assertEquals(0,y);
    }

    @Test
    void hasEdge() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        assertEquals(false,g.hasEdge(1,2));
        g.connect(1,2,0.5);
        assertEquals(true,g.hasEdge(1,2));

    }

    @Test
    void getEdge() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.connect(0,1,8);
        g.connect(0,2,10);
        assertEquals(8,g.getEdge(0,1));
        assertEquals(-1,g.getEdge(1,2));

    }

    @Test
    void addNode() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        assertEquals(g.nodeSize(),3);
    }

    @Test
    void connect() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.connect(0,1,9);
        g.connect(1,2,9);
        g.connect(0,2,9);
        g.connect(0,7,9);
        assertEquals(true,g.hasEdge(0,1));
        assertEquals(true,g.hasEdge(1,2));
        assertEquals(true,g.hasEdge(0,2));
        assertEquals(false,g.hasEdge(0,7));

    }

    @Test
    void getV() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.connect(0,1,9);
        g.connect(1,2,9);
        g.connect(0,2,9);
        g.connect(0,7,9);
        int x =g.getV().size();
        assertEquals(3,x);
    }

    @Test
    void testGetV() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.connect(0,1,9);
        g.connect(1,2,9);
        g.connect(0,2,9);
        g.connect(0,7,9);
        int x =g.getV(0).size();
        assertEquals(2,x);

    }

    @Test
    void removeNode() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.connect(0,1,9);
        g.connect(1,2,9);
        g.connect(0,2,9);
        g.connect(0,7,9);
        assertEquals(3,g.nodeSize());
        g.removeNode(0);
        assertEquals(2,g.nodeSize());
    }

    @Test
    void removeEdge() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.connect(0,1,9);
        g.connect(1,2,9);
        g.connect(0,2,9);
        g.connect(0,7,9);
        boolean t = g.hasEdge(0,1);
        assertEquals(true,t);
        g.removeEdge(0,1);
        boolean t1 = g.hasEdge(0,1);
        assertEquals(false,t1);
    }

    @Test
    void nodeSize() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.connect(0,1,9);
        g.connect(1,2,9);
        g.connect(0,2,9);
        g.connect(0,7,9);
        assertEquals(3,g.nodeSize());
    }

    @Test
    void edgeSize() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.connect(0,1,9);
        g.connect(1,2,9);
        g.connect(0,2,9);
        g.connect(0,7,9);
        assertEquals(3,g.edgeSize());
    }

    @Test
    void getMC() {
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.connect(0,1,9);
        g.connect(1,2,9);
        g.connect(0,2,9);
        g.connect(0,7,9);
        assertEquals(6,g.getMC());
    }
}