import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class HuffmanNode { 

	int frequency; 
	String character; 
	String binarycode;
	HuffmanNode left; 
	HuffmanNode right; 
} 

class MyComparator implements Comparator<HuffmanNode> { 
	public int compare(HuffmanNode x, HuffmanNode y) { 
		return x.frequency - y.frequency; 
	} 
} 

public class Huffman {

	static int countSingleCode = 0;
	static int countDualCode = 0;
	static int countSingle = 0;
	static int countDual = 0;

    static void generateTab(int[] single, int[] dual) {     
        try {
            File file = new File("dane.txt");
            Scanner scanner = new Scanner(file);
            String string = scanner.next();
            scanner.close();
            for(int i = 0; i < string.length(); i++) {
                int currentNumber = (int) string.charAt(i);
                currentNumber = currentNumber - 48;
                if(currentNumber >= 0 && currentNumber <= 7) {
                    single[currentNumber]++;
                    if(i%2 == 0) {
                        dual[currentNumber]++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
	}
	
    static void setBinaryCode(HuffmanNode root, String s) { 
		if (root.left == null && root.right == null) { 
			root.binarycode = s;
			return; 
		} 
		setBinaryCode(root.left, s + "0"); 
		setBinaryCode(root.right, s + "1"); 
	} 

	static void find(HuffmanNode h, String a, boolean b) {
		if(h == null)
			return;
		find(h.right, a, b);
		if(h.character.equals(a)) {
			if(b) {
				System.out.format("%11d|", h.frequency);
			} else {
				System.out.format("%8s|", h.binarycode);
			}
		}
		find(h.left, a, b);
	}

	static void countLength(HuffmanNode h, String a, boolean b) {
		if(h == null)
			return;
		countLength(h.right, a, b);
		if(h.character.equals(a)) {
			if(b) {
				countSingleCode += h.binarycode.length()*h.frequency;
				countSingle += h.frequency;
			} else {
				countDualCode += h.binarycode.length()*h.frequency;
				countDual += h.frequency;
			}
		}
		countLength(h.left, a, b);
	}

    public static void main(String[] args) {
        
        int n = 8;
        String[] singlechar = {"0", "1", "2", "3", "4", "5", "6", "7"};
        int[] singlefreq = new int[n];
        String[] dualchar = {"0", "1", "2", "3", "4", "5", "6", "7"};
		int[] dualfreq = new int[n];
		generateTab(singlefreq, dualfreq);
		PriorityQueue<HuffmanNode> single = new PriorityQueue<HuffmanNode>(n, new MyComparator()); 
		PriorityQueue<HuffmanNode> dual = new PriorityQueue<HuffmanNode>(n, new MyComparator()); 
        for (int i = 0; i < n; i++) {  
			HuffmanNode singlenode = new HuffmanNode(); 
			HuffmanNode dualnode = new HuffmanNode(); 
			singlenode.character = singlechar[i]; 
			dualnode.character = dualchar[i]; 
			singlenode.frequency = singlefreq[i]; 
			dualnode.frequency = dualfreq[i]; 
			singlenode.left = null; 
			dualnode.left = null; 
			singlenode.right = null; 
			dualnode.right = null; 
			single.add(singlenode); 
			dual.add(dualnode); 
		} 
		HuffmanNode rootsingle = null; 
		int z = 0;
		while (single.size() > 1) { 
			z++;
			HuffmanNode x = single.peek(); 
			single.poll(); 
			HuffmanNode y = single.peek(); 
			single.poll(); 
			HuffmanNode f = new HuffmanNode(); 
			f.frequency = x.frequency + y.frequency; 
			f.character = "z" + Integer.toString(z); 
			f.left = x; 
			f.right = y; 
			rootsingle = f; 
			single.add(f); 
		} 
		setBinaryCode(rootsingle, ""); 
		z = 0;
		HuffmanNode rootdual = null; 
		while (dual.size() > 1) { 
			z++;
			HuffmanNode x = dual.peek(); 
			dual.poll(); 
			HuffmanNode y = dual.peek(); 
			dual.poll(); 
			HuffmanNode f = new HuffmanNode(); 
			f.frequency = x.frequency + y.frequency; 
			f.character = "z" + Integer.toString(z); 
			f.left = x; 
			f.right = y; 
			rootdual = f; 
			dual.add(f); 
		} 
		setBinaryCode(rootdual, ""); 
		System.out.println("+-----------+-----------+-----------+--------+--------+");
		System.out.println("|           |   every   |   every   | every  | every  |");
		System.out.println("| character |  single   |  second   | single | second |");
		System.out.println("|           | frequency | frequency |  code  |  code  |");
		System.out.println("+-----------+-----------+-----------+--------+--------+");
		for(int i = 0; i < 8; i++) {
			System.out.format("|%11d|", i);
			String a = Integer.toString(i);
			find(rootsingle, a, true);
			find(rootdual, a, true);
			find(rootsingle, a, false);
			find(rootdual, a, false);
			System.out.println();
		}
		System.out.println("+-----------+-----------+-----------+--------+--------+");
		System.out.println();
		for(int i = 0; i < 8; i++) {
			String a = Integer.toString(i);
			countLength(rootsingle, a, true);
			countLength(rootdual, a, false);
		}
		System.out.println("+-------------------------+");
		System.out.println("|    Length Comparison    |");
		System.out.println("+--------+--------+-------+");
		System.out.println("|        | before | after |");
		System.out.format("| single |\u001B[31m%7d\u001B[0m |\u001B[32m%6d\u001B[0m |\n", countSingle*3, countSingleCode);
		System.out.format("|  dual  |\u001B[31m%7d\u001B[0m |\u001B[32m%6d\u001B[0m |\n", countDual*3, countDualCode);
		System.out.println("+--------+--------+-------+");
    }
}
