public class new_test_02 {
    public static void main(String[] args) {
        NodeData a = new NodeData();
        NodeData B = new NodeData();
        NodeData c = new NodeData();
        NodeData d = new NodeData();
        NodeData e = new NodeData();
        NodeData f = new NodeData();
        graph g = new Graph_DS();
        g.addNode(a);
        g.addNode(B);
        g.addNode(c);
        g.addNode(d);
        g.addNode(e);
        g.addNode(f);
        g.connect(a.getKey(),B.getKey());
        g.connect(a.getKey(),c.getKey());
        g.connect(a.getKey(),d.getKey());
        g.connect(c.getKey(),B.getKey());
        g.connect(d.getKey(),B.getKey());
        g.connect(d.getKey(),e.getKey());
        g.connect(e.getKey(),f.getKey());
        g.connect(a.getKey(),f.getKey());
        g.connect(c.getKey(),f.getKey());

        g.removeNode(f.getKey());
        System.out.println(g.edgeSize());
        System.out.println(g.nodeSize());
    }



}
