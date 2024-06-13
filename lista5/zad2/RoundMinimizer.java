import java.util.*;

public class RoundMinimizer {
    public static int[] minimizeRounds(int[][] mst, int n, int root) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mst[i][j] == 1) {
                    adjacencyList.get(i).add(j);
                }
            }
        }

        int[] rounds = new int[n];
        Arrays.fill(rounds, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        rounds[root] = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                
                List<Integer> children = adjacencyList.get(u);
                children.sort((a, b) -> adjacencyList.get(b).size() - adjacencyList.get(a).size());
                int r = rounds[u] + 1;

                for (int v : children) {
                    if (rounds[v] == -1) {
                        rounds[v] = r;
                        queue.offer(v);
                        r++;
                    }
                }
            }
        }

        return rounds;
    }
}
