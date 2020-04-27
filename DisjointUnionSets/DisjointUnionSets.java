import java.util.*;

public class DisjointUnionSets {

    List<Node> set;

    private class Node {
        private Node parent;
        private int key;
        private int rank;

        private Node(int key, int rank) {
            this.parent = this;
            this.key = key;
            this.rank = rank;
        }
    }

    private DisjointUnionSets() {
        this.set = new ArrayList();
    }

    private void MakeSet(int key) {
        this.set.add(new Node(key, 0));
    }

    private static Node FindSet(Node x) {
        if (x != x.parent) {
            x.parent = FindSet(x.parent);
        }
        return x.parent;
    }

    private static void Union(Node x, Node y) {
        if (x.rank < y.rank) {
            x.parent = y;
        } else {
            y.parent = x;
            if (x.rank == y.rank) {
                y.rank++;
            }
        }
    }

    private static void PathToRoot(Node x) {
        System.out.print(x.key);
        if (x != x.parent) {
            System.out.print(" -> ");
            PathToRoot(x.parent);
        }
    }

    public static void main(String[] args) {

        DisjointUnionSets Z = new DisjointUnionSets();

        for (int i = 0; i < 10; i++) {
            Z.MakeSet(i);
        }

        Union(FindSet(Z.set.get(0)), FindSet(Z.set.get(1)));
        Union(FindSet(Z.set.get(2)), FindSet(Z.set.get(3)));
        Union(FindSet(Z.set.get(1)), FindSet(Z.set.get(2)));
        Union(FindSet(Z.set.get(5)), FindSet(Z.set.get(6)));
        Union(FindSet(Z.set.get(7)), FindSet(Z.set.get(8)));
        Union(FindSet(Z.set.get(3)), FindSet(Z.set.get(5)));
        Union(FindSet(Z.set.get(0)), FindSet(Z.set.get(7)));

        for (Node x : Z.set) {
            System.out.format("Key: %x\n", x.key);
            System.out.format("Parent: %x\n", x.parent.key);
            System.out.format("Rank: %x\n", x.rank);
            System.out.print("Path to root: ");
            PathToRoot(Z.set.get(x.key));
            System.out.println("\n--------------------");
        }
    }
}
