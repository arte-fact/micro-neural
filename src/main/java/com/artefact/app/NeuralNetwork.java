package com.artefact.app;

import java.util.ArrayList;

class NeuralNetwork {
    private ArrayList<Layer> layers;

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    NeuralNetwork(ArrayList<Integer> layerSizes) {

        byte neuronCharge = 0;
        byte sensitivity = 100;

        layers = new ArrayList();
        int totalNeuronNumber = 0;
        int totalSynapseNumber = 0;

        int layerIndex = 0;
        for (Integer layerSize: layerSizes) {
            int neuronNumber = 0;
            int synapseNumber = 0;

            ArrayList<Neuron> neuronLayer = new ArrayList();
            for (int i = 0; i < layerSize; i++) {
                Neuron neuron = new Neuron(neuronCharge, sensitivity);
                neuronLayer.add(neuron);
                neuronNumber++;
            }
            Layer layer = new Layer(neuronLayer);
            layers.add(layer);

            for (Layer generatedLayer: this.layers) {
                int index = this.layers.indexOf(generatedLayer);
                if (index > 0) {
                    synapseNumber += this.layers.get(index - 1).generateSynapses(generatedLayer);
                }
            }

            System.out.printf("Layer %d: ", layerIndex);
            System.out.printf("%d neurons generated, ", neuronNumber);
            System.out.printf("%d synapses generated%n", synapseNumber);
            totalNeuronNumber += neuronNumber;
            totalSynapseNumber += synapseNumber;
            layerIndex++;
        }

        System.out.printf("%d neurons generated, ", totalNeuronNumber);
        System.out.printf("%d synapses generated%n", totalSynapseNumber);
        System.out.printf("Input size: %d neurons %n", layerSizes.get(0));
    }

    public byte burst (boolean expectation, boolean randomized) {
        byte error = 0;
        byte charge = 0;
        for (Neuron neuron: this.layers.get(0).getNeurons()) {
            if (!randomized) {
                charge += 8;
                neuron.setCharge(charge);
            } else {
                charge = Utils.randomByte(127);
                neuron.setCharge(charge);
            }
        }

        for (Layer layer: this.layers) {
            for (Neuron neuron: layer.getNeurons()) {
                neuron.burst();
            }
        }

        Neuron outputNeuron = this.layers.get(this.layers.size() - 1).getNeurons().get(0);

        for (Synapse synapse: outputNeuron.getParents()) {
            if (outputNeuron.getCharge() < outputNeuron.getSensitivity() && expectation) {
                byte parentCharge = synapse.getParent().getCharge();
                int correction = (int) Math.floor((127 - parentCharge));
                synapse.addCorrection(correction);
                error = 1;
            }
            if (outputNeuron.getCharge() > outputNeuron.getSensitivity() && !expectation) {
                int correction = (int) Math.floor((0 - synapse.getParent().getCharge()));
                synapse.addCorrection(correction);
                error = 1;
            }
        }
        return error;
    }

    public void applyCorrections (int layerIndex) {
        Layer layer = this.layers.get(layerIndex);
        for (Neuron neuron: layer.getNeurons()) {
            for (Synapse synapse: neuron.getParents()) {
                synapse.applyCorrection();
            }
        }
    }
}
