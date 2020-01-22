/**
 * @author SaiKotesh0102
 */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
/**
 * SAP Class is used to get the Shortest path between two Synsets
 */
public class SAP {
    private final Digraph graph;
    private BreadthFirstDirectedPaths g1, g2;

    /**
     * constructor takes a digraph (not necessarily a DAG)
     */
    public SAP(Digraph G) {
        this.graph = G;
    }
 
    /**
     * length of shortest ancestral path between v and w; -1 if no such path
     * @param v
     * @param w
     * @return the distance between two Synsets; -1 if there is no Path
     */
    public int length(int v, int w) {
        if (!((v >= 0 && v <= graph.V() - 1) && (w >= 0 && w <= graph.V() - 1))) {
            throw new IllegalArgumentException();
        }

        g1 = new BreadthFirstDirectedPaths(graph, v);
        g2 = new BreadthFirstDirectedPaths(graph, w);

        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++) {
            if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
                minLength = Math.min(minLength, g1.distTo(i) + g2.distTo(i));
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        }
        return minLength;
    }
 
    /**
     * a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     * @param v
     * @param w
     * @return the shortest distance to the ancestor between two vertices, -1 is there is no path.
     */
    public int ancestor(int v, int w) {
        if (!((v >= 0 && v <= graph.V() - 1) && (w >= 0 && w <= graph.V() - 1))) {
            throw new IllegalArgumentException();
        }
        g1 = new BreadthFirstDirectedPaths(graph, v);
        g2 = new BreadthFirstDirectedPaths(graph, w);

        int minLength = Integer.MAX_VALUE;
        int result = -1;
        for (int i = 0; i < graph.V(); i++) {
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
 
    /**
     * length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     * @param v
     * @param w
     * @return the length of the shortest ancestral path.
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }
        if (!isValid(v, w)) {
            throw new IllegalArgumentException();
        }

        g1 = new BreadthFirstDirectedPaths(graph, v);
        g2 = new BreadthFirstDirectedPaths(graph, w);

        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++) {
            if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
                minLength = Math.min(minLength, g1.distTo(i) + g2.distTo(i));
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        }
        return minLength; 
    }
 
    /**
     * a common ancestor that participates in shortest ancestral path; -1 if no such path
     * @param v
     * @param w
     * @return common ancestor that participates in shortest ancestral path; -1 if no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }
        if (!isValid(v, w)) {
            throw new IllegalArgumentException();
        }
        
        g1 = new BreadthFirstDirectedPaths(graph, v);
        g2 = new BreadthFirstDirectedPaths(graph, w);

        int minLength = Integer.MAX_VALUE;
        int result = -1;
        for (int i = 0; i < graph.V(); i++) {
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
    /**
     * Helper method to check whether the given vertex is in the range
     * @param v
     * @return true if in range or false if not.
     */
    private boolean isValid(int v) {
        return (v >= 0 && v <= this.graph.V() - 1);
    }
    /**
     * Helper method to check whether the given vertex is in the range
     * @param v
     * @param w
     * @return true if in range or false if not.
     */
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
    public static void main(String[] args) {
        // In input = new In("digraph1.txt");
        // Digraph graph = new Digraph(input);
    }
}