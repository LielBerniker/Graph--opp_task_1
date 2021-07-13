public class test_test {
    public static void main(String[] args) {
        Graph_DS g = new Graph_DS();
        NodeData temp = new NodeData();

        for (int i=0; i<1000000;i++){
            temp = new NodeData();
            g.addNode(temp);
        }
        for (int i=1,j=999990; i<490000;i++,j--) {
            g.connect(i,j);
            g.connect(i+1,j-2);

        }
        System.out.println(g.edgeSize());



    }
}
