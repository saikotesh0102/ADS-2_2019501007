import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class WordNet {
    ArrayList<String> synID;
    ArrayList<String> synsets;
    ArrayList<String> hypernymID;
    ArrayList<String> hypernyms;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms){
        this.synsets = new ArrayList<String>();
        this.hypernyms = new ArrayList<String>();
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
            // if(ID[0] != null){
            //     synID.add(ID[0]);
            // }
            System.out.println(Arrays.toString(ID));
        }
        read.close();
    }

    private void parseHypernyms(String hypernyms) throws Exception{
        BufferedReader read = new BufferedReader(new FileReader(hypernyms + ".txt"));

        String str; 
        while ((str = read.readLine()) != null){
            String[] ID = str.split(",");
            // if(ID[1] != null){
            //     hypernymID.add(ID[1]);
            // }
            System.out.println(Arrays.toString(ID));
        }
        read.close();
    }
 
    // do unit testing of this class
    public static void main(String[] args) throws Exception{
        WordNet net = new WordNet("synsets", "hypernyms");
        net.parseSynsets("synsets");
        net.parseHypernyms("hypernyms");
    }
}