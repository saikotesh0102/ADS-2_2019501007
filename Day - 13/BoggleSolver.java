import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class BoggleSolver{

    private TrieST<Integer> data;
    private Set<String> words;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException();
        }
        this.data = new TrieST<Integer>();
        this.words = new HashSet<String>();
        for(String S : dictionary) {
            data.put(S,this.scoreOf(S));
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] visited = new boolean[board.rows()][board.cols()];
				dfs(board, i, j, check(board.getLetter(i, j)), visited);
			}
		}
        return words;
	}

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }

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
        int len = word.length();
        switch (len) {
            case 0:
                return 0;
            case 1 : 
                return 0;
            case 2 : 
                return 0;
            case 3 : 
                return 1;
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
    }

    private boolean index(int i, int j, int rows, int cols) {
		if (i < 0 || i >= rows || j < 0 || j >= cols) {
			return false;
		}
		return true;
    }
    
    private void dfs(BoggleBoard board, int row, int col, String word, boolean[][] visited) {
		int rows = board.rows();
		int cols = board.cols();
		if (row < 0 || col < 0 || row >= rows || col >= cols) {
			return;
		}
		visited[row][col] = true;
		if (data.contains(word) && (!words.contains(word)) && word.length() > 2 && word != null) {
			words.add(word);
		}
		if (index(row + 1, col + 1, rows, cols) && !visited[row + 1][col + 1] && isValid(word)) {
			String s = word + check(board.getLetter(row + 1, col + 1));
			dfs(board, row + 1, col + 1, s, visited);
			visited[row + 1][col + 1] = false;
		}
		if (index(row - 1, col - 1, rows, cols) && !visited[row - 1][col - 1] && isValid(word)) {
			String s = word + check(board.getLetter(row - 1, col - 1));
			dfs(board, row - 1, col - 1, s , visited);
			visited[row - 1][col - 1] = false;
		}
		if (index(row - 1, col + 1, rows, cols) && !visited[row - 1][col + 1] && isValid(word)) {
			String s = word + check(board.getLetter(row - 1, col + 1));
			dfs(board, row - 1, col + 1, s, visited);
			visited[row - 1][col + 1] = false;
		}
		if (index(row + 1, col - 1, rows, cols) && !visited[row + 1][col - 1] && isValid(word)) {
			String s = word + check(board.getLetter(row + 1, col - 1));
			dfs(board, row + 1, col - 1, s, visited);
			visited[row + 1][col - 1] = false;
		}
		if (index(row - 1, col, rows, cols) && !visited[row - 1][col] && isValid(word)) {
			String s = word + check(board.getLetter(row - 1, col));
			dfs(board, row - 1, col, s, visited);
			visited[row - 1][col] = false;
		}
		if (index(row + 1, col, rows, cols) && !visited[row + 1][col] && isValid(word)) {
			String s = word + check(board.getLetter(row + 1, col));
			dfs(board, row + 1, col, s, visited);
			visited[row + 1][col] = false;
		}
		if (index(row, col + 1, rows, cols) && !visited[row][col + 1] && isValid(word)) {
			String s = word + check(board.getLetter(row, col + 1));
			dfs(board, row, col + 1, s, visited);
			visited[row][col + 1] = false;
		}
		if (index(row, col - 1, rows, cols) && !visited[row][col - 1] && isValid(word)) {
			String s = word + check(board.getLetter(row, col - 1));
			dfs(board, row, col - 1, s, visited);
			visited[row][col - 1] = false;
		}
    }
    
    private boolean isValid(String word) {
		return data.contains(word);
    }
    
	private String check(char ch) {
		if (ch == 'Q') {
			return "QU";
		}
		return ch + "";
	}
    
    public static void main(String[] args) {
        
    }
}