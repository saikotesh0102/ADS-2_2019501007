/**
 * @author SaiKotesh0102
 */
/**
 * Outcast class is used to identify the Noun which is least related to others.
 */
public class Outcast {

    private final WordNet wordNet;
    /**
     * Constructor that takes WordNet as parameter
     * @param wordnet
     */
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;        
    }
    /**
     * Method to compute the sum of the distances between each noun and every other one
     * @param nouns
     * @return Noun which distance is maximum so that it will not contribute to the sum
     */
    public String outcast(String[] nouns) {
        int result = -1;
        int maxDis = -1;
        int[] dis = new int[nouns.length];
        for (int i = 0; i < nouns.length; i++) {
            for (int j = i+1; j < nouns.length; j++) {
            	int curDis = wordNet.distance(nouns[i], nouns[j]);
                dis[i] += curDis;
                dis[j] += curDis;
            }            
        }
        for (int i = 0; i < dis.length; i++) {
        	if (dis[i] > maxDis) {
        		maxDis = dis[i];
        		result = i;
        	}
        }
        return nouns[result];
    }
    public static void main(String[] args) {
        // WordNet wordnet = new WordNet(args[0], args[1]);
        // Outcast outcast = new Outcast(wordnet);
    }
}