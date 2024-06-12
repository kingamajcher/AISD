import java.io.FileWriter;
import java.io.IOException;

public class GetResults {
    public static void main(String[] args) {
        for (int n = 10; n <= 1000; n += 10) {
            System.out.println(n);
            for (int k = 0; k < 10; k++) {
                Graph graph = new Graph(n);

                // Kruskal's algorithm
                long startTimeKruskal = System.nanoTime();
                KruskalsMST kruskalMST = new KruskalsMST(graph);
                long endTimeKruskal = System.nanoTime();
                long durationKruskal = endTimeKruskal - startTimeKruskal;

                // Prim's Algorithm
                long startTimePrim = System.nanoTime();
                PrimsMST primMST = new PrimsMST(graph);
                long endTimePrim = System.nanoTime();
                long durationPrim = endTimePrim - startTimePrim;

                try (FileWriter writer = new FileWriter("mst_results.txt", true)) {
                    writer.write(n + " " + durationKruskal + " " + durationPrim + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
