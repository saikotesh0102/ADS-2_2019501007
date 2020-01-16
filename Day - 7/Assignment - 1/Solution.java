import java.util.*;
import java.io.*;

public class Solution{

	public static void main(String[] args) throws Exception {
		int no_of_testcases = 4;
		int i = 0;
		while (i < no_of_testCases) {
			String inputFile = "input00" + i + ".txt";
			String outputFile = "output00" + i + ".txt";
			ReadInput(inputFile,outputFile);
			i++;
		}
	}

	public static void ReadInput(String inputFile, String outputFile) throws Exception {
		Scanner scan = new Scanner(new File(inputFile));
		int no_of_inputs = Integer.parseInt(scan.nextLine());
		String[] str = new String[no_of_inputs];
		int[] len = new int[no_of_inputs];
		for(int i = 0; i < no_of_inputs; i++) {
			
		}
		LongestRepeatedSubstring lrs = new LongestRepeatedSubstring();
		scan.close();
	}

	public static void CheckOutput(String output, int no_of_inputs, String outputFile) throws Exception {
		Scanner scan = new Scanner(new File(outputFile));
		scan.close();
	}
}