import java.util.*;

public class GraphAdjacencyList{

    static class Edge{
        int src;
        int dest;
        //for weighted graph
        int wt;

        public Edge(int s,int d,int w){
            this.src=s;
            this.dest=d;
            this.wt=w;
        }
    }


    public static void createGraph(ArrayList<Edge> graph[]){
        
        for(int i=0;i<graph.length;i++){
            //creating EMPTY ArrayList 
            //replacing null values
            //if we try to add edges in null values,
            //it will throw an error
            graph[i]=new ArrayList<Edge>();
        }

        //adding edges
        graph[0].add(new Edge(0,2,2));

        graph[1].add(new Edge(1,2,10));
        graph[1].add(new Edge(1,3,0));

        graph[2].add(new Edge(2,0,2));
        graph[2].add(new Edge(2,1,10));
        graph[2].add(new Edge(2,3,-1));

        graph[3].add(new Edge(3,1,0));
        graph[3].add(new Edge(3,2,-1));
    }
    public static void main(String[] args){

        int V=4;

        ArrayList<Edge> graph[]=new ArrayList[V];
        createGraph(graph);

        //print 2's neighbour's
        for(int i=0;i<graph[2].size();i++){
            Edge e=graph[2].get(i);
            System.out.println(e.dest+" , "+e.wt);
        }

    }
}