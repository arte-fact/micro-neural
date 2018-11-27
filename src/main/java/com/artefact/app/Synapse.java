package com.artefact.app;

import java.util.ArrayList;

public class Synapse {
    private Neuron child;

    private int batchSize = 0;

    public int getBatchSize() {
        return batchSize;
    }

    private int batchCorrectionSum = 0;

    public void addCorrection(int value) {
        this.batchSize++;
        this.batchCorrectionSum += value;
    }

    public void applyCorrection() {
        int correction = (int) Math.floor(this.batchCorrectionSum / (this.batchSize));
        this.weight += Math.floor(correction);
        for(Synapse parentSynapse: this.getParent().getParents()) {
            if (parentSynapse.parent.getCharge() > 100) {
                parentSynapse.addCorrection(correction);
            }
        }
    }

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
