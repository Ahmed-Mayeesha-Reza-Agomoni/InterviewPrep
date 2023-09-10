import java.util.*;

public class GraphAlgos{

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
 
            graph[i]=new ArrayList<Edge>();
        }

        //adding edges
        // graph[0].add(new Edge(0,1));
        // graph[0].add(new Edge(0,2));

        // graph[1].add(new Edge(1,0));
        // graph[1].add(new Edge(1,3));

        // graph[2].add(new Edge(2,0));
        // graph[2].add(new Edge(2,4));

        // graph[3].add(new Edge(3,1));
        // graph[3].add(new Edge(3,4));
        // graph[3].add(new Edge(3,5));

        // graph[4].add(new Edge(4,2));
        // graph[4].add(new Edge(4,3));
        // graph[4].add(new Edge(4,5));

        // graph[5].add(new Edge(5,3));
        // graph[5].add(new Edge(5,4));
        // graph[5].add(new Edge(5,6));

        // graph[6].add(new Edge(6,5));

        //directed graph
        // graph[0].add(new Edge(0, 2));

        // graph[1].add(new Edge(1, 0));

        // graph[2].add(new Edge(2, 3));

        // graph[3].add(new Edge(3, 0));

        //topological sort graph
        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 1));

        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));

        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));

    }

    public static void bfs(ArrayList<Edge> graph[],int V,boolean vis[],int start){
        //O(V+En )
        Queue<Integer> q=new LinkedList<>();
        //boolean vis[]=new boolean[V];
        //initialisation
        q.add(start);//source

        while(!q.isEmpty()){
            int current=q.remove();

            if(vis[current]==false){
                System.out.print(current+"  ");
                vis[current]=true;

                //add all neighbours of curr to q
                for(int i=0;i<graph[current].size();i++){
                    Edge e=graph[current].get(i);
                    q.add(e.dest);
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge> graph[],int current,boolean vis[]){

        System.out.print(current+" ");
        vis[current]=true;

        for(int i=0;i<graph[current].size();i++){
            Edge e=graph[current].get(i);
            
            if(vis[e.dest]==false){
                dfs(graph,e.dest,vis);
            }
        }
    }

    public static void topSortUtil(ArrayList<Edge> graph[],int current,boolean vis[],Stack<Integer> stack){

        //System.out.print(current+" ");
        vis[current]=true;

        for(int i=0;i<graph[current].size();i++){
            Edge e=graph[current].get(i);
            
            if(vis[e.dest]==false){
                topSortUtil(graph, e.dest, vis, stack);
            }
        }

        //push the current at last after visiting its neighbors
        stack.push(current);
    }

    public static void topologicalSort(ArrayList<Edge> graph[],int V){

        boolean vis[]=new boolean[V];
        Stack<Integer> stack=new Stack<>();
        //for disconnected graphs
        for(int i=0;i<V;i++){
            if(vis[i]==false){
                //bfs(graph,V,vis,i);
                topSortUtil(graph, i, vis, stack);
            }
        }

        while(stack.isEmpty()==false){
            System.out.print(stack.pop()+" ");
        }

        System.out.println();
    }

    public static void printAllPath(ArrayList<Edge> graph[],boolean vis[],int curr,String path, int tar){
        //O(V^V)
        if(curr==tar){
            System.out.println(path);
            return;
        }

        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);

            if(!vis[e.dest]){
                vis[curr]=true;
                printAllPath(graph, vis, e.dest, path+e.dest, tar);
                vis[curr]=false;
            }
        }
    }

    public static boolean isCycleDirected(ArrayList<Edge> graph[], boolean vis[], int curr, boolean rec[]){
        //O(E+V)
        vis[curr]=true;
        rec[curr]=true;

        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);

            if(rec[e.dest]){
                //cycle
                return true;
            }
            else if(!vis[e.dest]){
                if (isCycleDirected(graph, vis, e.dest, rec)){
                    return true;
                }
            }
        }

        rec[curr]=false;
        return false;
    }

    public static boolean isCycleUndirected(ArrayList<Edge> graph[], boolean vis[], int curr, int parent){
        vis[curr]=true;

        for(int i=0;i<graph[curr].size();i++){

            Edge e=graph[curr].get(i);

            if(vis[e.dest]==true && e.dest!=parent){
                return true;
            }

            else if(vis[e.dest]==false){
                if(isCycleUndirected(graph, vis, e.dest, curr)){
                    return true;
                }
            }
        }

        return false;
    }



    public static class Pair implements Comparable<Pair>{
        int node;
        int dist;

        public Pair(int n, int d){
            this.node=n;
            this.dist=d;
        }

        @Override
        public int compareTo(Pair p2){
            return this.dist-p2.dist;//ascending
        }
    }

    public static void dijkstra(ArrayList<Edge> graph[],int src,int V){
        //O(E+ElogV)
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        int dist[]=new int[V];

        for(int i=0;i<V;i++){
            if(i!=src){
                //initialize with infinity
                dist[i]=Integer.MAX_VALUE;
            }
        }

        boolean vis[]=new boolean[V];

        //add src
        pq.add(new Pair(0,0));

        while(pq.isEmpty()==false){

            Pair curr=pq.remove();//shortest

            if(vis[curr.node]==false){
                vis[curr.node]=true;

                for(int i=0;i<graph[curr.node].size();i++){

                    Edge e=graph[curr.node].get(i);

                    int u=e.src;
                    int v=e.dest;

                    //relaxation
                    if(dist[u]+e.wt<dist[v]){
                        dist[v]=dist[u]+e.wt;
                        pq.add(new Pair(v,dist[v]));
                    }
                }
            }
        }

        for(int i=0;i<V;i++){
            System.out.print(dist[i]+" ");
        }

        System.out.println();

    }

    public static void bellmanFord(ArrayList<Edge> graph[],int src,int V){
        //O(E.V)

        int dist[]=new int[V];

        for(int i=0;i<V;i++){
            if(i!=src){
                //initialize with infinity
                dist[i]=Integer.MAX_VALUE;
            }
        }
        
        //bellmanFord runs V-1 times->O(V)
        for(int k=0;k<V-1;k++){
            //O(E)
            for(int i=0;i<V;i++){
                for(int j=0;j<graph[i].size();j++){

                    Edge e=graph[i].get(j);
                    int u=e.src;
                    int v=e.dest;

                    if(dist[u]!=Integer.MAX_VALUE &&
                    dist[u]+e.wt<dist[v]){

                        dist[v]=dist[u]+e.wt;
                    }
                }
            }
        }

        for(int i=0;i<V;i++){
                for(int j=0;j<graph[i].size();j++){

                    Edge e=graph[i].get(j);
                    int u=e.src;
                    int v=e.dest;

                    if(dist[u]!=Integer.MAX_VALUE &&
                    dist[u]+e.wt<dist[v]){

                        //dist[v]=dist[u]+e.wt;
                        System.out.println("There is a negative edge weight cycle");
                    }
                }
            }

        for(int i=0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }

        System.out.println();
    }

    public static void prims(ArrayList<Edge> graph[], int V){
        //O(ElogE)
        PriorityQueue<Pair> pq=new PriorityQueue<>();//non-mst
        boolean vis[]=new boolean[V];//mst


        pq.add(new Pair(0,0));
        int mstCost=0;

        while(pq.isEmpty()==false){

            Pair current=pq.remove();

            if(vis[current]==false){
                vis[current]=true;
                mstCost+=current.dist;

                for(int i=0;i<graph[current].size();i++){

                    Edge e=graph[current.node].get(i);

                    if(vis[e.dest]==false){
                        pq.add(new Pair(e.dest,e.wt));
                    }
                }
            }
        }

        System.out.println("The minimum cost is : ", mstCost);
       }

    public static void main(String[] args){

        int V=6;

        /*
          1------3
         /       | \
         0       |  5 -- 6
         \       | /
          2 -----4
         */

        ArrayList<Edge> graph[]=new ArrayList[V];
        createGraph(graph);

        //print 2's neighbour's
        // for(int i=0;i<graph[2].size();i++){
        //     Edge e=graph[2].get(i);
        //     System.out.println(e.dest+" , "+e.wt);
        // }
        
        // boolean vis[]=new boolean[V];
        // //for disconnected graphs
        // for(int i=0;i<V;i++){
        //     if(vis[i]==false){
        //         //bfs(graph,V,vis,i);
        //         dfs(graph,i,vis);
        //     }
        // }

        // System.out.println();

        // int src=0,tar=5;
        // printAllPath(graph,new boolean[V], src, "0", tar);

        // boolean vis[]=new boolean[V];
        // boolean rec[]=new boolean[V];

        // for(int i=0;i<V;i++){
        //     if(!vis[i]){
        //         boolean isCycle=isCycleDirected(graph, vis, i, rec);
        //         if (isCycle){
        //             System.out.println(isCycle);
        //             break;
        //         }
        //     }
        // }

        topologicalSort(graph, V);

    }
}