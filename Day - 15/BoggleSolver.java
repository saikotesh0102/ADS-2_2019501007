import java.util.Set;
import java.util.HashSet;

public class BoggleSolver {

    private final TrieSET data;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        this.data = new TrieSET();
        for (String word : dictionary) {
            this.data.add(word);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        Set<String> words = new HashSet<String>();
        for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] visited = new boolean[board.rows()][board.cols()];
				dfs(board, i, j, visited, "", words);
			}
		}
        return words;
	}

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {

        // if (word.length() == 0 || word.length() == 1 || word.length() == 2) {
        //     return 0;
        // } else if (word.length() == 3 || word.length() == 4) {
        //     return 1;
        // } else if (word.length() == 5) {
        //     return 2;
        // } else if (word.length() == 6) {
        //     return 3;
        // } else if (word.length() == 7) {
        //     return 5;
        // } else {
        //     return 11;
        // }

        if (data.contains(word)) {
            switch (word.length()) {
            case 0 :
            case 1 : 
            case 2 : 
                return 0;
            case 3 : 
            case 4 : 
                return 1;
            case 5 : 
                return 2;
            case 6 : 
                return 3;
            case 7 : 
                return 5;
            default: 
                return 11;
            }
        } else {
            return 0;
        }
    }
    
    private void dfs(BoggleBoard board, int row, int col, boolean[][] visited, String prefix, Set<String> set) {
        if (visited[row][col]) {
            return;
        }

        char letter = board.getLetter(row, col);
        String word = prefix;

        if (letter == 'Q') {
            word += "QU";
        } else {
            word += letter;
        }

        if (!data.hasPrefix(word)) {
            return;
        }

        if (word.length() > 2 && data.contains(word)) {
            set.add(word);
        }

        visited[row][col] = true;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if ((row + i >= 0) && (row + i < board.rows()) && (col + j >= 0) && (col + j < board.cols())) {
                    dfs(board, row + i, col + j, visited, word, set);
                }
            }
        }
        visited[row][col] = false;
    }
    
    public static void main(String[] args) {
        // BoggleSolver bs = new BoggleSolver(array);
    }
}