package com.artefact.app;

public class Synapse {
    private Neuron child;
    private byte weight;

    Synapse(Neuron child, byte weight) {
        this.child = child;
        this.weight = weight;
    }

    public Neuron getChild() {
        return child;
    }

    public void setChild(Neuron child) {
        this.child = child;
    }

    public byte getWeight() {
        return weight;
    }

    public void setWeight(byte weight) {
        this.weight = weight;
    }
}
