import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NeuralNetworkSimulation {
    private List<List<List<Double>>> connectionWeights;

    public NeuralNetworkSimulation(int numLayers, int[] nodesInLayer) {
        connectionWeights = new ArrayList<>();

        for (int i = 0; i < numLayers - 1; i++) {
            List<List<Double>> layerWeights = new ArrayList<>();

            for (int j = 0; j < nodesInLayer[i]; j++) {
                List<Double> nodeWeights = new ArrayList<>();

                for (int k = 0; k < nodesInLayer[i + 1]; k++) {
                    nodeWeights.add(0.0);
                }

                layerWeights.add(nodeWeights);
            }

            connectionWeights.add(layerWeights);
        }
    }

    public void adjustWeight(int layer, int nodeFrom, int nodeTo, double weight) {
        connectionWeights.get(layer - 1).get(nodeFrom).set(nodeTo, weight);
    }

    public double retrieveWeight(int layer, int nodeFrom, int nodeTo) {
        return connectionWeights.get(layer - 1).get(nodeFrom).get(nodeTo);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Specify the number of layers: ");
        int numLayers = scanner.nextInt();
        int[] nodesInLayer = new int[numLayers];

        for (int i = 0; i < numLayers; i++) {
            System.out.print("Specify the number of nodes in layer " + i + ": ");
            nodesInLayer[i] = scanner.nextInt();
        }

        NeuralNetworkSimulation nnSimulation = new NeuralNetworkSimulation(numLayers, nodesInLayer);
        System.out.println("Provide the weights for each connection:");

        for (int i = 1; i < numLayers; i++) {
            for (int j = 0; j < nodesInLayer[i - 1]; j++) {
                for (int k = 0; k < nodesInLayer[i]; k++) {
                    System.out.print("Enter the weight for connection from node " + j + " in layer " + (i - 1) + " to node " + k + " in layer " + i + ": ");
                    double weight = scanner.nextDouble();
                    nnSimulation.adjustWeight(i, j, k, weight);
                }
            }
        }

        System.out.println("Specify the node indices to inquire about the weight (layer, nodeFrom, nodeTo):");
        int layer = scanner.nextInt();
        int nodeFrom = scanner.nextInt();
        int nodeTo = scanner.nextInt();

        double weight = nnSimulation.retrieveWeight(layer, nodeFrom, nodeTo);
        System.out.println("The weight is: " + weight);
    }
}
