import java.util.Random;

public class QuickSort {

    public static int[] GenerujDuzomTablice(int ilosc) {
        int[] Y = new int[ilosc];
        Random generator = new Random();
        for(int i = 0; i < ilosc; i++) {
            Y[i] = generator.nextInt(ilosc+1);
        }
        return Y;
    }
    public static int[] GenerujDuzomTabliceMalejaca(int ilosc) {
        int[] Y = new int[ilosc];
        int j = ilosc;
        for (int i = 0; i < ilosc; i++) {
            Y[i] = j;
            j--;
        }
        return Y;    
    }

    public static int Partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p-1;
        for(int j = p; j <= r; j++) {
            if(A[j] <= x) {
                i++;   
                int swap = A[j];
                A[j] = A[i];
                A[i] = swap;
            }
        }
        if(i < r) {
            return i;
        } else {
            return i-1;
        }
    }

    public static void QuickSort1(int[] A, int p, int r) {
        if(p < r) {
            int q = Partition(A, p, r);
            QuickSort1(A, p, q);
            QuickSort1(A, q+1, r);
        }
    }

    public static void QuickSort2(int[] A, int p, int r) {
        int c = 5;
        if(p < r) {
            if(r-p+1 < c) {
                BubbleSort(A, p, r);
            } else {
                int q = Partition(A, p, r);
                QuickSort2(A, p, q);
                QuickSort2(A, q+1, r); 
            }
        }
    }

    public static void BubbleSort(int[] A, int p, int r) {
        for(int i = p; i <= r; i++) {
            for(int j = p; j < r; j++) {
                if(A[j] > A[j+1]) {
                    int swap = A[j];
                    A[j] = A[j+1];
                    A[j+1] = swap;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] A = {23, 6, 11, 12, 17, 19, 7, 18, 12, 14, 15};
        int[] B = {23, 6, 11, 12, 17, 19, 7, 18, 12, 14, 15};
        System.out.print("Przed posortowaniem: ");
        for(int x : A) {
            System.out.print(x + ", ");
        }
        QuickSort1(A, 0, (A.length-1));
        System.out.print("\nPo posortowaniu 1: ");
        for(int x : A) {
            System.out.print(x + ", ");
        }
        QuickSort2(B, 0, (B.length-1));
        System.out.print("\nPo posortowaniu 2: ");
        for(int x : B) {
            System.out.print(x + ", ");
        }
        int ilosc = 10;
        System.out.println("\n\n    DANE LOSOWE (czas w ms)");
        System.out.println("| rozmiar tablicy | czas - algorytm 1 | czas - algorytm 2 |");
        for(int i = 0; i < 11; i++) {
            int[] X = GenerujDuzomTablice(ilosc);
            int[] Y = new int[ilosc];
            for(int j = 0; j < ilosc; j++) {
                Y[j] = X[j];
            }
            long start1 = System.currentTimeMillis();
            QuickSort1(X, 0, (X.length-1));
            long stop1 =System.currentTimeMillis();
            long start2 = System.currentTimeMillis();
            QuickSort2(Y, 0,( X.length-1));
            long stop2 = System.currentTimeMillis();
            System.out.format("|%17d|%19d|%19d|\n", ilosc, stop1-start1, stop2-start2);
            ilosc = ilosc*2;
        }
        System.out.println("\n    DANE NIEKORZYSTNE (czas w ms)");
        System.out.println("| rozmiar tablicy | czas - algorytm 1 | czas - algorytm 2 |");
        ilosc = 10;
        for(int i = 0; i < 11; i++) {
            int[] X = GenerujDuzomTabliceMalejaca(ilosc);
            long start1 = System.currentTimeMillis();
            QuickSort1(X, 0, (X.length-1));
            long stop1 =System.currentTimeMillis();
            int[] Y = GenerujDuzomTabliceMalejaca(ilosc);
            long start2 = System.currentTimeMillis();
            QuickSort2(Y, 0, (X.length-1));
            long stop2 = System.currentTimeMillis();
            System.out.format("|%17d|%19d|%19d|\n", ilosc, stop1-start1, stop2-start2);
            ilosc = ilosc*2;
        } 
    }
}