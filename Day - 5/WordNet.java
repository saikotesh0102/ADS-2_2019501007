/**
 * @author SaiKotesh0102
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
/**
 * WordNet Class is used to establish the connection between Synsets(Nouns)
 * and the Hypernyms. 
 */
public class WordNet {
    private final HashMap<Integer, String> idToStr;
    private final HashMap<String, Set<Integer>> strToID;
    private final Digraph graph;
    private final SAP sap;

    /**
     * constructor takes the name of the two input files
     */
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }
        this.idToStr = new HashMap<Integer, String>();
        this.strToID = new HashMap<String, Set<Integer>>();
        this.parseSynsets(synsets);
        this.graph = new Digraph(idToStr.size());
        this.parseHypernyms(hypernyms);
        this.sap = new SAP(this.graph);
    }
 
    /**
     * returns all the WordNet Nouns
     * @return nouns
     */
    public Iterable<String> nouns() {
        return this.strToID.keySet();
    }
 
    /**
     * If the @param word is null then throw an illegal argument exception
     * @param word
     * @return True if the Word is a Noun or else False
     */
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return this.strToID.containsKey(word);
    }
 
    /**
     * returns the shortest distance between two nouns A and B
     * @param nounA
     * @param nounB
     * @return distance
     */
    public int distance(String nounA, String nounB) {
        if (!(strToID.containsKey(nounA) && strToID.containsKey(nounB))) {
            throw new IllegalArgumentException("Argument should be in WordNet.");
        }
        // Iterable<Integer> itA = strToID.get(nounA);
        // Iterable<Integer> itB = strToID.get(nounB);
        // BreadthFirstDirectedPaths g1 = null, g2 = null;
        // g1 = new BreadthFirstDirectedPaths(this.graph, itA);
        // g2 = new BreadthFirstDirectedPaths(this.graph, itB);
                
        // int result = Integer.MAX_VALUE;
        // for (int i : idToStr.keySet()) {
        //     if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
        //         int cur = g1.distTo(i) + g2.distTo(i);
        //         // if (cur < result) {
        //         // 	result = cur;
        //         // }
        //         result = Math.min(result, cur);
        //     }
        // }
        // if (result == Integer.MAX_VALUE) {
        //     return -1;
        // }
        // return result;
        return this.sap.length(this.strToID.get(nounA), this.strToID.get(nounB));
    }
 
    /**
     * @param nounA
     * @param nounB
     * @return Synset that is common ancestor to both Nouns A nad B
     * in a shortest path possible.
     */
    public String sap(String nounA, String nounB) {
        if (!(strToID.containsKey(nounA) && strToID.containsKey(nounB))) {
            throw new IllegalArgumentException("Argument should be in WordNet.");
        }
        
        // Iterable<Integer> itA = strToID.get(nounA);
        // Iterable<Integer> itB = strToID.get(nounB);
        // BreadthFirstDirectedPaths g1 = null, g2 = null;
        // g1 = new BreadthFirstDirectedPaths(this.graph, itA);
        // g2 = new BreadthFirstDirectedPaths(this.graph, itB);
        
        // int result = Integer.MAX_VALUE;
        // int index = -1;
        // for (int i : idToStr.keySet()) {
        //     if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
        //         int cur = g1.distTo(i) + g2.distTo(i);
        //         if (cur < result) {
        //         	result = cur;
        //         	index = i;
        //         }                
        //     }
        // } 
        // String val = idToStr.get(index);        
        // return val;
        return this.idToStr.get(this.sap.ancestor(this.strToID.get(nounA), this.strToID.get(nounB)));
    }
    /**
     * Helper method to load the Synsets file and read the data.
     * @param synsets
     */
    private void parseSynsets(String synsets) {
        // File file = new File("D:\\Study\\MSIT\\Algorithms and Data Structures - 2\\ADS-2_2019501007\\Day - 1\\Word Net\\" + synsets + ".txt");
        // BufferedReader read = new BufferedReader(new FileReader(file));
        if (synsets == null) {
            throw new IllegalArgumentException();
        }
        In scan = new In(synsets);
        while (scan.hasNextLine()) {
            String[] ID = scan.readLine().split(",");
            if (ID.length < 2) {
                continue;
            }
            String[] words = ID[1].split("\\s++");
            for (String s : words) {
                idToStr.put(Integer.parseInt(ID[0]), ID[1]);
                if (!strToID.containsKey(s)) {
                    strToID.put(s, new HashSet<Integer>());
                }
            }
        }
        // scan.close();
    }
    /**
     * Helper Method to load the Hypernyms and read the data.
     * @param hypernyms
     */
    private void parseHypernyms(String hypernyms) {
        // File file = new File("D:\\Study\\MSIT\\Algorithms and Data Structures - 2\\ADS-2_2019501007\\Day - 1\\Word Net\\" + hypernyms + ".txt");
        // BufferedReader read = new BufferedReader(new FileReader(file));
        if (hypernyms == null) {
            throw new IllegalArgumentException();
        }
        In scan = new In(hypernyms);
        while (scan.hasNextLine()) {
            String[] ID = scan.readLine().split(",");
            if (ID.length < 2) {
                continue;
            }
            for (int i = 1; i < ID.length; i++) {
                this.graph.addEdge(Integer.parseInt(ID[0]), Integer.parseInt(ID[i]));
            }
        }
        // scan.close();

        if (hasCycle()) {
            throw new IllegalArgumentException("Given graph is not a DAG.");
        }
    }
    /**
     * Private method to check whether the graph has a Cycle or Not.
     * @return True if the graph has a cycle or else false.
     */
    private boolean hasCycle() {
        ArrayList<Integer> rootArr = new ArrayList<Integer>();
        for (int i = 0; i < graph.V(); i++) {
            if (!graph.adj(i).iterator().hasNext()) {
                rootArr.add(i);
            }
        }

        if (rootArr.isEmpty() || rootArr.size() > 1) {
            return true;
        }
        DirectedCycle diCycle = new DirectedCycle(graph);
        return diCycle.hasCycle();        
    }
    public static void main(String[] args) {
        // WordNet net = new WordNet("synsets", "hypernyms");
    }
}