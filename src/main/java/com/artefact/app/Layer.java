package com.artefact.app;

import java.util.ArrayList;

class Layer {
    private ArrayList<ArrayList<Neuron>> matrix;
    private Layer child;

    Layer(ArrayList<ArrayList<Neuron>> matrix) {
        this.matrix = matrix;
    }

    public ArrayList<ArrayList<Neuron>> getMatrix() {
        return matrix;
    }

    void setChild(Layer child) {
        if (child != null) {
            this.child = child;
        }
    }

    private int generateNeuronSynapses(Neuron neuron, Layer childLayer, int parentX, int parentY) {
        int numberOfSynapses = 0;
        for (ArrayList<Neuron> line :childLayer.getMatrix()) {
            for (Neuron child: line) {
                int layerNeurones = (int) Math.pow(2d, this.getMatrix().size());
                byte weight = (byte) 1;

                int childX = childLayer.getMatrix().indexOf(line);
                int childY = line.indexOf(child);

                parentX++;
                parentY++;
                childX++;
                childY++;
//
//                boolean isClose = childX == Math.ceil(parentX/2) && childY == Math.ceil(parentY/2);
//
//                if (isClose) {
//                }

                Synapse synapse = neuron.addChild(child);
                child.addParent(synapse);
                System.out.printf("Synapse %d(%d,%d,%d,%d) Weight: %d %n", numberOfSynapses, parentX, parentY, childX, childY, weight);
                System.out.printf("Parent: %s, Child, %s.%n", synapse.getChild(), synapse.getParent());
                numberOfSynapses++;
            }
        }
        return numberOfSynapses;
    }

    int generateSynapses(Layer childLayer) {
        int numberOfSynapses = 0;
        for (ArrayList<Neuron> line: this.matrix) {
            for (Neuron neuron: line) {
                int parentX = this.matrix.indexOf(line);
                int parentY = line.indexOf(neuron);

                numberOfSynapses += generateNeuronSynapses(neuron, childLayer, parentX, parentY);
            }
        }
        return numberOfSynapses;
    }
}
