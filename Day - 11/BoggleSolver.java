import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
// import edu.princeton.cs.algs4.In;

public class BoggleSolver{

    private String[] dictionary;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException();
        }
        this.dictionary = dictionary;
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {

	}

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }

        if (word.length() == 0 || word.length() == 1 || word.length() == 2) {
            return 0;
        } else if (word.length() == 3 || word.length() == 4) {
            return 1;
        } else if (word.length() == 5) {
            return 2;
        } else if (word.length() == 6) {
            return 3;
        } else if (word.length() == 7) {
            return 5;
        } else {
            return 11;
        }
    }
    
    public static void main(String[] args) {
        HashMap<String,Integer> database = new HashMap<String,Integer>();
        In scan = new In("dictionary-algs4.txt");
        while(scan.hasNextLine()) {
            String str = scan.readLine();
            database.put(str, str.length());
        }
        Set<String> keys = database.keySet();
        String[] array = keys.toArray(new String[0]);
        BoggleSolver bs = new BoggleSolver(array);
    }
}