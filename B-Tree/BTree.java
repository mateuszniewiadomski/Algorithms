public class BTree {

    int t; //tree degree
    BNode x;

    class BNode {
        
        int t; //degree of tree
        int n; //amount of Nodes
        boolean leaf; //if node is a leaf
        int[] k; //keys inside node
        BNode[] c; //node children
        //BNode p; //node parent

        //BNode() {}

        BNode(int t, boolean leaf) {
            
            this.t = t;
            this.n = 0;
            this.leaf = leaf;
            this.k = new int[2*t-1];
            this.c = new BNode[2*t];
            //this.p = p;
        }

        void nSet(int n) {
            this.n = n;
        }

        int nGet() {
            return n;
        }

        int tGet() {
            return t;
        }

        boolean isLeaf() {
            return leaf;
        }

        int kGet(int i) {
            return k[i];
        }

        BNode BTreeSearch(int ks) {
            //searching key k in root x
    
            int i = 0;
            while(i < n && ks > k[i]) {
                i++;
            }
            if(i <= n && ks == k[i]) {
                return this;
            }
            if(leaf) {
                return null;
            } else {
                diskReader(this);
                return c[i].BTreeSearch(ks);
            }
        }

        void BTreeSplitChild(int i, BNode y) {
            //split node which i child is node y
    
            BNode z = new BNode(y.tGet(), y.isLeaf()); 
            z.nSet(t-1);
            for(int j = 0; j < t-1; j++) {
                z.k[j] = y.k[j+t];
            }
            if(!y.isLeaf()) {
                for(int j = 0; j < t; j++) {
                    z.c[j] = y.c[j+t];
                }
            }
            y.nSet(t-1);
            for(int j = n; j >= i+1; j--) {
                c[j+1] = c[j];
            }
            c[i+1] = z;
            for(int j = n-1; j >= i; j--) {
                k[j+1]= k[j];
            }
            k[i] = y.k[t-1];
            n++;
            diskWriter(y);
            diskWriter(z);
            diskWriter(x);
        }

        void BTreeInsertNonfull(int ks) {
            //inserts ks key, to tree which is root, node is not full
    
            int i = n-1;
            if(leaf) {
                while(i >= 0 && ks < k[i]) {
                    k[i+1] = k[i];
                    i--;
                }
                k[i+1] = ks;
                n++;
                diskWriter(x);
            } else {
                while(i >= 0 && ks < k[i]) {
                    i--;
                }
                //i++;
                diskReader(c[i+1]);
                if(c[i+1].n == t*2-1) {
                    BTreeSplitChild(i+1, c[i+1]);
                    if(ks > k[i+1]) {
                        i++;
                    }
                }
                c[i+1].BTreeInsertNonfull(ks);
            }
        }

        void BTreePrint(boolean ch, int p) {
            //prints tree inside console, (root left children right)
            //p number of spaces
            //ch if true changing to char mode

            if(leaf) {
                for(int i = 0; i < p; i++) {
                    System.out.print(" ");
                }
                for(int i = 0; i < n; i++) {
                    if(ch) {
                        System.out.print((char) k[i] + " ");
                    } else {
                        System.out.print(k[i] + " ");
                    }
                }
                System.out.println();
            } else {
                c[n].BTreePrint(ch, p+4);
                for(int i = n-1; i >= 0; i--) {
                    for(int j = 0; j < p; j++) {
                        System.out.print(" ");
                    } 
                    if(ch) {
                        System.out.println((char) k[i]);
                    } else {
                        System.out.println(k[i]);
                    }
                    c[i].BTreePrint(ch, p+4);
                }
            }
        }

        void diskReader(BNode x) {
            //here read disc function
        }
        
        void diskWriter(BNode x) {
            //here write disc function
        }

    }

    BTree(int t) {
        this.t = t;
        this.x = null;
    }

    void BTreeInsert(int k) {

        if(x == null) {
            x = new BNode(t, true);
            x.k[0] = k;
            x.nSet(x.nGet()+1);
        } else {
            if(x.nGet() == 2*t-1) {
                BNode s = new BNode(t, false);
                s.c[0] = x;
                s.BTreeSplitChild(0, x);
                int i = 0;
                if(s.k[0] < k) {
                    i++;
                }
                s.c[i].BTreeInsertNonfull(k);
                x = s;
            } else {
                x.BTreeInsertNonfull(k);
            }
        }
    }
    
    BNode BTreeSearch(int k) {

        if(x == null) {
            return null;
        }
        return x.BTreeSearch(k);
    }

    void BTreePrint(boolean ch) {

        if(x != null) {
            if(ch) {
                x.BTreePrint(true, 0);
            } else {
                x.BTreePrint(false, 0);
            }
        }
    }

    
    public static void main(String[] args) {

        int t = 3;
        BTree btree = new BTree(t);
        boolean isChar;
        //int[] tab = {21, 49, 90, 6, 22, 82, 50, 64, 61, 94, 9, 3, 60, 28, 56, 10, 73, 53, 88, 78};
        //isChar = false;
        char[] tab = {'F', 'S', 'Q', 'K', 'C', 'L', 'H', 'T', 'V', 'W', 'M', 'R', 'N', 'P', 'A', 'B', 'X', 'Y', 'D', 'Z', 'E'};
        isChar = true;
        int j = 1;
        for(int i : tab) {
            System.out.println("step " + j++  + ")-----------------");
            btree.BTreeInsert(i);
            btree.BTreePrint(isChar);
        }
        //btree.BTreePrint(isChar);
        int k = 'S';
        System.out.println("Search result: " + btree.BTreeSearch(k));
        k = 'G';
        System.out.println("Search result: " + btree.BTreeSearch(k));
        k = 'B';
    }
} 
