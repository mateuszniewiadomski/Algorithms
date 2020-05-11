import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchPattern {

    private static List<Integer> lines = new ArrayList<Integer>();

    private static void Naive(String text, String pattern) {
        int tLength = text.length();
        int pLength = pattern.length();
        for (int i = 0; i <= tLength - pLength; i++) {
            int j;
            for (j = 0; j < pLength; j++) {
                if (text.charAt(i+j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == pLength) {
                int x = 0;
                while (lines.get(x) <= i) {
                    x++;
                }
                System.out.println("Pattern found at line: " + (x+1) + ", character: " + (i-lines.get(x-1)+1));
            }
        }
    }

    private static void RabinKarp(String text, String pattern, int d, int q) {
        int tLength = text.length();
        int pLength = pattern.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;

        for (i = 0; i < pLength - 1; i++) {
            h = (h * d) % q;
        }
        for (i = 0; i < pLength; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }
        for (i = 0; i <= tLength - pLength; i++) {
            if (p == t) {
                for (j = 0; j < pLength; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }
                if (j == pLength) {
                    int x = 0;
                    while (lines.get(x) <= i) {
                        x++;
                    }
                    System.out.println("Pattern found at line: " + (x+1) + ", character: " + (i-lines.get(x-1)+1));
                }
            }
            if (i < tLength - pLength) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + pLength)) % q;
                if (t < 0) {
                    t = (t + q);
                }
            }
        }
    }

    private static void KnuthMorrisPratt(String text, String pattern) {
        int tLength = text.length();
        int pLength = pattern.length();
        int lps[] = new int[pLength];
        int j = 0;
        PrefixFunction(pattern, pLength, lps);
        int i = 0;
        while (i < tLength) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == pLength) {
                int x = 0;
                while (lines.get(x) <= (i-j)) {
                    x++;
                }
                System.out.println("Pattern found at line: " + (x+1) + ", character: " + (i-lines.get(x-1)+1-j));
                j = lps[j - 1];
            } else if (i < tLength && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }

    private static void PrefixFunction(String pattern, int pLength, int lps[]) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < pLength) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    private static String readFile(String pathName, boolean text) {
        String x = "";
        lines.clear();
        try {
            File file = new File(pathName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                x = x + scanner.nextLine();
                if (text) {
                    lines.add(x.length());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        return x;
    }

    public static void main(String[] args) {

        String pattern = readFile("wzorzec.txt", false);
        String text = readFile("tekst.txt", true);
        System.out.println("----------------------------------------");
        System.out.println("1) Naive Algorithm: ");
        System.out.println("----------------------------------------");
        long startNaive = System.currentTimeMillis();
        Naive(text, pattern);
        long stopNaive = System.currentTimeMillis();
        System.out.println("----------------------------------------");
        System.out.println("2) Rabin-Karp Algorithm: ");
        System.out.println("----------------------------------------");
        long startRabinKarp = System.currentTimeMillis();
        RabinKarp(text, pattern, 128, 27077);
        long stopRabinKarp = System.currentTimeMillis();
        System.out.println("----------------------------------------");
        System.out.println("3) Knuth-Morris-Pratt Algorithm: ");
        System.out.println("----------------------------------------");
        long startKnuthMorrisPratt = System.currentTimeMillis();
        KnuthMorrisPratt(text, pattern);
        long stopKnuthMorrisPratt = System.currentTimeMillis();
        System.out.println("----------------------------------------");
        System.out.format("%20s | %15s \n", "Algorithm", "Time Complexity");
        System.out.format("%20s | %12d ms\n", "Naive", stopNaive-startNaive);
        System.out.format("%20s | %12d ms\n", "Rabin-Karp", stopRabinKarp-startRabinKarp);
        System.out.format("%20s | %12d ms", "Knuth-Morris-Pratt", stopKnuthMorrisPratt-startKnuthMorrisPratt);
    }
}
