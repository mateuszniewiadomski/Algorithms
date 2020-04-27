import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class HeapSort {

    public static void GenerujPlik(PrintWriter generuj) {
        Random generator = new Random();
        for (int i = 0; i < 20; i++) {
            generuj.println(generator.nextInt(20)+1);
        }
    }

    public static int WielkoscTablicy(Scanner in0, int ilosc) {
        while (in0.hasNext()) {
            in0.nextInt();
            ilosc++;
        }
        System.out.println("Ilosc elementow: " + ilosc);
        return ilosc;
    }

    public static void OdczytPliku(Scanner in1, int ilosc, int[] surowe) {
        while (in1.hasNext()) {
            int cyferka = in1.nextInt();
            surowe[ilosc] = cyferka;
            ilosc++;
        }
    }

    public static void PrzepiszTablice(int[] B, int[] A) {
        int i = 0;
        for (int x : A) {
            B[i] = x;
            i++;
        } 
    }

    public static void BuildHeapRecuration(int A[]) {
        int n = A.length;
        for (int i = n/2-1; i >= 0; i--) {
            HeapifyRecurtation(A, n, i);
        }
        for (int i = n-1; i >= 0; i--) {
            int swap = A[0];
            A[0] = A[i];
            A[i] = swap;
            HeapifyRecurtation(A, i, 0);
        }
    }

    public static void HeapifyRecurtation(int A[], int n, int i) {
        int largest = i;
        int l = 2*i+1;
        int r = 2*i+2;
        if (l < n && A[l] > A[largest]) {
            largest = l;
        }
        if (r < n && A[r] > A[largest]) {
            largest = r;
        }
        if (largest != i) {
            int swap = A[i];
            A[i] = A[largest];
            A[largest] = swap;
            HeapifyRecurtation(A, n, largest);
        }
    }

    public static void BuildHeapIterative(int B[]) {
        int n = B.length;
        for (int i = 1; i < n; i++) {
            if (B[i] > B[(i-1)/2]) {
                int j = i;
                while (B[j] > B[(j-1)/2]) {
                    int swap = B[j];
                    B[j] = B[(j-1)/2];
                    B[(j-1)/2] = swap;
                    j = (j-1)/2;
                }
            }
        }
    }

    public static void HeapifyIterative(int B[]) {
        BuildHeapIterative(B);
        int n = B.length;
        for (int i = n-1; i > 0; i--) {
            int swap = B[0];
            B[0] = B[i];
            B[i] = swap;
            int index;
            int j = 0;
            do {
                index = (2*j+1);
                if (index < (i-1) && B[index] < B[index+1]) {
                    index++;
                }
                if (index < i && B[j] < B[index]) {
                    int swap2 = B[j];
                    B[j] = B[index];
                    B[index] = swap2;
                } 
                j = index;
            } while (index < i);
        }
    }

    public static void ZapisPliku(PrintWriter zapis, int A[]) {
        for (int x : A) {
            zapis.println(x);
        } 
    } 

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter generuj = new PrintWriter("HeapSort.txt");
        GenerujPlik(generuj);
        generuj.close();
        System.out.println("Generowanie pliku wykonano");
        File file = new File("HeapSort.txt");
        PrintWriter zapis = new PrintWriter("HeapSort_output.txt");
        Scanner in0 = new Scanner(file);
        Scanner in1 = new Scanner(file);
        int ilosc = WielkoscTablicy(in0, 0);
        System.out.println("Obliczanie wielkosci tablicy wykonano");
        int A[] = new int[ilosc];
        int B[] = new int[ilosc];
        OdczytPliku(in1, 0, A);
        PrzepiszTablice(B, A);
        System.out.println("Odczyt pliku wykonano");
        BuildHeapRecuration(A);
        System.out.println("Sortowanie rekurencyjne wykonano");
        zapis.println("Sortowanie Rekurencyjne:\n");
        ZapisPliku(zapis, A);
        zapis.println("\n");
        System.out.println("Zapisywanie posortowanej tablicy rekurencyjnie wykonano");
        HeapifyIterative(B);
        System.out.println("Sortowanie iteracyjne wykonano");
        zapis.println("Sortowanie Iteracyjne:\n");
        ZapisPliku(zapis, B);
        System.out.println("Zapisywanie posortowanej tablicy iteracyjnie wykonano");
        zapis.close();
    }
}
