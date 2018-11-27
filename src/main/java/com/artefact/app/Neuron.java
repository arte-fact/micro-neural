package com.artefact.app;

import java.util.ArrayList;

class Neuron {
    private byte charge;
    private byte sensitivity;
    private ArrayList<Synapse> childs;
    private ArrayList<Synapse> parents;


    public ArrayList<Synapse> getParents() {
        return parents;
    }

    public void setParents(ArrayList<Synapse> parents) {
        this.parents = parents;
    }

    Neuron(byte charge, byte sensitivity) {
        this.charge = charge;
        this.sensitivity = sensitivity;
        childs = new ArrayList<Synapse>();
        parents = new ArrayList<Synapse>();
    }

    public void setCharge(byte charge) {
        this.charge = charge;
    }

    public void setSensitivity(byte sensitivity) {
        this.sensitivity = sensitivity;
    }

    public void setChilds(ArrayList<Synapse> childs) {
        this.childs = childs;
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
//        System.out.printf("| %d |", this.charge);
        Boolean activation = false;
        if (this.charge > this.sensitivity) {
            for (Synapse synapse: this.childs) {
                Neuron child = synapse.getChild();
                child.addCharge((byte) (this.charge * synapse.getWeight() / 127));
                activation = true;
            }
        }
        this.charge = (byte) 0;
        return activation;
    }

    public ArrayList<Synapse> getChilds() {
        return childs;
    }

    public void addParent(Synapse synapse) {
        this.parents.add(synapse);
    }

    public Synapse addChild(Neuron child) {
        Synapse synapse = new Synapse(child, this, Utils.randomByte(127));
        this.childs.add(synapse);
        return synapse;
    }
}
