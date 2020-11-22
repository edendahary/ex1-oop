package ex1;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms,Serializable {
    private weighted_graph graph;

    @Override
    public void init(weighted_graph g) {
        this.graph = g;
    }

    @Override
    public weighted_graph getGraph() {
        return this.graph;
    }

    @Override
    public weighted_graph copy() {
        weighted_graph g = new WGraph_DS();
        //copy all the nodes from the graph
        for (node_info curr : this.graph.getV()) {
            g.addNode(new WGraph_DS.NodeInfo(curr.getKey()).getKey());
        }
        // copy all the edges in the graph
        for (node_info curr : graph.getV()) {
            for (node_info curr1 : this.graph.getV(curr.getKey())) {
                g.connect(curr.getKey(), curr1.getKey(), curr1.getTag());
            }
        }
        return g;
    }

    @Override
    public boolean isConnected() {
        if(this.graph.nodeSize() == 0){
            return true;
        }
        if(this.graph.edgeSize()== 0 && this.graph.nodeSize()>2){
            return false;
        }
        for(node_info curr : this.graph.getV()){
            Dijkstra(curr.getKey());
            break;
        }
        for(node_info curr : this.graph.getV()){
            // check if there is a node with max value
            // if so return false.
            if (curr.getTag() == Double.MAX_VALUE){
                return false;
            }
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if(this.graph.getNode(src)==null|| this.graph.getNode(dest)==null)return -1;
        if (src == dest) return 0;
        Dijkstra(src);
        if(this.graph.getNode(dest).getTag()== Double.MAX_VALUE){
            return -1;
        }else{
            return this.graph.getNode(dest).getTag();
        }
    }

    @Override
    public List<node_info> shortestPath(int src, int dest) {
        node_info s = this.graph.getNode(src);
        node_info d = this.graph.getNode(dest);
        if (s == null || d == null || d.getTag() == -1) return null;
        LinkedList<node_info> Path = new LinkedList<>();
        // creating a new list of node_info
        if (src == dest) {
            Path.addFirst(s);
            return Path;
        }
        HashMap<Integer,node_info> ValidPath = Dijkstra(s.getKey());
        // adding to the list the current dest and every time taking
        // the parent of the current node until we get to null(the src node).
        Path.addFirst(graph.getNode(dest));
        node_info curr = ValidPath.get(dest);

        while(curr != null)
        {
            Path.addFirst(graph.getNode(curr.getKey()));
            curr = ValidPath.get(curr.getKey());
        }

        return Path;

    }

    @Override
    public boolean save(String file) {
        //creating and new file and write the graph to the given file
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(getGraph());
            fileOutputStream.close();
            objectOutputStream.close();
            System.out.println("Object has been serialized");
        }catch (IOException e){
            System.out.println("IOException is caught");
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        //load the graph from the given file
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            this.graph= (weighted_graph) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // dijkstra algorithm with PriorityQueue the gives a priority to the lower tag to be first by
    //compare override function
    public HashMap<Integer, node_info> Dijkstra(int src) {
        Queue<node_info> queue = new PriorityQueue<node_info>(new Comparator<node_info>() {
            @Override
            public int compare(node_info o1, node_info o2) {
                // compare if the tag is smaller he will get much more priority
                if (o1.getTag() < o2.getTag()) {
                    return -1;
                } else if (o1.getTag() > o2.getTag()) {
                    return 1;
                }
                return 0;
            }
        });
            HashMap<Integer, node_info> Parent = new HashMap<>();// save all the Parent of all the nodes
            HashMap<Integer, Double> Dis = new HashMap<>(); // save the dis of all the nodes
            node_info s = this.graph.getNode(src);
        for (node_info curr : this.graph.getV()) { // init all the nodes tag and insert to the queue
            curr.setTag(Double.MAX_VALUE);
            Dis.put(curr.getKey(), Double.MAX_VALUE);
            queue.add(curr);
        }

        Dis.replace(s.getKey(), 0.0);
        Parent.put(s.getKey(), null);
        s.setTag(0);
        while (queue.size() != 0) {
            // remove a vertex from queue
            s = queue.poll();
            // Get all adjacent vertices of the dequeued vertex s
            node_info curr = s;
            Iterator<node_info> i = this.graph.getV(s.getKey()).iterator();
            while (i.hasNext()) {
                // running on all the neighbors of the current node and checking
                // if the distance of the node + the edge wight from the node to is neighbor is smaller
                // if so replace the distance and put in the Hashmap Dis
                node_info n = i.next();
                double dis = Dis.get(s.getKey()) + this.graph.getEdge(s.getKey(), n.getKey());
                if(dis<Dis.get(n.getKey())){
                    n.setTag(dis);
                    Dis.put(n.getKey(), dis);
                    Parent.put(n.getKey(), s);
                    queue.add(n);
                }
           }
        }
        // return a Hashmap of all the nodes with there parent
        // note: the given node is parent should be null
            return Parent;
    }
    }

