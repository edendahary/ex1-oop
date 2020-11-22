# ex1-oop

### This is an object oriented programmin project which his main idea is based on functions.

### This Project Made by Eden Dahary Student in Ariel University.

### This project contains a undirectional unweighted graph with vertexs and edges.

### in this project we implements data structure, algorithms.

## WGraph_DS Class
This Class represents the set of operations applicable on a node (vertex) in a undirectional weighted graph.

## This Class contains serval methods:

### NodeInfo class
a inner class in the WGraph_DS that represents a node with string key and a tag.

### node_info getNode(int key)
retrun the currrent node if he exists if not return null.

### boolean hasEdge(int node1, int node2)
checking if there is an edge between the given nodes and if there is return true else false.

### double getEdge(int node1, int node2)
return the wight of the edge if the edge exists else return -1;

### void addNode(int key)
add a new node to the graph.

### void connect(int node1, int node2, double w)
creating a new edge with wight if the edge already exists just update the wight

### getV()
return all the nodes in the graph

### getV(int node_id)
return all the neighbors of the cuurent node.

### node_info removeNode(int key)
remove the given node from the graph and delete all the connected edges with him.

### void removeEdge(int node1, int node2)
remove the edge from the graph.

### int nodeSize()
return the amount of the nodes in the graph.

### int edgeSize()
return the amount of the edges in the graph.

## WGraph_Algo Class
This Class represents the "regular" Graph Theory algorithms.
such as:
dijkstra algorithm that given us the shortest path and the dist from the given nodes.

checking if the graph is conncted that from every node i can get to every other node.

## Methods:

### boolean isConnected()
check if every there is a valid path from every node to each other.

### int shortestPathDist(int src, int dest)
give the smallest wight of the shortest path between src to dest.

### List<node_info> shortestPath(int src, int dest)
give the the shortest path between src to dest - as an ordered List of nodes.
