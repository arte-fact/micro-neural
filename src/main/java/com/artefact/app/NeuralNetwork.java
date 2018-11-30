package com.artefact.app;

import java.util.ArrayList;

class NeuralNetwork {
    private ArrayList<Layer> layers;

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    NeuralNetwork(ArrayList<Integer> layerSizes) {

        layers = new ArrayList();
        int totalNeuronNumber = 0;
        int totalSynapseNumber = 0;

        int layerIndex = 0;
        for (Integer layerSize: layerSizes) {
            int neuronNumber = 0;
            int synapseNumber = 0;

            ArrayList<Neuron> neuronLayer = new ArrayList();
            for (int i = 0; i < layerSize; i++) {
                Neuron neuron = new Neuron();
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
        ArrayList<Neuron> neurons = this.layers.get(0).getNeurons();

        for (int i = 0, neuronsSize = neurons.size(); i < neuronsSize; i++) {
            Neuron neuron = neurons.get(i);
            if (!randomized) {
                charge += 8;
                neuron.setCharge(charge);
            } else {
                charge = Utils.randomByte(127);
                neuron.setCharge(charge);
            }
        }

        int layerIndex = this.layers.size() - 1;
        while (layerIndex >  -1) {
            int neuronIndex = this.layers.get(layerIndex).getNeurons().size() - 1;
            while (neuronIndex > -1) {
                this.layers.get(layerIndex).getNeurons().get(neuronIndex).activation();
                neuronIndex--;
            }
            layerIndex--;
        }



        layerIndex = this.layers.size() -1;
        while (layerIndex >  -1) {
            int neuronIndex = this.layers.get(layerIndex).getNeurons().size() - 1;
            while (neuronIndex > -1) {
                int synapseIndex = this.layers.get(layerIndex).getNeurons().get(neuronIndex).getChilds().size() - 1;
                while (synapseIndex > -1) {
                    this.layers.get(layerIndex).getNeurons().get(neuronIndex).getChilds().get(synapseIndex).activation();
                    synapseIndex--;
                }
                neuronIndex--;
            }
            layerIndex--;
        }
//        System.out.printf("Network output: %d. %n", this.layers.get(this.layers.size() - 1).getNeurons().get(0).getOutput());
        if (this.layers.get(this.layers.size() - 1).getNeurons().get(0).getOutput() > 0 && expectation) {
            int synapseIndex = 0;
            while (this.layers.get(this.layers.size() -1).getNeurons().get(0).getParents().size() > synapseIndex) {
                this.layers.get(this.layers.size() -1).getNeurons().get(0).getParents().get(synapseIndex).addToCorrection(127);
                synapseIndex++;
            }
            error = 1;
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
