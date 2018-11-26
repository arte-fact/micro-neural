package com.artefact.app;

import java.util.ArrayList;

class NeuralNetwork {
    private ArrayList<Layer> layers;

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    NeuralNetwork(int numberOfLayer, int height, int width) {

        byte neuronCharge = 0;
        byte sensibility = 100;

        int totalNeuronNumber = 0;
        int totalSynapseNumber = 0;
        layers = new ArrayList();
        int inputSize = height * width;
        Layer lastLayer = null;


        int i = 0;
        while (i < numberOfLayer + 1) {
            int j = 0;
            ArrayList<ArrayList<Neuron>> matrix = new ArrayList();

            while (j < height) {
                int k = 0;
                ArrayList<Neuron> line = new ArrayList();
                matrix.add(line);

                while (k < width) {
                    Neuron neuron = new Neuron(neuronCharge, sensibility);
                    line.add(neuron);
                    k++;
                    totalNeuronNumber++;
                }
                j++;
            }

            Layer layer = new Layer(matrix);

            if (lastLayer != null){
                lastLayer.setChild(layer);
                totalSynapseNumber += lastLayer.generateSynapses(layer);
            }

            lastLayer = layer;
            layers.add(layer);

            height /= 2;
            width /= 2;

            i++;
        }
        System.out.printf("%d neurons generated%n", totalNeuronNumber);
        System.out.printf("%d synapses generated%n", totalSynapseNumber);
        System.out.printf("Input size: %d neurons %n", inputSize);
        System.out.printf("Output size: %d neurons %n", width * height);
    }

    public byte burst () {
        byte charge = 0;
        for (ArrayList<Neuron> line: layers.get(0).getMatrix()) {
            for (Neuron neuron: line) {
                neuron.setCharge(charge);
                charge += 8;
            }
        }

        for (Layer layer: layers) {
            for (ArrayList<Neuron> line: layer.getMatrix()) {
                for (Neuron neuron: line) {
                    neuron.burst();
                }
            }
            System.out.printf("%n");
        }
        return (byte) 0;
    }
}
