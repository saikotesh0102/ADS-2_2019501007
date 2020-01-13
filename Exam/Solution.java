import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

public class Solution{
    private HashMap<Integer,String> emailID;
    private HashMap<String,Integer> emailCount;
    private Digraph graph;

    public Solution(String emails, String logs) throws Exception{
        this.emailID = new HashMap<Integer,String>();
        this.emailCount = new HashMap<String,Integer>();

        In emailsIO = new In(emails + ".txt");
        In logsIO = new In(logs + ".txt");

        while(emailsIO.hasNextLine()){
            String[] line = emailsIO.readLine().split(";");
            if (line.length < 2) continue;
            int index = Integer.parseInt(line[0]);
            // String email = line[1];
            emailID.put(index,line[1]);
        }
        // System.out.println(emailID.size());
        // System.out.println(emailCount.size());
        this.graph = new Digraph(emailID.size());

        while(logsIO.hasNextLine()){
            String[] line = logsIO.readLine().split(",");
            if (line.length < 2) continue;

            String[] from = line[0].split(" ");
            String[] to = line[1].split(" ");
            int fromIndex = Integer.parseInt(from[1]);
            int toIndex = Integer.parseInt(to[2]);
            // emailCount.put(toIndex, Arrays.asList(fromIndex));
            for (int i = 1; i < line.length; i++) {
                this.graph.addEdge(fromIndex, toIndex);
            }
            emailCount.put(emailID.get(toIndex), graph.indegree(toIndex));
        }
        // System.out.println(graph.E());

        // System.out.println(emailID.get(179170) + ", " + emailCount.get(emailID.get(179170)));
        // System.out.println(emailID.get(422) + ", " + emailCount.get(emailID.get(422)));
        // System.out.println(emailID.get(30) + ", " + emailCount.get(emailID.get(30)));
        // System.out.println(emailID.get(72) + ", " + emailCount.get(emailID.get(72)));
        // System.out.println(emailID.get(298) + ", " + emailCount.get(emailID.get(298)));
        // System.out.println(emailID.get(485) + ", " + emailCount.get(emailID.get(485)));
        // System.out.println(emailID.get(83) + ", " + emailCount.get(emailID.get(83)));
        // System.out.println(emailID.get(366) + ", " + emailCount.get(emailID.get(366)));
        // System.out.println(emailID.get(70524) + ", " + emailCount.get(emailID.get(70524)));
        // System.out.println(emailID.get(994) + ", " + emailCount.get(emailID.get(994)));
    }

    // public int compareTo(emailcount<Integer,Integer> e1, emailCount<Integer,Integer> e2){
    //     int value1 = e1.get();
    //     int value2 = e2.get();
    // }

    public HashMap<String,Integer> sortHashMap(HashMap<String,Integer> hm){
        List<String> mapKeys = new ArrayList<>(hm.keySet());
        List<Integer> mapValues = new ArrayList<>(hm.values());

        HashMap<String, Integer> sortedMap = new HashMap<>();
        Iterator<Integer> valueIt = mapValues.iterator();

        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();
    
            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = hm.get(key);
                Integer comp2 = val;
    
                if (comp1 == comp2) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        // System.out.println(sortedMap.keySet());
        return sortedMap;
    }

    public static void main(String[] args) throws Exception{
        Solution obj = new Solution("emails", "logs");
        obj.sortHashMap(obj.emailCount);
    }
}