import java.util.*;

public class Graph_DS implements graph {
    private HashMap<Integer,node_data> nodes_of_graph; // collection of the graph vertices
    private int edges_number; // number of edges in the graph
    private int MC; // number of changes in the graph (remove node or edge, add node or edge)
    /** constructor of the graph */
    public Graph_DS()
    {
        this.nodes_of_graph = new HashMap<Integer, node_data>();
        this.edges_number=0;
        this.MC= 0;
    }
    /**  constructor of the graph, copy the mc
     * @param graph_const - graph */
    public Graph_DS(graph graph_const)
    {
        this.nodes_of_graph = new HashMap<Integer, node_data>();
        this.edges_number=0;
        this.MC= graph_const.getMC()-graph_const.nodeSize()-graph_const.edgeSize();
    }
    /** return the node_data by the node_id,null if none
     * @param key - the node_id
     * @return */
    public node_data getNode(int key) {
        if(nodes_of_graph.containsKey(key))
        return nodes_of_graph.get(key);
        else
            return null;
    }
    /** return true iff there is an edge between node1 and node2
     * @param node1
     * @param node2
     * @return */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(!nodes_of_graph.containsKey(node1)||!nodes_of_graph.containsKey(node2))
            return false;
       if(node1==node2)
           return false;
       return (getNode(node1).hasNi(node2)&&getNode(node2).hasNi(node1));
   }
    /** add a new node to the graph with the given node_data.
     * @param n */
    @Override
    public void addNode(node_data n) {
        //if node not in graph , add it
        if(!nodes_of_graph.containsKey(n.getKey()))
        {nodes_of_graph.put(n.getKey(),n);
          MC++;   }
    }
    /** Connect an edge between node1 and node2, if  already exists does nothing
     * @param node1
     * @param node2 */
    @Override
    public void connect(int node1, int node2) {
        // if equal does nothing
        if(node1!=node2) {
            if (!nodes_of_graph.containsKey(node1) || !nodes_of_graph.containsKey(node2))
                return;
            else {
                // if don't have an edge add it
                if (!getNode(node1).hasNi(node2) && !getNode(node2).hasNi(node1)) {
                    getNode(node1).addNi(getNode(node2));
                    getNode(node2).addNi(getNode(node1));
                    edges_number++;
                    MC++;
                }
            }
        }
    }
    /** return a collection with all the nodes in the graph.
     * @return Collection<node_data> */
    @Override
    public Collection<node_data> getV() {
        return nodes_of_graph.values();
    }
    /**
     * This method return a collection of  the
     * collection representing all the nodes connected to node_id
     * @return Collection<node_data> */
    @Override
    public Collection<node_data> getV(int node_id) {
        return (getNode(node_id).getNi());
    }
    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * @return the data of the removed node (null if none).
     * @param key */
    @Override
    public node_data removeNode(int key) {
        // if node not in graph return null
        if (nodes_of_graph.containsKey(key)) {
            // only if the node have neighbors go over neighbors and remove
            if(getV(key).size()>0) {
                // go over node connected vertices
                for (node_data current_node : nodes_of_graph.get(key).getNi()) {
                    current_node.removeNode(nodes_of_graph.get(key));
                    edges_number--;
                    MC++;
                }
            }
            this.MC++;
                return nodes_of_graph.remove(key);
        }
        else
            return null;
    }
    /**
     * Delete the edge from the graph,
     * @param node1
     * @param node2 */
    @Override
    public void removeEdge(int node1, int node2) {
//  delete only if the two nodes are in the graph
        if (nodes_of_graph.containsKey(node1) && nodes_of_graph.containsKey(node2))
           {if(hasEdge(node1,node2)) {
               getNode(node1).removeNode(getNode(node2));
               getNode(node2).removeNode(getNode(node1));
               edges_number--;
               this.MC++;
           }
           }
    }
    /** return the number of vertices (nodes) in the graph.
     * @return */
    @Override
    public int nodeSize() {
        return this.nodes_of_graph.size();
    }
    /** return the number of edges (unidirectional graph).
     * @return */
    @Override
    public int edgeSize() {
        return this.edges_number;
    }
    /** return the Mode Count - for testing changes in the graph.
     * @return */
    @Override
    public int getMC() {
        return this.MC;
    }
    /** return a string general information about the graph
     * contains node and edge number, and all the node general information
     * @return */
    @Override
    public String toString()
    { String graph_Structure;
        graph_Structure =  ("Graph: \nnumber of vertex: " + nodes_of_graph.size() + ", number of edges:  " + edgeSize())+
                "\nnumber of changes in graph: " + getMC() + "\n";
        for (node_data current_node:getV()) {
            graph_Structure = graph_Structure +current_node.toString();
        }
        return  graph_Structure;
    }
}
