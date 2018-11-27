package com.artefact.app;

public class Synapse {
    private Neuron child;

    public Neuron getParent() {
        return parent;
    }

    public void setParent(Neuron parent) {
        this.parent = parent;
    }

    private Neuron parent;
    private byte weight;

    Synapse(Neuron child, Neuron parent, byte weight) {
        this.child = child;
        this.weight = weight;
        this.parent = parent;
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
