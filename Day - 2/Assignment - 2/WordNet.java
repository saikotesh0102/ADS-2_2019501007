import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class WordNet {
    ArrayList<String> synID;
    ArrayList<String> Synsets;
    ArrayList<String> Hypernyms;
    ArrayList<String> edges;
    DiGraph vertices;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) throws Exception{
        this.Synsets = new ArrayList<String>();
        this.Hypernyms = new ArrayList<String>();
        this.synID = new ArrayList<String>();
        this.edges = new ArrayList<String>();
        this.parseSynsets(synsets);
        // System.out.println(synID.size());
        this.vertices = new DiGraph(synID.size());
        this.parseHypernyms(hypernyms);
        //System.out.println(vertices.size());
        int count = 0;
        for (int i = 0; i < vertices.size(); i++) {
            count = count + vertices.adj[i].size();
        }
        System.out.println(count);
    }
 
    // returns all WordNet nouns
    public Iterable<String> nouns(){
        return null;
    }
 
    // is the word a WordNet noun?
    public boolean isNoun(String word){
        return false;
    }
 
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB){
        return 0;
    }
 
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB){
        return null;
    }

    private void parseSynsets(String synsets) throws Exception{
        BufferedReader read = new BufferedReader(new FileReader(synsets + ".txt"));

        String str; 
        while ((str = read.readLine()) != null){
            String[] ID = str.split(",");
            if(ID[0] != null){
                synID.add(ID[0]);
            }

            if(ID[1] != null){
                Synsets.add(ID[1]);
            }
        }
        // for (int i = 0; i < synID.size(); i++) {
        //     System.out.println(synID.get(i));
        // }
        read.close();
    }

    private void parseHypernyms(String hypernyms) throws Exception{
        BufferedReader read = new BufferedReader(new FileReader(hypernyms + ".txt"));

        String str;
        int count = 0;
        while ((str = read.readLine()) != null){
            String[] ID = str.split(",");
            // if(ID.length > 1){
            //     for(int i = 1; i < ID.length; i++){
            //         Hypernyms.add(ID[i]);
            //     }
            // }
            Hypernyms.add(ID[0]);
            for (int i = 1; i < ID.length; i++) {
                vertices.addEdge(Integer.parseInt(ID[count]), Integer.parseInt(ID[i]));
            }

            for (int i = 1; i < ID.length; i++) {
                edges.add(ID[i]);
            }
        }
        // for (int i = 0; i < Hypernyms.size(); i++) {
        //     System.out.println(Hypernyms.get(i));
        // }
        // System.out.println(Hypernyms.size());
        read.close();
    }
 
    // do unit testing of this class
    public static void main(String[] args) throws Exception{
        WordNet net = new WordNet("synsets", "hypernyms");
        // net.parseSynsets("synsets");
        // net.parseHypernyms("hypernyms");
    }
}