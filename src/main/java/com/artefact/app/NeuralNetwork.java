package com.artefact.app;

import java.util.ArrayList;

class NeuralNetwork {

    NeuralNetwork(int numberOfLayer, int height, int width) {

        byte neuronSensitivity = 0;
        byte neuronCharge = -128;
        byte neuronStrengths = 127;


        int totalNeuronNumber = 0;
        int totalSynapseNumber = 0;
        ArrayList<ArrayList<ArrayList<Neuron>>> matrix = new ArrayList<>();
        ArrayList<Layer> layers = new ArrayList<>();
        int inputSize = height * width;
        Layer lastLayer = null;


        int i = 0;
        while (i < numberOfLayer) {
            int j = 0;
            ArrayList<ArrayList<Neuron>> column = new ArrayList<>();
            matrix.add(column);

            while (j < height) {
                int k = 0;
                ArrayList<Neuron> line = new ArrayList<>();
                column.add(line);

                while (k < width) {
                    Neuron neuron = new Neuron(neuronCharge, neuronSensitivity, neuronStrengths);
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
}
