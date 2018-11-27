package com.artefact.app;

import java.util.ArrayList;

class Neuron {
    private byte charge;
    private byte sensitivity;

    public void setSensitivity(byte sensitivity) {
        this.sensitivity = sensitivity;
    }

    private ArrayList<Synapse> childs;
    private ArrayList<Synapse> parents;

    public ArrayList<Synapse> getChilds() {
        return childs;
    }

    public ArrayList<Synapse> getParents() {
        return parents;
    }

    Neuron(byte charge, byte sensitivity) {
        this.charge = charge;
        this.sensitivity = sensitivity;
        childs = new ArrayList<>();
        parents = new ArrayList<>();
    }

    public void setCharge(byte charge) {
        this.charge = charge;
    }

    public byte getCharge() {
        return charge;
    }

    public byte getSensitivity() {
        return sensitivity;
    }

    public void addCharge (byte value) {
        if (this.charge + value > 126) {
            this.charge = 127;
        } else {
            this.charge += value;
        }
    }

    public boolean burst () {
        boolean activation = false;
        if (this.charge > this.sensitivity) {
            for (Synapse synapse: this.childs) {
                Neuron child = synapse.getChild();
                child.addCharge((byte) (this.charge * synapse.getWeight() / 127));
                activation = true;
            }
        }
        return activation;
    }

    public void addParent(Synapse synapse) {
        this.parents.add(synapse);
    }

    public Synapse connectChild(Neuron child, byte weight) {
        Synapse synapse = new Synapse(child, this, weight);
        this.childs.add(synapse);
        child.addParent(synapse);

        return synapse;
    }
}
