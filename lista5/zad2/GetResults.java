import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GetResults {
    public static void main(String[] args) {
        for (int n = 10; n < 1001; n += 10) {
            System.out.println(n);
            Graph graph = new Graph(n);

            PrimsMST primMST = new PrimsMST(graph);

            int[][] primMatrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (Edge edge : primMST.edges()) {
                    int source = edge.getSource();
                    int destination = edge.getDestination();
                    primMatrix[source][destination] = 1;
                    primMatrix[destination][source] = 1;
                }
            }

            Random random = new Random();

            for (int root = 0; root < 10; root++) {
                int randomroot = random.nextInt(n);
                int[] rounds = RoundMinimizer.minimizeRounds(primMatrix, n, randomroot);
                int maxRound = 0;
                for (int i = 0; i < n; i++) {
                    if (rounds[i] > maxRound) maxRound = rounds[i];
                }
                try (FileWriter writer = new FileWriter("mst_results.txt", true)) {
                    writer.write(n + " " + maxRound + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
