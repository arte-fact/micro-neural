package com.artefact.app;

import java.time.Duration;
import java.time.ZonedDateTime;

public class App {

    public static void main(String[] args) {
        int layers = 3;

        ZonedDateTime startNetworkGen = ZonedDateTime.now();
        NeuralNetwork neuralNetwork = new NeuralNetwork(layers, (int) Math.pow(2d, (double) layers), (int) Math.pow(2d, (double) layers));
        System.out.printf("Generated in %d seconds.%n", Duration.between(startNetworkGen, ZonedDateTime.now()).getSeconds());

        ZonedDateTime startBurst = ZonedDateTime.now();

        for (int i = 0; i < 100; i++){
            int error = 0;
            for (int j = 0; j < 50; j++) {
                if(!neuralNetwork.burst(true, false)) {
                    error++;
                }
                if(neuralNetwork.burst(false, true)) {
                    error++;
                }
            }
            System.out.printf("%d percent error... %n", (int) error);
        }
        System.out.printf("100 000 bursts in %d seconds.%n", Duration.between(startBurst, ZonedDateTime.now()).getSeconds());
    }
}
