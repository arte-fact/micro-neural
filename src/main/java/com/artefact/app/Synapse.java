package com.artefact.app;

public class Synapse {
    private Neuron child;

    private int batchSize = 1;
    private int batchCorrectionSum = 0;
    private Neuron parent;
    private double weight;

    Synapse(Neuron child, Neuron parent, double weight) {
        this.child = child;
        this.weight = weight;
        this.parent = parent;
    }

    public void addToCorrection(int value) {
        this.batchSize++;
        this.batchCorrectionSum += value;
    }

    public void activation() {
        this.child.addToCharge((byte)(this.parent.getOutput() * this.weight));
    }

    public void applyCorrection() {
        this.weight += (double) this.batchCorrectionSum / this.batchSize;
//        System.out.printf("Synapse: correction %d. %n", this.batchCorrectionSum / this.batchSize);

        for(Synapse parentSynapse: this.parent.getParents()) {
            if (parentSynapse.parent.getOutput() > 0) {
                parentSynapse.addToCorrection((byte) (parentSynapse.parent.getCharge() * 0.05));
            }
        }
    }

}
