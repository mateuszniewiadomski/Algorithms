public class LongestCommonSubsequence {
    
    static void tabFill(int[][] tab, int a, int b) {
        for(int i = 0; i <= b; i++) {
            for(int j = 0; j <= a; j++) {
                tab[j][i] = 0;
            }
        }
    }

    static void lcs(int[][] tab, String seq1, String seq2, int a, int b) {
        tabFill(tab, a, b);
        for(int i = 1; i <= b; i++) {
            for(int j = 1; j <= a; j++) {
                if(seq1.charAt(j-1) == seq2.charAt(i-1)) {
                    tab[j][i] = tab[j-1][i-1] + 1;
                } else {
                    if(tab[j-1][i] >= tab[j][i-1]) {
                        tab[j][i] = tab[j-1][i];
                    } else {
                        tab[j][i] = tab[j][i-1];
                    }
                }
            }
        }
        lcsPrintTable(tab, seq1, seq2, a, b);
        lcsPrint(tab, seq1, seq2, a, b);
    }

    static void lcsPrintTable(int[][] tab, String seq1, String seq2, int a, int b) {
        System.out.println("Graphic visualization: ");
        System.out.format("    ");
        for (int i = 0; i < a; i++) {
            System.out.format("|%c", seq1.charAt(i));
        }
        System.out.format("|");
        System.out.println();
        System.out.format("  |0");
        for (int i = 0; i < a; i++) {
            System.out.format("|%d", tab[i][0]);
        }
        System.out.format("|");
        System.out.println();
        for (int i = 1; i <= b; i++) {
            System.out.format("|%c", seq2.charAt(i-1));
            System.out.format("|%d", tab[0][i]);
            for (int j = 1; j <= a; j++) {
                System.out.format("|%d", tab[j][i]);
            }
            System.out.format("|");
            System.out.println();
        }
    }

    static void lcsPrint(int[][] tab, String seq1, String seq2, int a, int b) {
        String subsequence = "";
        String[] tabOfSeq = new String[a*b+1];
        int x = a;
        int y = b;
        int c = 0;
        while(tab[a][b] != 0) {
            if(tab[a-1][b] < tab[a][b]) {
                a--;
                b--;
                subsequence = subsequence + seq1.charAt(a);
            } else {
                a--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = subsequence.length() - 1; i >= 0; i--)
        {
            sb.append(subsequence.charAt(i));
        }
        subsequence = sb.toString();
        tabOfSeq[c] = subsequence;
        c++;
        System.out.println("The Longest Common Subsequence: " + subsequence);
        System.out.println("Length = " + subsequence.length());
        System.out.println("The rest of subsequences:");
        for(int i = x; i >= 0; i--) {
            for(int j = y; j >= 0; j--) {
                a = i;
                b = j;
                subsequence = "";
                while(tab[a][b] != 0) {
                    if(tab[a-1][b] < tab[a][b]) {
                        a--;
                        b--;
                        subsequence = subsequence + seq1.charAt(a);
                    } else {
                        a--;
                    }
                }
                if(subsequence != "") {
                    StringBuilder sb2 = new StringBuilder();
                    for(int z = subsequence.length() - 1; z >= 0; z--)
                    {
                        sb2.append(subsequence.charAt(z));
                    }
                    subsequence = sb2.toString();
                    tabOfSeq[c] = subsequence;
                    boolean bool = true;
                    for(int z = c-1; z >= 0; z--) {
                        if(tabOfSeq[z].equals(subsequence)) {
                            bool = false;
                        }
                    }
                    c++;
                    if(bool) {
                        System.out.println(subsequence);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String seq1 = "politechnika";
        String seq2 = "toaleta";
        int a = seq1.length();
        int b = seq2.length();
        int[][] tab = new int[a + 1][b + 1];
        lcs(tab, seq1, seq2, a, b);
    }
}