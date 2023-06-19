package basic;

import java.util.*;

public class Dijkstra {
    public static int N, edgeCount, startPoint, endPoint, d[];
    public static List<List<Edge>> graph;
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        N = scn.nextInt(); // node count
        d = new int[N+1];
        edgeCount = scn.nextInt(); // edge count
        graph = new ArrayList<>();
        for(int i = 0; i<N+1; i++) {
            graph.add(new ArrayList<>()); // graph initialization
        }
        for(int i = 0; i<edgeCount; i++) {
            int start = scn.nextInt();
            int end = scn.nextInt();
            int weight = scn.nextInt();
            graph.get(start).add(new Edge(end, weight));
        }
        startPoint = scn.nextInt();
        endPoint = scn.nextInt();
        dijkstra();
        System.out.println(d[endPoint]);
    }
    public static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        Arrays.fill(d, Integer.MAX_VALUE);
        pq.offer(new Edge(startPoint, 0));
        while(!pq.isEmpty()) {
            Edge current = pq.poll();
            int des = current.destination;
            int dis = current.weight;
            if(dis > d[des]) {
                continue;
            }
            d[des] = dis;
            for(Edge next : graph.get(des)) {
                if(d[next.destination] > d[des] + next.weight) {
                    d[next.destination] = d[des] + next.weight;
                    pq.offer(new Edge(next.destination, d[next.destination]));
                }
            }
        }
    }
}
class Edge{
    public int destination;
    public int weight;
    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

}
