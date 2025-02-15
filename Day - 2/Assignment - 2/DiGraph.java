import java.util.*;
import java.lang.*;

public class DiGraph{
    public final int V;
    public final Bag<Integer>[] adj;

    public DiGraph(int V){
        this.V = V;
        adj = (Bag <Integer>[]) new Bag[V];
        
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer> ();
        }
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public int size(){
        return adj.length;
    }
}