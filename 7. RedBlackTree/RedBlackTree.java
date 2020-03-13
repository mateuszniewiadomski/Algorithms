/* 

Diagram printing algorithm:
Made by: Anurag Agarwal
https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram

*/

import java.util.Random;

public class RedBlackTree {
    
    class Node {
        int key;
        Node left, right, parent;
        char color;
    
        Node(int key) {
            this.key = key;
            this.left = nulNode;
            this.right = nulNode;
            this.parent = nulNode;
            this.color = 'B';
        }
    }

    Node node;
    Node nulNode = new Node(-1);
    Node root = nulNode;
    int countRed;
    int maxDepth = 0;
    int minDepth = 0;

    RedBlackTree() {
        node = nulNode;
    }

    RedBlackTree(Node root) {
        this.root = root;
    }
    
    public Node getRoot() {
		return this.root;
	}

    void insert(int key) {
        Node node = new Node(key);
        insert(node);
    }

    void insert(Node node) {
        Node temp = root;
        if(root == nulNode) { // jeżeli drzewo jest puste, węzeł staje się korzeniem
            root = node;
            node.color = 'B';
            node.parent = nulNode;
        } else {
            node.color = 'R'; // węzły są wstawiane jako czerwone
            while(true) {
                if(node.key < temp.key) { // jeżeli mniejsze to zgodnie z zasadą idzie na lewo
                    if(temp.left == nulNode) { // jeżeli lewy jest pusty, wchodzi w jego miejsce
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left; // jeżeli nie jest puste idze dalej w głąb drzewa
                    }
                } else { // jeżeli nie mniejsze, to zgodnie z zasadą idzie w prawo
                    if(temp.right == nulNode) { // jeżeli puste miejsce to wchodzi w jego miejsce
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right; // jeżeli nie jest puste idzie dalej w głąb drzewa
                    }
                }
            }
            fixTree(node); // po wstawieniu funkcja naprawy drzewa
        }
    }

    void fixTree(Node node) {
        Node uncle = nulNode;
        while(node.parent.color == 'R') {
            if(node.parent == node.parent.parent.left) { // jeżeli ojciec jest lewym synem
                uncle = node.parent.parent.right; // to wujek prawym synem dziadka
                if(uncle.color == 'R') { // jeżeli wujek czerwony zamiana kolorów
                    node.parent.color = 'B';
                    uncle.color = 'B';
                    node.parent.parent.color = 'R';
                    node = node.parent.parent; // węzeł staje się dziadkiem
                    continue;
                } else if(node == node.parent.right) { // prawy syn rotuje z ojcem
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.color = 'B'; // lewy syn rotuje z dziadkiem i zamiana kolorami
                node.parent.parent.color = 'R';
                rotateRight(node.parent.parent);
            } else { // jeżeli ojciec jest nie lewym synem
                uncle = node.parent.parent.left; // to wujek lewym synem dziadka
                if(uncle.color == 'R') { // jeżeli wujek czerwony zamiana kolorów
                    node.parent.color = 'B';
                    uncle.color = 'B';
                    node.parent.parent.color = 'R';
                    node = node.parent.parent;
                    continue;
                } else if(node == node.parent.left) { // lewy syn rotuje z ojcem
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = 'B'; // prawy syn rotuje z dziadkiem i zamiana kolorami
                node.parent.parent.color = 'R';
                rotateLeft(node.parent.parent);
            }
        }
        root.color = 'B'; // gdy dojdzie do korzenia, to ustawia go na czarno
    }

    private void rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left; // lewe poddrzewo staje się prawym poddrzewem
        if(temp.left != nulNode) { // jeżeli ma lewe dziecko
            temp.left.parent = node; // przypisanie dziecka
        }
        temp.parent = node.parent; // przypisanie rodzica
        if(node.parent == nulNode) { // jeżeli jest korzeniem
            this.root = temp; // przypisanie korzenia
        } else if(node == node.parent.left) { // jeżeli jest lewym dzieckiem
            node.parent.left = temp;
        } else { // jeżeli nie jest lewym dzieckiem
            node.parent.right = temp;
        }
        temp.left = node; // przypisanie jako lewe dziecko
        node.parent = temp; // przypisanie rodzica
    }

    private void rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right; // prawe poddrzewo staje się lewym poddrzewem
        if(temp.right != nulNode) { // jeżeli ma prawe dziecko
            temp.right.parent = node; // przypisanie dziecka
        }
        temp.parent = node.parent; // przypisanie rodzica
        if(node.parent == nulNode) { // jeżeli jest korzeniem
            this.root = temp; // przypisanie korzenia
        } else if(node == node.parent.right) { // jeżeli jest prawym dzieckiem
            node.parent.right = temp;
        } else { // jeżeli nie jest prawym dzieckiem
            node.parent.left = temp;
        }
        temp.right = node; // przypisanie jako prawe dziecko
        node.parent = temp; // przypisanie rodzica
    }

    int getRedNodes() {
		return this.countRed;
	}

    void countRedNodes(Node node) {
        if(node != nulNode) {
            if(node.color == 'R') {
                this.countRed++;
			}
			countRedNodes(node.left);
			countRedNodes(node.right);
		}
    }
    
    public int maxDepth(Node node) {
		if(node == nulNode) {
			return 0;
		}
		int leftDepth = maxDepth(node.left);
		int rightDepth = maxDepth(node.right);
		return Math.max(leftDepth, rightDepth)+1;
	}
	
	public int minDepth(Node node) {
		if(node == nulNode) {
			return 0;
		}
		int leftDepth = minDepth(node.left);
		int rightDepth = minDepth(node.right);
		return Math.min(leftDepth, rightDepth)+1;
	}
/*        
    void delete(int key) {
        node = delete(node, key);
        fixTree(node);
    }

    Node delete(Node root,int key) {
        if(node == nulNode) {
            return node;
        } else if(node.key > key) {
            node.left = delete(node.left, key);
        } else if(node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if(node.left == nulNode) {
                return node.left;
            } else if(node.right == nulNode) {
                return node.right;
            }
        }
        return root;
    }
*/    
    void print() {
        print(root, 0);
    }

    void print(Node root, int depth) {
        if(root == nulNode) {
            return;
        }
        print(root.right, depth+1);
        if(depth != 0) {
            for(int i = 0; i < depth - 1; i++) {
                System.out.print("|\t");
            }
                System.out.println("|───── " + root.key + "(" + root.color + ")");
        }
        else {
            System.out.println(root.key + "(" + root.color + ")");
        }
        print(root.left, depth+1);
    }
    public static void main(String[] args ) {
        RedBlackTree tree = new RedBlackTree();
        //int[] tab = {38, 31, 22, 8, 20, 5, 10, 9, 21, 27, 29, 25, 28};
        int[] tab = {23, 43, 16, 8, 22, 34, 11, 8};
        for(int i = 0; i < tab.length; i++) {
            tree.insert(tab[i]);
        }
        tree.print();
        
        tree.countRedNodes(tree.getRoot());
        System.out.println("Liczba czerownych: " + tree.getRedNodes());
        System.out.println("Max glebokosc: " + tree.maxDepth(tree.getRoot()));
		System.out.println("Min glebokosc: " + tree.minDepth(tree.getRoot()));
/*        
        tree.delete(5);
        tree.delete(38);
        tree.delete(8);
        tree.delete(10);
        tree.delete(10);
        tree.delete(22);
        tree.delete(20);
        tree.delete(29);
        tree.delete(31);

        tree.print();
*/
        System.out.println("Duże drzewo:");
        RedBlackTree bigTree = new RedBlackTree();
        for(int i = 0; i < 1000; i++) {
            Random generator = new Random();
            bigTree.insert(generator.nextInt(1000)+1);
        }
        bigTree.print();
        bigTree.countRedNodes(bigTree.getRoot());
        System.out.println("Liczba czerownych: " + bigTree.getRedNodes());
        System.out.println("Max glebokosc: " + bigTree.maxDepth(bigTree.getRoot()));
		System.out.println("Min glebokosc: " + bigTree.minDepth(bigTree.getRoot()));
    }
}
