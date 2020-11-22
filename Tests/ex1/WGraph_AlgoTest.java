package ex1;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {
    private static Random _rnd = null;

    private weighted_graph g= new WGraph_DS();

    @Test
    void copy() {
        g= graph_creator(10,20,2);
        weighted_graph_algorithms g1 = new WGraph_Algo();
        g1.init(g);
        weighted_graph_algorithms g2 = new WGraph_Algo();
        g2.init(g1.copy());
        assertEquals(g1.getGraph().nodeSize(),g2.getGraph().nodeSize(),"Should be equals");
        assertEquals(g1.getGraph().edgeSize(),g2.getGraph().edgeSize(),"Should be equals");
        g1.getGraph().removeNode(1);
        assertNotEquals(g1.getGraph().nodeSize(),g2.getGraph().nodeSize(),"Should not be equals");

    }
    @Test
  void isConnected() {
        g= graph_creator(9,18,2);
        weighted_graph_algorithms g1 = new WGraph_Algo();
        g1.init(g);
        assertTrue(g1.isConnected(),"The graph should be connected");
        g1.getGraph().removeNode(1);
        assertTrue(g1.isConnected(),"The graph should be connected");
        g1.getGraph().removeNode(0);
        assertFalse(g1.isConnected(),"The graph should be disconnected");
        g1.getGraph().removeNode(4);
        assertTrue(g1.isConnected(),"The graph should be connected");
        weighted_graph graph = new WGraph_DS();
        weighted_graph_algorithms EmptyG = new WGraph_Algo();
        EmptyG.init(graph);
        assertTrue(EmptyG.isConnected(),"Empty graph is connected");

    }
    @Test
    void shortestPathDist() {
        g= graph_creator(5,10,2);
        weighted_graph_algorithms graphAlgorithms = new WGraph_Algo();
        graphAlgorithms.init(g);
        double dis =graphAlgorithms.shortestPathDist(0,3);
        assertEquals(6.0,dis,"Should be equals");
        weighted_graph g1 = new WGraph_DS();
        g1=graph_creator(1000,5000,2);
        weighted_graph_algorithms graphAlgorithms1 = new WGraph_Algo();
        graphAlgorithms1.init(g1);
        dis = graphAlgorithms1.shortestPathDist(0,15);
        assertEquals(-1,dis,"Should be equals");
        dis = graphAlgorithms1.shortestPathDist(5,20);
        assertEquals(4,dis,"Should be equals");
    }
    @Test
   void shortestPath() {
        g= graph_creator(5,6,2);
        weighted_graph_algorithms graphAlgorithms  = new WGraph_Algo();
        graphAlgorithms.init(g);
        String s="";
        for(node_info curr:graphAlgorithms.shortestPath(0,1) ){
             s+=curr.getKey() +"->";
        }
        assertEquals("0->4->1->",s,"Should be equals");
    }
    @Test
    void File() {
        g = graph_creator(5, 6, 2);
        weighted_graph_algorithms graphAlgorithms  = new WGraph_Algo();
        graphAlgorithms.init(g);
        graphAlgorithms.save("test");
        weighted_graph_algorithms loadga  = new WGraph_Algo();
        loadga.load("test");
        loadga.getGraph().removeNode(0);
        assertNotEquals(loadga,graphAlgorithms,"the node should only deleted on the loadga graph");

    }
    public static weighted_graph graph_creator(int v_size, int e_size, int seed) {
        weighted_graph g = new WGraph_DS();
        _rnd = new Random(seed);
        for(int i=0;i<v_size;i++) {
            node_info n = new WGraph_DS.NodeInfo();
            g.addNode(n.getKey());
        }
        int[] nodes = nodes(g);
        while(g.edgeSize() < e_size) {
            int a = nextRnd(0,v_size);
            int b = nextRnd(0,v_size);
            int c = _rnd.nextInt(10);
            int i = nodes[a];
            int j = nodes[b];
            g.connect(i,j,c);
        }
        return g;
    }
    private static int[] nodes(weighted_graph g) {
        int size = g.nodeSize();
        Collection<node_info> V = g.getV();
        node_info[] nodes = new node_info[size];
        V.toArray(nodes); // O(n) operation
        int[] ans = new int[size];
        for(int i=0;i<size;i++) {ans[i] = nodes[i].getKey();}
        Arrays.sort(ans);
        return ans;
    }
    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    private static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }



}
