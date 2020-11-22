package ex1;


import java.io.Serializable;
import java.util.*;

public class WGraph_DS implements weighted_graph, Serializable {
    private HashMap<Integer,node_info> MapNode;
    private HashMap<Integer,HashMap<Integer,Double>>Edges;
    private int MC=0;
    private int ES=0;
    public WGraph_DS(){
        MapNode = new HashMap<>();
        Edges = new HashMap<>();
    }
    @Override
    public node_info getNode(int key) {
        if (this.MapNode.containsKey(key)) {
            return this.MapNode.get(key);
        }
        return null;
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        if(this.Edges.get(node1)== null){
            return false;
        }
        return this.Edges.get(node1).containsKey(node2);
    }

    @Override
    public double getEdge(int node1, int node2) {
        if(this.Edges.containsKey(node1) && this.Edges.containsKey(node2)) {
            if (this.Edges.get(node1).containsKey(node2)) {
                return this.Edges.get(node1).get(node2);
            }
            return -1;
        }
        return -1;
    }

    @Override
    public void addNode(int key) {
        if(!this.MapNode.containsKey(key)) {
            this.MapNode.put(key, new NodeInfo(key));
            MC++;
        }
    }

    @Override
    public void connect(int node1, int node2, double w) {
        //connect between the two given nodes and if the already created just update the wight
        if(this.MapNode.containsKey(node1) && this.MapNode.containsKey(node2) && node1!= node2 && w>=0) {
            if(this.Edges.containsKey(node1)) {
                if(this.Edges.get(node1).containsKey(node2)){
                    this.Edges.get(node1).replace(node2,w);
                    this.Edges.get(node2).replace(node1,w);
                    MC--;
                    ES--;
                }else {
                    this.Edges.get(node1).put(node2, w);
                    if(!this.Edges.containsKey(node2)){
                        HashMap<Integer,Double> t1 = new HashMap<>();
                        t1.put(node1,w);
                        this.Edges.put(node2,t1);
                    }
                    else {
                        this.Edges.get(node2).put(node1,w);
                    }
                }
            }
            else {
                HashMap<Integer,Double> t = new HashMap<>();
                t.put(node2,w);
                this.Edges.put(node1,t);
                if(!this.Edges.containsKey(node2)){
                    HashMap<Integer,Double> t1 = new HashMap<>();
                    t1.put(node1,w);
                    this.Edges.put(node2,t1);
                }else {
                    this.Edges.get(node2).put(node1,w);
                }
            }
            ES++;
            MC++;
        }
    }

    @Override
    public Collection<node_info> getV() {
        return this.MapNode.values();
    }

    @Override
    public Collection<node_info> getV(int node_id) {
        List<node_info>ni= new ArrayList<>();
        if(this.MapNode.get(node_id)== null || this.Edges.size() == 0 || this.Edges.get(node_id) == null){
        return ni;
        }
        for(int i : this.Edges.get(node_id).keySet()){
            ni.add(getNode(i));
        }
        return ni;
    }

    @Override
    public node_info removeNode(int key) {
        //remove the node from the MapNode and running on all is neighbors and delete the edge
        node_info curr =this.MapNode.get(key);
        this.MapNode.remove(key);
        MC++;
        if(this.Edges.get(key) == null){
            return null;
        }
        for(int i : this.Edges.get(key).keySet()){
            this.Edges.get(i).remove(key);
            ES--;

        }
        this.Edges.remove(key);
        return curr;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        //delete the edge from the two given nodes
        if(this.Edges.containsKey(node1) && this.Edges.containsKey(node2)){
            this.Edges.get(node1).remove(node2);
            this.Edges.get(node2).remove(node1);
            ES--;
            MC++;
        }
        else if(this.Edges.containsKey(node1)){
            this.Edges.get(node1).remove(node2);
            ES--;
            MC++;
        }
        else if(this.Edges.containsKey(node2)){
            this.Edges.get(node2).remove(node1);
            ES--;
            MC++;
        }
    }

    @Override
    public int nodeSize() {
        return this.MapNode.size();
    }

    @Override
    public int edgeSize() {
        return ES;
    }

    @Override
    public int getMC() {
        return this.MC;
    }
    public static class NodeInfo implements node_info,Serializable{
        private static int k=0;
        private double Tag;
        private int key;
        private String Info ;

        // create a new node with a unique key
        public NodeInfo(){
            this.key=k++;
        }
        public NodeInfo(int k){
            this.key=k;
        }
        @Override
        public int getKey() {
            return this.key;
        }

        @Override
        public String getInfo() {
            return this.Info;
        }

        @Override
        public void setInfo(String s) {
            this.Info=s;
        }

        @Override
        public double getTag() {
            return this.Tag;
        }

        @Override
        public void setTag(double t) {
            this.Tag=t;
        }
    }
}
