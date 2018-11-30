package com.artefact.app;

import java.util.ArrayList;

class Neuron {

    private byte charge;
    private byte output;

    public byte getOutput() {
        return output;
    }

    private ArrayList<Synapse> childs;
    private ArrayList<Synapse> parents;

    ArrayList<Synapse> getChilds() {
        return childs;
    }

    ArrayList<Synapse> getParents() {
        return parents;
    }

    Neuron() {
        this.charge = 0;
        this.output = 0;
        childs = new ArrayList<>();
        parents = new ArrayList<>();
    }

    void setCharge(byte charge) {
        this.charge = charge;
    }

    byte getCharge() {
        return charge;
    }

    void addToCharge(byte value) {
        if (this.charge + value > 126) {
            this.charge = 127;
        } else {
            this.charge += value;
        }
    }

    void activation () {
        this.output = (byte) (((1 / (1 - Math.exp((double) this.charge))) * 127));

//        System.out.printf("Neuron output: %d, charge %d.%n", this.output, this.charge);
    }

    private void addParent(Synapse synapse) {
        this.parents.add(synapse);
    }

    Synapse connectChild(Neuron child, double weight) {
        Synapse synapse = new Synapse(child, this, weight);
        this.childs.add(synapse);
        child.addParent(synapse);

        return synapse;
    }
}
