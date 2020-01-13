/**
 * @author SaiKotesh0102
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    private HashMap<Integer, String> IDtoStr;
    private HashMap<String, Set<Integer>> StrtoID;
    Digraph graph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) throws Exception{
        this.IDtoStr = new HashMap<Integer,String>();
        this.StrtoID = new HashMap<String,Set<Integer>>();
        this.parseSynsets(synsets);
        this.graph = new Digraph(IDtoStr.size());
        this.parseHypernyms(hypernyms);
    }
 
    // returns all WordNet nouns
    public Iterable<String> nouns(){
        return this.StrtoID.keySet();
    }
 
    // is the word a WordNet noun?
    public boolean isNoun(String word){
        return this.StrtoID.containsKey(word);
    }
 
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB){
        if (!(StrtoID.containsKey(nounA) && StrtoID.containsKey(nounB))) {
            throw new IllegalArgumentException("Argument should be in WordNet.");
        }
        Iterable<Integer> itA = StrtoID.get(nounA);
        Iterable<Integer> itB = StrtoID.get(nounB);
        BreadthFirstDirectedPaths g1 = null, g2 = null;
        g1 = new BreadthFirstDirectedPaths(this.graph, itA);
        g2 = new BreadthFirstDirectedPaths(this.graph, itB);
                
        int result = Integer.MAX_VALUE;
        for (int i : IDtoStr.keySet()) {
            if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
                int cur = g1.distTo(i) + g2.distTo(i);
                result = Math.min(result, cur);
            }
        }
        return result;
    }
 
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB){
        return null;
    }

    private void parseSynsets(String synsets) throws Exception{
        BufferedReader read = new BufferedReader(new FileReader("D:\\Study\\MSIT\\Algorithms and Data Structures - 2\\ADS-2_2019501007\\Day - 1\\Word Net\\synsets.txt"));

        String str; 
        while ((str = read.readLine()) != null){
            String[] ID = str.split(",");
            if(ID.length < 2){
                continue;
            }
            int index = Integer.parseInt(ID[0]);
            String[] words = ID[1].split("\\s++");
            for(String s : words){
                IDtoStr.put(index, ID[1]);
                if(!StrtoID.containsKey(s)){
                    StrtoID.put(s,new HashSet<Integer>());
                }
            }
        }
        read.close();
    }

    private void parseHypernyms(String hypernyms) throws Exception{
        BufferedReader read = new BufferedReader(new FileReader("D:\\Study\\MSIT\\Algorithms and Data Structures - 2\\ADS-2_2019501007\\Day - 1\\Word Net\\hypernyms.txt"));
        String str;
        while ((str = read.readLine()) != null){
            String[] ID = str.split(",");
            if(ID.length < 2){
                continue;
            }
            int index = Integer.parseInt(ID[0]);
            for (int i = 1; i < ID.length; i++) {
                this.graph.addEdge(index,Integer.parseInt(ID[i]));
            }
        }
        read.close();
    }
    public static void main(String[] args) throws Exception{
        WordNet net = new WordNet("synsets", "hypernyms");
    }
}