package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class NeuralNetwork {
    private int layer;
    private int[] nodes;
    private Map <Edge, Double> weights;

    public NeuralNetwork(int layers, int[] nodesInEachLayer) {
        this.layer = layers;
        this.nodes = nodesInEachLayer;
        this.weights = new HashMap<>();

        // weights
        initializeWeights();
    }

    private void initializeWeights() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < layer - 1; i++) {
            for (int node1 = 0; node1 < nodes[i]; node1++) {
                for (int node2 = 0; node2 < nodes[i + 1]; node2++) {
                    System.out.print("Enter weight for edge (" + i + ", " + node1 + ") to (" + (i + 1) + ", " + node2 + "): ");
                    double weight = scanner.nextDouble();
                    weights.put(new Edge(i, node1, i + 1, node2), weight);
                }
            }
        }
    }

    public double getWeight(int layerFrom, int nodeFrom, int layerTo, int nodeTo) {
        Edge edge = new Edge(layerFrom, nodeFrom, layerTo, nodeTo);
        return weights.getOrDefault(edge, 0.0);
    }
}

class Edge {
    private int layerFrom, nodeFrom, layerTo, nodeTo;

    public Edge(int layerFrom, int nodeFrom, int layerTo, int nodeTo) {
        this.layerFrom = layerFrom;
        this.nodeFrom = nodeFrom;
        this.layerTo = layerTo;
        this.nodeTo = nodeTo;
    }

    @Override
    public int hashCode() {
        return (layerFrom * 31 + nodeFrom) * 31 + (layerTo * 31 + nodeTo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return layerFrom == edge.layerFrom && nodeFrom == edge.nodeFrom && layerTo == edge.layerTo && nodeTo == edge.nodeTo;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // input
        System.out.print("Enter the number of layers: ");
        int layers = scanner.nextInt();

        int[] nodesInEachLayer = new int[layers];
        for (int i = 0; i < layers; i++) {
            System.out.print("Enter the number of nodes in layer " + i + ": ");
            nodesInEachLayer[i] = scanner.nextInt();
        }

        // object
        NeuralNetwork neuralNetwork = new NeuralNetwork(layers, nodesInEachLayer);

        // weight for edge
        System.out.print("Enter layer from: ");
        int layerFrom = scanner.nextInt();
        System.out.print("Enter node from: ");
        int nodeFrom = scanner.nextInt();
        System.out.print("Enter layer to: ");
        int layerTo = scanner.nextInt();
        System.out.print("Enter node to: ");
        int nodeTo = scanner.nextInt();

        double weight = neuralNetwork.getWeight(layerFrom, nodeFrom, layerTo, nodeTo);
        System.out.println("Weight for edge (" + layerFrom + ", " + nodeFrom + ") to (" + layerTo + ", " + nodeTo + "): " + weight);
    }
}
