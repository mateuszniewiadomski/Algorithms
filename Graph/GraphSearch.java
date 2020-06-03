import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GraphSearch {

    private static int size;
    int[][] matrix;
    private static ArrayList<Node> V = new ArrayList<Node>();
    private ArrayList<String> Q = new ArrayList<String>();
    private Queue<Node> queue = new LinkedList<Node>();

    public void readFile() {
        try {
            File file = new File("src/input");
            Scanner scanner = new Scanner(file);
            size = scanner.nextInt();
            matrix = new int[size][size];
            int j = 0;
            while (scanner.hasNext()) {
                for (int i = 0; i < size; i++) {
                    matrix[j][i] = scanner.nextInt();
                }
                j++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    static class Node {
        int nr;
        boolean visited = false;
        Node(int nr) {
            this.nr = nr;
        }
    }

    public void DFS(Node node) {
        System.out.print(node.nr + " ");
        ArrayList<Node> Adj = Adj(node);
        node.visited=true;
        for (int i = 0; i < Adj.size(); i++) {
            Node n=Adj.get(i);
            if(n!=null && !n.visited) {
                Q.add("{"+node.nr+":"+n.nr+"}");
                DFS(n);
            }
        }
    }

    public ArrayList<Node> Adj(Node x) {
        int nodeIndex=-1;

        ArrayList<Node> Adj = new ArrayList<>();
        for (int i = 0; i < V.size(); i++) {
            if(V.get(i).equals(x)) {
                nodeIndex=i;
                break;
            }
        }

        if(nodeIndex!=-1) {
            for (int j = 0; j < matrix[nodeIndex].length; j++) {
                if (matrix[nodeIndex][j]==1) {
                    Adj.add(V.get(j));
                }
            }
        }
        return Adj;
    }

    public void BFS(Node node) {
        queue.add(node);
        node.visited=true;
        while (!queue.isEmpty()) {
            Node element=queue.remove();
            System.out.print(element.nr + " ");
            ArrayList<Node> Adj = Adj(element);
            for (int i = 0; i < Adj.size(); i++) {
                Node n = Adj.get(i);
                if(n!=null && !n.visited) {
                    queue.add(n);
                    Q.add("{"+element.nr+":"+n.nr+"}");
                    n.visited=true;
                }
            }
        }
    }

    public void printQ(){
        System.out.println();
        System.out.print("Q: ");
        for(String s : Q) {
            System.out.print(s);
        }
    }

    public static void main(String[] args) {
        GraphSearch gs = new GraphSearch();
        gs.readFile();
        for(int i=0;i<size;i++){
            Node node = new Node(i);
            V.add(node);
        }
/*
    wybrać DFS lub BFS
*/

        //System.out.print("DFS:\nV: ");
        //gs.DFS(V.get(0));

        System.out.print("BFS:\nV: ");
        gs.BFS(V.get(0));

        gs.printQ();
    }
}
/*
    testowane na grafie z wykładu (na zdjęciu)
*/