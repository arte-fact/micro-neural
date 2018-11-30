package com.artefact.app;

import java.util.ArrayList;

class Layer {
    private ArrayList<Neuron> neurons;

    Layer(ArrayList<Neuron> neurons) {
        this.neurons = neurons;
    }

    public ArrayList<Neuron> getNeurons() {
        return neurons;
    }

    int generateSynapses(Layer parentLayer) {
        int numberOfSynapses = 0;
        for (Neuron neuron: parentLayer.getNeurons()) {
            for (Neuron parentNeuron: this.getNeurons()) {
                parentNeuron.connectChild(neuron, (double) (Math.random() * 127));
                numberOfSynapses++;
            }
        }
        return numberOfSynapses;
    }
}
