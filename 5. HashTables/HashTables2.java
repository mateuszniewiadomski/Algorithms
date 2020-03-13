import java.io.*;
import java.util.*;

/* WARIANT U+OK */


public class HashTables2 {
    
    static int zliczLinie(String nazwapliku) throws FileNotFoundException {
        int linie = 0;
        File file = new File(nazwapliku);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            scanner.nextLine();
            linie++;
        }
        scanner.close();
        return linie;
    } 

    static void uzupelnijTablice(String nazwapliku, String[] nazwisko, int[] ilosc, int linie) throws FileNotFoundException {
        int licznik = 0;
        File file = new File(nazwapliku);
        Scanner scanner = new Scanner(file);
        while(licznik < linie) {
            ilosc[licznik] = scanner.nextInt();
            nazwisko[licznik] = scanner.next();
            licznik++;
        }
        scanner.close();
    }

    static void hash(int[] ilosc, String[] nazwisko, String[] hashtab, int wielkosc, boolean pierwsze) {
        int i = 0;
        int j = 0;
        int dopelnienie = 0;
        if(pierwsze != true) {
            j = (int)(wielkosc*0.4);
            dopelnienie = wielkosc;
        }
        while(j < wielkosc*0.8) {
            i = hashujSlowo(nazwisko[j], wielkosc);
            if(hashtab[i] == null || hashtab[i] == "DEL") {
                hashtab[i] = ilosc[j+dopelnienie] + " " + nazwisko[j+dopelnienie];
            } else {
                int x = 0;
                int c1 = 13;
                int c2 = 7;
                int y = 0;
                boolean b = false;
                while(b == false) {
                    x = ((i) + (c1*y) + (c2*(y*y))) % wielkosc;
                    if(x < 0) x += wielkosc-1;
                    if(hashtab[x] == null || hashtab[x] == "DEL") {
                        hashtab[x] = ilosc[j+dopelnienie] + " " + nazwisko[j+dopelnienie];
                        b = true;
                    }
                    y++;
                }
            }
            j++;
        }
    }

    static int hashujSlowo(String s, int wielkosc) {
        int i = 0;
        int x = 0;
        double y = 0;
        while(s.length() != i) {
            x = (int)s.charAt(i)*11;
            y += x;
            i++;
        }
        return (int)y % wielkosc;
    }

    static void hashUsun(String[] hashtab, int wielkosc) {
        int j = 0;
        int i = 0;
        while(i < wielkosc*0.4) {
            if(hashtab[j] != null && hashtab[j] != "DEL") {
                hashtab[j] = "DEL";
                i++;
            }
            j++;
        }
    }

    static void licz(int[] ilosc, String[] nazwisko, String[] hashtab, int wielkosc, boolean male) {
        hash(ilosc, nazwisko, hashtab, wielkosc, true);
        hashUsun(hashtab, wielkosc);
        hash(ilosc, nazwisko, hashtab, wielkosc, false);
        int i = 0;
        int licznik = 0;
        int nule = 0;
        while(hashtab.length != i) {
            if(hashtab[i] == "DEL") {
                licznik++;
            }
            if(hashtab[i] == null) {
                nule++;
            }
            i++;
        }
        if(male == true) {
            System.out.println("_____|PRZYKLADOWA DLA MALEGO|_____");
            for(String x : hashtab) {
                System.out.println(x);
            }
        }
        System.out.println("\nIlosc \"DEL\": " + licznik);
        System.out.println("Ilosc \"null\" " + nule + "\n");
    }

    public static void main(String[] args) throws FileNotFoundException {

        int m = 10;
        String[] nazwiskoM = new String[2*m];
        int[] iloscM = new int[2*m];
        String[] hashtabM = new String[m];
        uzupelnijTablice("nazwiskaASCII.txt", nazwiskoM, iloscM, 2*m);
        licz(iloscM, nazwiskoM, hashtabM, m, true);

        System.out.println("__________|DLA DUZEJ|__________");

        int wielkosc = zliczLinie("nazwiskaASCII.txt");
        String[] nazwiskoD = new String[wielkosc];
        int[] iloscD = new int[wielkosc];
        String[] hashtabD = new String[wielkosc/2];
        uzupelnijTablice("nazwiskaASCII.txt", nazwiskoD, iloscD, wielkosc);
        licz(iloscD, nazwiskoD, hashtabD, wielkosc/2, false);
        
    }
}