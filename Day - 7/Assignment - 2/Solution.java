import java.util.Scanner;

public class Solution{

	public static void main(String[] args) {
		int no_of_inputs = 5;
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
		for (int i = 0; i < no_of_inputs; i++) {
			str[i] = scan.nextLine();
		}
		scan.close();
	}

	public static void CheckOutput(String[] output, int no_of_inputs, String outputFile) throws Exception {
		Scanner scan = new Scanner(new File(outputFile));
		String[] data = new String[no_of_inputs];
		for (int i = 0; i < no_of_inputs; i++) {
			data[i] = scan.nextLine();
		}
		for (int i = 0; i < no_of_inputs; i++) {
			if(!(output[i].equals(data[i]))) {
				System.out.println("Your output is not matching with output in file " + outputFile);
				return;
			}
		}
		System.out.println("your output with file " + outputFile + " Matched. This Test case passed");
	}
}