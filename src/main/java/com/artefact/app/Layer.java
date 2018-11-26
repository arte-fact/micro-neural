package com.artefact.app;

import java.util.ArrayList;

class Layer {
    private ArrayList<ArrayList<ArrayList<Neuron>>> matrix;
    private Layer child;

    Layer(ArrayList<ArrayList<ArrayList<Neuron>>> matrix) {
        this.matrix = matrix;
    }

    private ArrayList<ArrayList<ArrayList<Neuron>>> getMatrix() {
        return matrix;
    }

    void setChild(Layer child) {
        if (child == null) {
            this.child = child;
        }
    }

    private int generateNeuronSynapses(Neuron neuron, Layer childLayer, int parentX, int parentY) {
        int numberOfSynapses = 0;
        for (ArrayList<ArrayList<Neuron>> column :
                childLayer.getMatrix()) {
            for (ArrayList<Neuron> line:
                    column) {
                for (Neuron child: line) {
                    byte sensitivity = 127;
                    byte strenght = 127;

                    int childX = column.indexOf(line);
                    int childY = line.indexOf(child);

                    boolean isClose = (Math.ceil((float) childX / 2) == parentX) && (Math.ceil((float) childY / 2) == parentY);

                    if (isClose) {
                        neuron.addSynapse(child, sensitivity, strenght);
                        numberOfSynapses++;
                    }

                }
            }
        }
        return numberOfSynapses;
    }

    int generateSynapses(Layer childLayer) {
        int numberOfSynapses = 0;
        for (ArrayList<ArrayList<Neuron>> column :
                this.getMatrix()) {
            for (ArrayList<Neuron> line:
                column) {
                for (Neuron neuron: line) {
                    int parentX = column.indexOf(line);
                    int parentY = line.indexOf(neuron);

                    numberOfSynapses += generateNeuronSynapses(neuron, childLayer, parentX, parentY);


                }
            }
        }

        return numberOfSynapses;
    }
}
