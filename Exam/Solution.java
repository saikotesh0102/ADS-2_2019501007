import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Solution{
    private HashMap<Integer,String> emailID;
    private HashMap<Integer,Integer> emailCount;
    private Digraph graph;

    public Solution(String emails, String logs) throws Exception{
        this.emailID = new HashMap<Integer,String>();
        this.emailCount = new HashMap<Integer,Integer>();

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
            emailCount.put(toIndex, graph.indegree(toIndex));
        }

        System.out.println(emailID.get(179170) + ", " + emailCount.get(179170));
        System.out.println(emailID.get(422) + ", " + emailCount.get(422));
        System.out.println(emailID.get(30) + ", " + emailCount.get(30));
        System.out.println(emailID.get(72) + ", " + emailCount.get(72));
        System.out.println(emailID.get(298) + ", " + emailCount.get(298));
        System.out.println(emailID.get(485) + ", " + emailCount.get(485));
        System.out.println(emailID.get(83) + ", " + emailCount.get(83));
        System.out.println(emailID.get(366) + ", " + emailCount.get(366));
        System.out.println(emailID.get(70524) + ", " + emailCount.get(70524));
        System.out.println(emailID.get(994) + ", " + emailCount.get(994));
    }

    // public int compareTo(emailcount<Integer,Integer> e1, emailCount<Integer,Integer> e2){
    //     int value1 = e1.get();
    //     int value2 = e2.get();
    // }

    public static void main(String[] args) throws Exception{
        Solution obj = new Solution("emails", "logs");
    }
}