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
        if (!((v >= 0 && v <= graph.V() - 1) && (w >= 0 && w <= graph.V() - 1))) {
            throw new IllegalArgumentException();
        }

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
        if (!((v >= 0 && v <= graph.V() - 1) && (w >= 0 && w <= graph.V() - 1))) {
            throw new IllegalArgumentException();
        }
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
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }
        if (!isValid(v, w)) {
            throw new IllegalArgumentException();
        }
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
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }
        if (!isValid(v, w)) {
            throw new IllegalArgumentException();
        }
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

    private boolean isValid(int v) {
        return (v >= 0 && v <= this.graph.V() - 1);
    }

    private boolean isValid(Iterable<Integer> v, Iterable<Integer> w) {
        for (Integer integer : w) {
            if (!isValid(integer)) {
                return false;
            }
        }

        for (Integer integer : v) {
            if (!isValid(integer)) {
                return false;
            }
        }
        return true;
    }
 
    // do unit testing of this class
    public static void main(String[] args) {
        // In input = new In("digraph1.txt");
        // Digraph graph = new Digraph(input);
    }
}