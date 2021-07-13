import java.util.Collection;
import java.util.HashMap;

public class NodeData implements node_data
{
    private int Key; // Key of node
    private HashMap<Integer,node_data> Node_Neighbors;//Collection of the node neighbors.
    private String Information; // Information about the node
    private int Tag; //Temporal data which can be used by algorithms
    private static int node_key_counter=0; //Determine the node key(id), so each node_data will have a unique key.

    /**constructor of the node*/
 public NodeData()
 {
     this.Key = node_key_counter;
     node_key_counter++;
     Node_Neighbors = new HashMap<Integer, node_data>();
     this.Information = null;
     this.Tag = -1;
 }
    /**constructor of the node, use the key that the function get.*/
public NodeData(node_data node_copy)
{
    this.Key = node_copy.getKey();
    Node_Neighbors = new HashMap<Integer, node_data>();
    this.Information =node_copy.getInfo() ;
    this.Tag = node_copy.getTag();
}
    /**
     * Return the key (id) associated with te node node.
     * @return*/
    @Override
    public int getKey() {
        return this.Key;
    }
    /**
     * This method returns a collection with all the Neighbor nodes of this node_data
     * @return */
    @Override
    public Collection<node_data> getNi() {
       return Node_Neighbors.values();

    }
    /**
     * return true iff this<==>key are adjacent, as an edge between them.
     * @param key
     * @return
     */
    @Override
    public boolean hasNi(int key) {
        return Node_Neighbors.containsKey(key);
    }
    /** This method adds the node_data (t) to this node_data.
     * @param t */
    @Override
    public void addNi(node_data t) {
    Node_Neighbors.put(t.getKey(),t);
    }
    /**
     * Removes the edge this-key,
     * @param node */
    @Override
    public void removeNode(node_data node) {
        Node_Neighbors.remove(node.getKey());
    }
    /**
     * return the string information associated with this node.
     * @return */
    @Override
    public String getInfo() {
        return this.Information;
    }
    /**
     * Allows changing the information associated with this node.
     * @param s */
    @Override
    public void setInfo(String s) {
this.Information = s;
    }
    /**
     * return the tag data
     * @return  */
    @Override
    public int getTag() {
        return Tag;
    }
    /**
     * set the tag data
     * @param t */
    @Override
    public void setTag(int t) {
this.Tag= t;
    }
    /**
     * return a string general information about the node
     * @return */
    public String toString ()
    {
        String node_Structure ="";
        node_Structure = "|node key: " + getKey() + "| node neighbors: [";
        // go over the node neighbors and add their information to the string
        for (node_data current_node: getNi()) {
            node_Structure= node_Structure + current_node.getKey()+",";
        }
        node_Structure = node_Structure + "]\n";
        return node_Structure;
    }
}
