import java.util.*;
import java.io.*;

public class Solution{

	public static void main(String[] args) throws Exception {
		int no_of_testCases = 8;
		int  i = 0;
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
		String[] s = new String[no_of_inputs];
		LSD lsd = new LSD();
		int w = 0;
		for (int i = 0; i < no_of_inputs; i++) {
			s[i] = scan.nextLine();
			w = s[i].length();
		}
		scan.close();
		String[] output = lsd.sort(s,w);
		CheckOutput(output, no_of_inputs, outputFile);
	}

	public static void CheckOutput(String[] output, int no_of_inputs, String outputFile) throws Exception {
		Scanner scan = new Scanner(new File(outputFile));
		String[] data = scan.nextLine().split(",");
		scan.close();
		for (int i = 0; i < no_of_inputs; i++) {
			if (output[i].equals(data[i])) {
				System.out.println("Your output is not matching with output in file "+ outputFile);
				return;
			}
		}
		System.out.println("your output with file "+ outputFile+" Matched. This Test case passed");
	}
}