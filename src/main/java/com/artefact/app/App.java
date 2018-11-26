package com.artefact.app;

import java.time.Duration;
import java.time.ZonedDateTime;

public class App {

    public static void main(String[] args) {
        int layers = 2;

        ZonedDateTime startNetworkGen = ZonedDateTime.now();
        NeuralNetwork neuralNetwork = new NeuralNetwork(layers, (int) Math.pow(2d, (double) layers), (int) Math.pow(2d, (double) layers));
        System.out.printf("Generated in %d seconds.%n", Duration.between(startNetworkGen, ZonedDateTime.now()).getSeconds());

        ZonedDateTime startBurst = ZonedDateTime.now();
        for (int i = 0; i < 1; i++) {
            neuralNetwork.burst();
        }
        System.out.printf("1 bursts in %d seconds.%n", Duration.between(startBurst, ZonedDateTime.now()).getSeconds());
    }
}
