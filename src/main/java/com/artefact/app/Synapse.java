package com.artefact.app;

public class Synapse {
    private Neuron child;
    private byte sensitivity;
    private byte strenght;

    Synapse(Neuron child, byte sensitivity, byte strenght) {
        this.child = child;
        this.sensitivity = sensitivity;
        this.strenght = strenght;
    }

    @Override
    public String toString() {
        return "Synapse{" +
                "child=" + child +
                ", sensitivity=" + sensitivity +
                ", strenght=" + strenght +
                '}';
    }
}
