package com.artefact.app;

import java.util.ArrayList;

class Neuron {
    private byte charge;
    private byte sensitivity;
    private byte strengh;
    private ArrayList<Synapse> synapses;

    Neuron(byte charge, byte sensitivity, byte strength) {
        this.charge = charge;
        this.sensitivity = sensitivity;
        this.strengh = strength;
        synapses = new ArrayList<Synapse>();
    }

    void addSynapse(Neuron child, byte strength, byte sensitivity) {
        this.synapses.add(new Synapse(child, strength, sensitivity));
    }
}
