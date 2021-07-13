
import java.util.*;


public class Graph_Algo implements graph_algorithms {

     private graph graph1 ;// a graph that we use for the algorithms

     public Graph_Algo()
    {
        graph1 = new Graph_DS();

    }
    public Graph_Algo(graph g)
    {
        this.graph1 = g;
    }
    /**Init the graph on which this set of algorithms operates on.
     * @param g
     */
    @Override
    public void init(graph g) {
      graph1 = g;
    }
    /** Compute a deep copy of this graph.
     * the function go over the original graph vertices and add them to the new graph.
     * its also add to the new graph the vertex neighbors ,and crate a path between them and the vertex.
     * if in any case one of the vertices are already in the new graph the function don't add it again
     * to the new graph, but add its neighbors.
     * if the neighbor of the vertex its already in the new graph the function don't add it
     * again but just create a path between them , if the path already existent it will not been added again
     * @return graph_new - return the new copy of the graph original graph */
    @Override
    public graph copy() {
        graph graph_new;
        node_data node_temp1, node_temp2;
        if(graph1==null)
            return graph_new=null;
        graph_new=new Graph_DS(graph1);
        // go over the graph vertices
        for (node_data current_node : graph1.getV()) {
            // if the new graph don't contain the node add it
            if (graph_new.getNode(current_node.getKey()) == null) {
                node_temp1 = new NodeData(current_node);
                graph_new.addNode(node_temp1);
            }
            if (current_node.getNi().size() > 0)
            {
                // go over the vertex neighbors
        for (node_data node_ni : current_node.getNi()) {
            // if the new graph don't contain the node add it
            if (graph_new.getNode(node_ni.getKey()) == null) {
                node_temp2 = new NodeData(current_node);
                graph_new.addNode(node_temp2);
            }
            // connect between the node and its neighbors in the new graph
            graph_new.connect(current_node.getKey(), node_ni.getKey());
        }
    }
        }
        return graph_new;
    }

    /** the function get one of the graph nodes and activates a inner function
     * the inner function set the tag in each node in the graph , to be the distance in number of edges,
     * from the start node in the inner function.
     * if the inner function didn't found any path to a specific node from the start node ,
     * this specific node tag will stay -1
     * than we go over the graph nodes to check if their any node with -1 in his tag
     * if their is return false else return true
     * @return - Returns true if and only if there is a valid path from EVERY node to each
     * other node in the unidirectional graph.
     *  if the graph have only 0 or 1 nodes the function return true */
    @Override
    public boolean isConnected() {
    node_data node_temp;
    // if the graph have 1 or 0 node is connected
    if(graph1.nodeSize()==0||graph1.nodeSize()==1)
        return true;
    // get a node from the graph
    Iterator<node_data> first = this.graph1.getV().iterator();
    node_temp = first.next();
    // activates ann inner function to find the distance from the node_temp
     distance_from(node_temp.getKey());
     // go over the graph nodes and if one node tag = -1 than graph not connected
     if(node_temp.getNi().size()>0) {
         for (node_data current_node : graph1.getV()) {
             if (current_node.getTag() == -1)
                 return false;
         }
     }
     else
         return false;
        return true;
    }
    /**
     * an inner function that get a node key from the graph
     * sets every node tag to contain the distance between the source node and a specific node
     * first sets every node tag to -1 and information to white
     * add the nodes to a queue and operate by the information color
     * white = not been dealt with, grey = been added to the list to be handle , black = finish
     * its start from a source node and add his neighbors tag,to be parent tag +1,
     * and add them to the queue ,after finish with his neighbors remove the node from queue
     * do the same to their neighbors until the queue is empty
     * @param src- node key
     */
    private void distance_from (int src)
    {
        node_data temp_node;
        // operate ass a queue
        LinkedList<node_data> list_of_nodes = new LinkedList<node_data>();
        // go over the graph nodes and set their information and tag
        for (node_data current_node : graph1.getV()) {
            current_node.setInfo("white");
            current_node.setTag(-1);
        }
        // set first node and add it to queue
        graph1.getNode(src).setInfo("grey");
        graph1.getNode(src).setTag(0);
        list_of_nodes.add(graph1.getNode(src));
        // go over the nodes added to the queue until all been handled
        while(!list_of_nodes.isEmpty())
        {
            temp_node = list_of_nodes.getFirst();
            list_of_nodes.remove(list_of_nodes.getFirst());
            if (temp_node.getNi().size()>0) {
                // if the specific node have neighbors go over them
                for (node_data current_node : temp_node.getNi()) {
                    // if node tag not been changed set it and add the node to the queue
                    if (current_node.getInfo().equals("white")) {
                        current_node.setInfo("grey");
                        current_node.setTag(temp_node.getTag() + 1);
                        list_of_nodes.add(current_node);
                    }
                }
            }
            // finish with the node set to black
            temp_node.setInfo("black");
        }
    }
    /**
     * the function calls an inner function that go from src node to dest node
     * sets every node tag in the path to the distance ,if the inner function finds the dest node its stops
     * the tag of the node neighbors sets to be node tag + 1
     * the function returns the dest tag that contain the shortest distance from src
     * to get the shortest path the function make sure to go over from node to neighbors
     * and not to set a node tag that already been set
     * @param src - start node
     * @param dest - end (target) node
     * @return returns the length (by edges) of the shortest path between vertices src to dest
     * (if no path possible return -1)
     */
    @Override
    public int shortestPathDist(int src, int dest) {
    if(graph1 == null)
        return -1;
    if(graph1.nodeSize()==0)
        return -1;
    // if one of the vertices not in the graph return -1
        if(graph1.getNode(src)==null||graph1.getNode(dest)== null)
            return -1;
        short_path(src,dest);
        // return the dest vertex shortest path from src
        return graph1.getNode(dest).getTag();
    }
    /** its an inner function that gets an start node key and dest
     * go from the start node and sets his neighbors tags to be start tag +1
     * and go over their neighbors and sets their tag the same,
     * stops if sets all the nodes tag in the path or reach the dest node
     * in every set of node tag , the node key and his previous are inserted to a collection
     * @param src - start node
     * @param dest - end (target) node
     * @return node_map - a collection that contain all the nodes key in the path and theirs previous
     */
private HashMap<Integer,Integer> short_path (int src,int dest)
{    HashMap<Integer,Integer> node_map = new HashMap<Integer,Integer>();
    node_data temp_node;
    // a list that function as a queue
    LinkedList<node_data> list_of_nodes = new LinkedList<node_data>();
    // if the dest and src are equal return just one node in the collection
    if(src==dest)
    { graph1.getNode(src).setTag(0);
    node_map.put(src,-1);
    return node_map;}
    // rest every node node in the graph , tag to -1 and information to white
    for (node_data current_node : graph1.getV()) {
       current_node.setInfo("white");
       current_node.setTag(-1);
    }// sets the src node and insert it to the queue and the collection
    graph1.getNode(src).setInfo("grey");
    graph1.getNode(src).setTag(0);
    list_of_nodes.add(graph1.getNode(src));
    node_map.put(src,-1);
    // run until sets every node in the path
    while(!list_of_nodes.isEmpty())
    {
        // get the first node in queue
        temp_node = list_of_nodes.getFirst();
        list_of_nodes.remove(list_of_nodes.getFirst());
        // check if the node have neighbors if it had go over them and sets them
        if(temp_node.getNi().size()>0) {
            // go over the node neighbors
            for (node_data current_node : temp_node.getNi()) {
                // if not been altered, sets his info to grey and his tag to previous tag +1
                if (current_node.getInfo().equals("white")) {
                    current_node.setInfo("grey");
                    current_node.setTag(temp_node.getTag() + 1);
                    // insert the node to the collection
                    node_map.put(current_node.getKey(), temp_node.getKey());
                    // if its reach the dest node stop and return the collection
                    if (current_node.equals(graph1.getNode(dest))) {
                        return node_map;
                    }
                    // add the node to the queue
                    list_of_nodes.add(current_node);
                }
            }
        }
        temp_node.setInfo("black");
    }
    return node_map;
}
    /** the function activates an inner function that returns a collection that contain
     * all the nodes in the path between vertices src to dest,
     * its hold the key of an vertex and the key of his previous in the path
     * also the inner function sets every node tag in the path to the distance from src vertex by edges
     * the function use the collection to create a list of vertices in the path from src to dest
     * @param src - start node
     * @param dest - end (target) node
     * @return path_list2 - returns the the shortest path between src to dest - as an ordered List of nodes:
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        int key_temp;
         LinkedList<node_data> path_list1,path_list2;
        path_list1=new LinkedList<node_data>();
        path_list2=new LinkedList<node_data>();
        HashMap<Integer,Integer> node_map;
        // if graph null or empty return empty list
        if(graph1==null)
            return path_list2;
        if(graph1.nodeSize()==0)
            return path_list2;
        // if one of the vertex not in the graph return empty list
        if(graph1.getNode(src)==null||graph1.getNode(dest)== null)
            return path_list2;
        // activates inner function to set tags and create a collection
        node_map = short_path(src, dest);
        key_temp = dest;
        // if its a path between the vertices create a list
        if(graph1.getNode(dest).getTag()!=-1) {
            // create a list by the collection the list will be from dest to src
            while (node_map.get(key_temp) != -1) {
                path_list1.add(graph1.getNode(key_temp));
                key_temp = node_map.get(key_temp);
            }
            path_list1.add(graph1.getNode(key_temp));
            // reversed the list so it will be from src to dest
            while (!path_list1.isEmpty()) {
                path_list2.add(path_list1.getLast());
                path_list1.remove(path_list1.getLast());
            }
        }
        return path_list2;
    }
}
