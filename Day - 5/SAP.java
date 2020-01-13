/**
 * @author SaiKotesh0102
 */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

public class SAP {
    private final Digraph graph;
    private BreadthFirstDirectedPaths g1, g2;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.graph = G;
    }
 
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        g1 = new BreadthFirstDirectedPaths(graph, v);
        g2 = new BreadthFirstDirectedPaths(graph, w);

        int vertex = graph.V();
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < vertex; i++) {
            if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
                minLength = Math.min(minLength, g1.distTo(i) + g2.distTo(i));
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        }
        return minLength;
    }
 
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        g1 = new BreadthFirstDirectedPaths(graph, v);
        g2 = new BreadthFirstDirectedPaths(graph, w);

        int vertex = graph.V();
        int minLength = Integer.MAX_VALUE;
        int result = -1;
        for (int i = 0; i < vertex; i++) {
            if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
                int cur = g1.distTo(i) + g2.distTo(i);
                if (cur < minLength) {
                    minLength = cur;
                    result = i;
                }                
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        }  
        return result;
    }
 
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        g1 = new BreadthFirstDirectedPaths(graph, v);
        g2 = new BreadthFirstDirectedPaths(graph, w);

        int vertex = graph.V();
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < vertex; i++) {
            if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
                minLength = Math.min(minLength, 
                        g1.distTo(i) + g2.distTo(i));
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        }
        return minLength; 
    }
 
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        g1 = new BreadthFirstDirectedPaths(graph, v);
        g2 = new BreadthFirstDirectedPaths(graph, w);

        int vertex = graph.V();
        int minLength = Integer.MAX_VALUE;
        int result = -1;
        for (int i = 0; i < vertex; i++) {
            if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
                int cur = g1.distTo(i) + g2.distTo(i);
                if (cur < minLength) {
                    minLength = cur;
                    result = i;
                }                
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        }     
        return result;
    }
 
    // do unit testing of this class
    public static void main(String[] args) {

    }
}