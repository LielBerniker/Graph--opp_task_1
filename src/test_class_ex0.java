public class test_class_ex0 {
    public static void main(String[] args) {
        node_data node1 ,node2,node3;
        Graph_DS graph1 = new Graph_DS();
        node1 = new NodeData();
        node2 = new NodeData();
        node3 = new NodeData();
        graph1.addNode(node2);
        graph1.addNode(node1);
        graph1.addNode(node3);
        graph1.connect(0,1);
        graph1.connect(0,2);
        graph1.connect(0,1);
        graph1.connect(1,2);
        System.out.println( graph1.edgeSize());
        System.out.println(graph1.nodeSize());
        graph1.removeNode(0);
        System.out.println("remove node 0");
        System.out.println( graph1.edgeSize());
        System.out.println(graph1.nodeSize());


    }
}
