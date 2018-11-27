package com.artefact.app;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        int layers = 3;

        ZonedDateTime startNetworkGen = ZonedDateTime.now();
        ArrayList<Integer> layerSizes = new ArrayList<>();
        layerSizes.add(16);
        layerSizes.add(64);
        layerSizes.add(1);

        NeuralNetwork neuralNetwork = new NeuralNetwork(layerSizes);
        System.out.printf("Generated in %d seconds.%n", Duration.between(startNetworkGen, ZonedDateTime.now()).getSeconds());

        int macroBatchQuantity = 400000;
        int batchSize = 5;
        int batchQuantity = 20;
        int minError = 100;
        int k = 0;
        ZonedDateTime startBurst = ZonedDateTime.now();
        while (k < macroBatchQuantity) {

            ArrayList<Byte> results = new ArrayList<>();
            for (int i = 0; i < batchQuantity; i++){
                byte error = 0;
                for (int j = 0; j < batchSize; j++) {
                    error += neuralNetwork.burst(true, false);
                    error += neuralNetwork.burst(false, true);
                }
                results.add(error);
                neuralNetwork.applyCorrections();
            }

            int batchError = (int) (Utils.calculateAverage(results) * 100 / (batchSize * 2));
//            System.out.printf("%d percent error on batch %d. %n", batchError, k);
            if (batchError < minError) {
                minError = batchError;
            }
            k++;
        }
        System.out.printf("%d batches of %d in %d seconds.%n",batchQuantity * macroBatchQuantity, batchSize * 2, Duration.between(startBurst, ZonedDateTime.now()).getSeconds());
        System.out.printf("Minimum error: %d percent", minError );

    }
}
