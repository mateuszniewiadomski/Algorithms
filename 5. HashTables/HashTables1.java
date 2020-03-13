import java.io.*;
import java.util.*;

public class HashTables1 {    
    static int liczLinie(String nazwaPliku) throws FileNotFoundException {
        int linie = 0;
        File file = new File(nazwaPliku);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            scanner.nextLine();
            linie++;
        }
        scanner.close();
        return linie;
    }
    
    static void wypelnijTablice(String[] T, String nazwaPliku) throws FileNotFoundException {
        int i = 0;
        File file = new File(nazwaPliku);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            T[i] = scanner.next();
            i++;
        }
        scanner.close();
    }

    static void zerujTablice(int[] tab) {
        int i = 0;
        while(tab.length != i) {
            tab[i] = 0;
            i++;
        }
    }

    static int hashujSlowo(String s, int m) {
        int i = 0;
        int x = 0;
        double y = 0;
        while(s.length() != i) {
            x = (int)s.charAt(i)*11;
            y += x;
            i++;
        }
        return (int)y % m;
    }

    static void hashuj(String[] T, int[] tab, int m) {
        int i = 0;
        int j = 0;
        while(tab.length != j) {
            i = hashujSlowo(T[j], m);
            tab[i]++;
            j++;
        }
    }

    static void testuj(String[] T, int[] tab, int m) {
        hashuj(T, tab, m);
        int zero = 0;
        int niezero = 0;
        double srednia = 0;
        int maksimum = 0;
        int i = 0;
        while(tab.length != i) {
            if(tab[i] == 0) {
                zero++;
            } else {
                srednia += tab[i]; 
                niezero++;
            }
            if(tab[i] > maksimum) {
                maksimum = tab[i];
            }
            i++;
        }
        if(niezero != 0) {
            srednia /= niezero;
        }
        System.out.format("\n| %5d | %15d | %15f | %18d |", m, zero, srednia, maksimum);
    }

    public static void main(String[] args) throws FileNotFoundException {
        int m = liczLinie("3700.txt");
        String[] T = new String[m];
        wypelnijTablice(T, "3700.txt");

        System.out.print("|   m   | ZEROWE ELEMENTY | SREDNIA WARTOSC | MAKSYMALNA WARTOSC |");
        
        for(int i = 0; i <= 10; i++) {
            if(i == 0) m = 3697;
            if(i == 1) m = 3001;
            if(i == 2) m = 2549;
            if(i == 3) m = 1999;
            if(i == 4) m = 1499;
            if(i == 5) m = 3698;
            if(i == 6) m = 3000;
            if(i == 7) m = 2500;
            if(i == 8) m = 2048;
            if(i == 9) m = 1600;
            if(i == 10) m = 1024;
            int tab[] = new int[m];
            zerujTablice(tab);
            testuj(T, tab, m);
        }
        System.out.println();
    }
}