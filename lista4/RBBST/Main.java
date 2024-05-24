import java.util.List;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <data_type>");
            System.exit(1);
        }

        RedBlackBinarySearchTree bst = new RedBlackBinarySearchTree();
        String dataType = args[0];
        if (dataType.equals("asc")) {
            List<Integer> keysToAdd = Generator.ascendingKeys(30);
            for (Integer key : keysToAdd) {
                bst.add(key);
                bst.printTree();
            }

            List<Integer> keysToDelete = Generator.randomKeys(30);
            for (Integer key : keysToDelete) {
                bst.delete(key);
                bst.printTree();
            }
        }
        else if (dataType.equals("rand")) {
            List<Integer> keysToAdd = Generator.randomKeys(30);
            for (Integer key : keysToAdd) {
                bst.add(key);
                bst.printTree();
            }

            List<Integer> keysToDelete = Generator.randomKeys(30);
            for (Integer key : keysToDelete) {
                bst.delete(key);
                bst.printTree();
            }
        }
        else {
            System.err.println("data type must be either asc or rand");
            System.exit(1);
        }
    }
}
