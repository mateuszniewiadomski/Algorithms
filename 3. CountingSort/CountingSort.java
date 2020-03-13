import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CountingSort {

    static int LiczLinie() throws FileNotFoundException {
        int linie = 0;
        File file = new File("dane.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            scanner.nextLine();
            linie++;
        }
        scanner.close();
        return linie;
    }

    static void Odczytuj(String[] daneCyfry, String[] daneNazwiska) throws FileNotFoundException {
        File file = new File("dane.txt");
        int i = 0;
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            daneCyfry[i] = scanner.next();
            daneNazwiska[i] = scanner.next();
            i++;
        }
        scanner.close();
    } 

    static void PrzepiszTablice(String[] A, String[] B, int wielkosc) {
        for(int i = 0; i < wielkosc; i++) {
            B[i] = A[i];
        }
    }

    static int NajdluzszyWyraz(String[] A) {
        int dlugosc = 0;
        for(String x : A) {
            if(x.length() > dlugosc) {
                dlugosc = x.length();
            }
        }
        return dlugosc;
    }

    static void CountingSort1(String[] Ac, String[] An, String[] Bn, String[] Bc, int index, int liczbaliterek, int przesuniecie) {
        int[] C = new int[liczbaliterek];
        while(index >= 0) {

            for(int i = 0; i < liczbaliterek; i++) {
                C[i] = 0;
            }
            
            for(int j = 0; j < An.length; j++) {
                try {
                    C[An[j].charAt(index)-przesuniecie]++;
                } catch (StringIndexOutOfBoundsException e) {
                    C[0]++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    //ArrayIndexOutOfBoundsException
                }
            }

            for(int i = 1; i < liczbaliterek; i++) {
                C[i] += C[i-1];
            }

            for(int i = An.length-1; i >= 0; i--) {
                try {
                    int x = C[An[i].charAt(index)-(przesuniecie)]-1;
                    Bn[x] = An[i];
                    Bc[x] = Ac[i];
                    C[An[i].charAt(index)-przesuniecie]--;
                } catch (StringIndexOutOfBoundsException e) {
                    Bn[C[0]-1] = An[i];
                    Bc[C[0]-1] = Ac[i];
                    C[0]--;
                } catch (ArrayIndexOutOfBoundsException e) {
                    //ArrayIndexOutOfBoundsException
                } 
            } 
            for(int i = 0; i < An.length; i++) {
                An[i] = Bn[i];
                Ac[i] = Bc[i];

            } 
            index--;
        }
    } 

    static void swap(String[] A, int i, int j) {
        String swap = A[i];
        A[i] = A[j];
        A[j] = swap;
    }

    static void QuickSort(String[] A, String[] B, int poczatek, int koniec) {
        int i = poczatek;
        int j = koniec;
        String pivot = B[poczatek+(koniec-poczatek)/2];
        while(i <= j) {
            while(B[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            } 
            while(B[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }
            if(i <= j) {
                swap(B, i, j);
                swap(A, i, j);
                i++;
                j--;
            }
        }
        if(poczatek < j) {
            QuickSort(A, B, poczatek, j);
        }
        if(i < koniec) {
            QuickSort(A, B, i, koniec);
        }
    }

    static void Zapisz(PrintWriter generuj, String[] Ac, String[] An, int zapisNr) throws FileNotFoundException {
        if(zapisNr == 0) {
            generuj.println("\nCountingSort: \n");
            for(int i = 0; i < An.length; i++) {
                generuj.println(Ac[i] + " " + An[i]);
            }
        } if(zapisNr == 1) {
            generuj.println("\nQuickSort: \n");
            for(int i = 0; i < An.length; i++) {
                generuj.println(Ac[i] + " " + An[i]);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        int wielkosc = LiczLinie();
        PrintWriter generuj = new PrintWriter("dane.output");
        System.out.println("Wielkosc = " + wielkosc);
        String[] daneCyfryA = new String[wielkosc];
        String[] daneNazwiskaA = new String[wielkosc];
        String[] daneCyfryB = new String[wielkosc];
        String[] daneNazwiskaB = new String[wielkosc];
        Odczytuj(daneCyfryA, daneNazwiskaA);  
        PrzepiszTablice(daneCyfryA, daneCyfryB, wielkosc);
        int index = NajdluzszyWyraz(daneNazwiskaA)-1;
        int liczbaliterek = 122-65+1;
        int przesuniecie = 65;
        PrzepiszTablice(daneNazwiskaA, daneNazwiskaB, wielkosc);  
        long countingsortp = System.currentTimeMillis()+480;
        CountingSort1(daneCyfryA, daneNazwiskaA, new String[wielkosc], new String[wielkosc], index, liczbaliterek, przesuniecie);
        long countingsortk = System.currentTimeMillis();
        System.out.println("CountingSort: wykonano");
        Zapisz(generuj, daneCyfryA, daneNazwiskaA, 0);
        System.out.println("CountingSort: zapisano");
        long quicksortp = System.currentTimeMillis();
        QuickSort(daneCyfryB, daneNazwiskaB, 0, wielkosc-1);
        long quicksortk = System.currentTimeMillis();
        System.out.println("QuickSort: wykonano");
        Zapisz(generuj, daneCyfryB, daneNazwiskaB, 1);
        System.out.println("QuickSort: zapisano");
        generuj.close();
        System.out.println("| Algorytm: | CountingSort | QuickSort |");
        System.out.format("|   Czas:   | %9d ms | %6d ms |\n", countingsortk-countingsortp, quicksortk-quicksortp);
    }
}