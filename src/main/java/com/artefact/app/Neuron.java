package com.artefact.app;

import java.util.ArrayList;

class Neuron {
    private byte charge;
    private byte sensitivity;
    private ArrayList<Synapse> synapses;

    Neuron(byte charge, byte sensitivity) {
        this.charge = charge;
        this.sensitivity = sensitivity;
        synapses = new ArrayList<Synapse>();
    }

    public void setCharge(byte charge) {
        this.charge = charge;
    }

    public void setSensitivity(byte sensitivity) {
        this.sensitivity = sensitivity;
    }

    public void setSynapses(ArrayList<Synapse> synapses) {
        this.synapses = synapses;
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

    public void burst () {
        System.out.printf("| %d |", this.charge);

        if (this.charge > this.sensitivity) {
            for (Synapse synapse: this.synapses) {
                Neuron child = synapse.getChild();
                child.addCharge((byte) (this.charge * synapse.getWeight() / 127));
            }
        }
        this.charge = (byte) 0;
    }

    public ArrayList<Synapse> getSynapses() {
        return synapses;
    }

    void addSynapse(Neuron child, byte strength) {
        this.synapses.add(new Synapse(child, strength));
    }
}
