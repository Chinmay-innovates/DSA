package java_practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Classroom {
    static class Edge {
        int src, dest, wt;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));

        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));

        // graph[0].add(new Edge(0, 1, 4));
        // graph[0].add(new Edge(0, 7, 8));

        // graph[1].add(new Edge(1, 2, 8));
        // graph[1].add(new Edge(1, 7, 11));

        // graph[2].add(new Edge(2, 3, 7));
        // graph[2].add(new Edge(2, 8, 2));
        // graph[2].add(new Edge(2, 5, 4));

        // graph[3].add(new Edge(3, 5, 14));
        // graph[3].add(new Edge(3, 4, 9));

        // graph[4].add(new Edge(3, 4, 9));
        // graph[4].add(new Edge(3, 4, 9));

        // graph[5].add(new Edge(5, 4, 10));
        // graph[5].add(new Edge(5, 6, 2));

        // graph[6].add(new Edge(6, 7, 1));
        // graph[6].add(new Edge(6, 8, 6));

        // graph[7].add(new Edge(7, 8, 7));

    }

    public static void bfs(ArrayList<Edge>[] graph, int V, boolean[] visited) {
        java.util.Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!visited[curr]) {
                visited[curr] = true;
                System.out.print(curr + " ");

                for (Edge e : graph[curr]) {
                    q.add(e.dest);
                }

            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited) {
        System.out.print(curr + " ");
        visited[curr] = true;

        for (Edge e : graph[curr]) {
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, boolean[] visited, int curr, String path, int tar) {
        if (curr == tar) {
            System.out.println(path);
            return;
        }
        for (Edge e : graph[curr]) {
            if (!visited[e.dest]) {
                visited[curr] = true;
                printAllPaths(graph, visited, e.dest, path + e.dest, tar);
                visited[curr] = false;
            }
        }
    }

    public static boolean isCycleDirected(ArrayList<Edge>[] graph, int curr, boolean[] visited, boolean[] recStack) {
        visited[curr] = true;
        recStack[curr] = true;
        for (Edge e : graph[curr]) {
            if (recStack[e.dest]) {
                return true;
            } else if (!visited[e.dest] &&
                    isCycleDirected(graph, e.dest, visited, recStack)) {
                return true;
            }
        }
        recStack[curr] = false;
        return false;
    }

    public static boolean isCycleUndirected(ArrayList<Edge>[] graph, int curr, boolean[] visited, int parent) {
        visited[curr] = true;

        for (Edge e : graph[curr]) {
            if (visited[e.dest] && e.dest != parent)
                return true;
            else if (!visited[e.dest] && isCycleUndirected(graph, e.dest, visited, parent))
                return true;
        }
        return false;
    }

    public static void topSortUtil(ArrayList<Edge>[] graph, int curr, boolean[] visited, Stack<Integer> stack) {
        visited[curr] = true;
        for (Edge e : graph[curr]) {
            if (!visited[e.dest]) {
                topSortUtil(graph, e.dest, visited, stack);
            }
        }
        stack.push(curr);
    }

    public static void topSort(ArrayList<Edge>[] graph, int V) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topSortUtil(graph, i, visited, stack);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static class Pair implements Comparable<Pair> {
        int node, dist;

        public Pair(int n, int d) {
            this.node = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist; // asc
        }
    }

    public static class MstPair implements Comparable<MstPair> {
        int node, cost, parent;

        public MstPair(int n, int d, int p) {
            this.node = n;
            this.cost = d;
            this.parent = p;
        }

        @Override
        public int compareTo(MstPair p2) {
            return this.cost - p2.cost; // Min-Heap (ascending order)
        }
    }

    // O(E + ElogV)
    // ElogV priority queue (sorting) -> nlogN;
    // E -> traversing all edges once
    public static void dijkstra(ArrayList<Edge>[] graph, int src, int V) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        boolean[] visited = new boolean[V];
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!visited[curr.node]) {
                visited[curr.node] = true;
                for (Edge e : graph[curr.node]) {
                    int u = e.src, v = e.dest;
                    if (dist[u] + e.wt < dist[v]) {
                        dist[v] = dist[u] + e.wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    public static boolean isNegativeWtCycle(ArrayList<Edge>[] graph, int V, int[] dist) {
        for (int j = 0; j < V; j++) {
            for (Edge e : graph[j]) {
                if (dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.wt < dist[e.dest]) {
                    dist[e.dest] = dist[e.src] + e.wt;
                    return true;
                }
            }
        }
        return false;
    }

    public static void bellmanFord(ArrayList<Edge>[] graph, int src, int V) {

        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        // V-1 relaxation
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < V; j++) {
                for (Edge e : graph[j]) {
                    if (dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.wt < dist[e.dest]) {
                        dist[e.dest] = dist[e.src] + e.wt;
                    }
                }
            }
        }

        // detect -ve wt cycle
        if (isNegativeWtCycle(graph, V, dist)) {
            System.out.println("Graph contains -ve wt cycle");
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    // ElogE
    public static void primsAlgo(ArrayList<Edge>[] graph, int V) {

        PriorityQueue<MstPair> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V];
        int mstCost = 0;
        ArrayList<Edge> mstEdges = new ArrayList<>();

        pq.add(new MstPair(0, 0, -1));

        while (!pq.isEmpty()) {
            MstPair curr = pq.remove();
            if (!visited[curr.node]) {
                visited[curr.node] = true;
                mstCost += curr.cost;

                if (curr.parent != -1) {
                    mstEdges.add(new Edge(curr.parent, curr.node, curr.cost));
                }

                for (Edge e : graph[curr.node]) {
                    if (!visited[e.dest]) {
                        pq.add(new MstPair(e.dest, e.wt, curr.node));
                    }
                }
            }
        }

        System.out.println("Minimum MST cost: " + mstCost);

        System.out.println("MST Edges:");
        for (Edge e : mstEdges) {
            System.out.println(e.src + " - " + e.dest + " (" + e.wt + ")");
        }
    }

    // O(V+E)
    public static void kosarajuAlgo(ArrayList<Edge>[] graph, int V) {
        // STEP 1
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topSortUtil(graph, i, visited, stack);
            }
        }

        // STEP 2
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] transpose = new ArrayList[V];
        for (int i = 0; i < graph.length; i++) {
            visited[i] = false;
            transpose[i] = new ArrayList<Edge>();
        }

        for (ArrayList<Edge> edges : graph) {
            for (Edge e : edges) {
                transpose[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        // STEP 3
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visited[curr]) {
                dfs(transpose, curr, visited);
                System.out.println();
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited, int[] dt, int[] low, int time,
            int parent) {
        visited[curr] = true;
        dt[curr] = low[curr] = ++time;

        for (Edge e : graph[curr]) {
            if (e.dest == parent)
                continue;
            else if (!visited[e.dest]) {
                dfs(graph, e.dest, visited, dt, low, time, curr);
                low[curr] = Math.min(low[curr], low[e.dest]);

                if (dt[curr] < low[e.dest]) {
                    System.out.println("Bridge : " + curr + "---" + e.dest);
                }
            } else {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            }
        }
    }

    public static void getBridge(ArrayList<Edge>[] graph, int V) {

        int[] dt = new int[V], low = new int[V];
        int time = 0;
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, dt, low, time, -1);
            }
        }
    }

    public static void tarjansAlgo(ArrayList<Edge>[] graph, int curr, boolean[] visited, boolean[] ap,
            int[] dt,
            int[] low,
            int time,
            int parent) {
        visited[curr] = true;
        dt[curr] = low[curr] = ++time;
        int children = 0;

        for (Edge e : graph[curr]) {
            int neighbor = e.dest;
            if (parent == neighbor)
                continue;
            else if (visited[neighbor]) {
                low[curr] = Math.min(low[curr], dt[neighbor]);
            } else {
                tarjansAlgo(graph, neighbor, visited, ap, dt, low, time, curr);
                low[curr] = Math.min(low[curr], low[neighbor]);

                if (dt[curr] <= low[neighbor] && parent != -1) {
                    ap[curr] = true;
                }
                children++;
            }
        }

        if (parent == -1 && children > 1) {
            ap[curr] = true;
        }
    }

    public static void getArticulationPoint(ArrayList<Edge>[] graph, int V) {
        int[] dt = new int[V], low = new int[V];
        boolean[] visited = new boolean[V], ap = new boolean[V];
        int time = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                tarjansAlgo(graph, i, visited, ap, dt, low, time, -1);
            }
        }

        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.println("AP : " + i);
            }
        }

    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int V = 6;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        getBridge(graph, V);
        getArticulationPoint(graph, V);

        bfs(graph, V, new boolean[V]);
        System.out.println("BFS");

        dfs(graph, 0, new boolean[V]);
        System.out.println("DFS");

        dijkstra(graph, 0, V);
        System.out.println("DIJKSTRA");

        bellmanFord(graph, 0, V);
        System.out.println("BELLMAN FORD");

        topSort(graph, V);
        System.out.println("TOPOLOGICAL SORTING");

        System.out.println("isCycleDirected " +
                isCycleDirected(graph, 0, new boolean[V], new boolean[V]));
        System.out.println("isCycleUndirected " +
                isCycleUndirected(graph, 0, new boolean[V], -1));

        primsAlgo(graph, V);
        System.out.println("PRIMS ALG");

        kosarajuAlgo(graph, V);
        System.out.println("SCC");
    }
}
