/**
 * @author SaiKotesh0102
 */

public class Outcast {

    private final WordNet wordNet;

    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;        
    }

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
}