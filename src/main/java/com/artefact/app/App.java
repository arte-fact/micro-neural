package com.artefact.app;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        ZonedDateTime startNetworkGen = ZonedDateTime.now();
        ArrayList<Integer> layerSizes = new ArrayList<>();
        layerSizes.add(16);
        layerSizes.add(32);
        layerSizes.add(1);

        NeuralNetwork neuralNetwork = new NeuralNetwork(layerSizes);
        System.out.printf("Generated in %d seconds.%n", Duration.between(startNetworkGen, ZonedDateTime.now()).getSeconds());

        int macroBatchQuantity = 10000;
        int batchSize = 20;
        int batchQuantity = 100;
        ArrayList<Integer> batchErrors = new ArrayList<>();
        int minError = 100;
        int maxError = 0;
        int k = 1;
        ZonedDateTime startBurst = ZonedDateTime.now();
        while (k < macroBatchQuantity) {
            ArrayList<Byte> results = new ArrayList<>();
            for (int i = 0; i < batchQuantity; i++){
                byte error = 0;
                for (int j = 0; j < batchSize; j++) {
                    error += neuralNetwork.burst(false, true);
                    error += neuralNetwork.burst(true, true);
//                    error += neuralNetwork.burst(false, true);
                }
                results.add(error);
                for (Layer layer: neuralNetwork.getLayers()) {
                    neuralNetwork.applyCorrections(neuralNetwork.getLayers().indexOf(layer));
                }
            }

            int batchError = (int) (Utils.calculateByteAverage(results) * 100 / (batchSize * 2));
//            System.out.printf("%d percent error on batch %d. %n", batchError, k);
            batchErrors.add(batchError);

            float percentCompletition = (float) k * 100f / (float) macroBatchQuantity;
            if (percentCompletition % 1f == 0f) {
                System.out.printf("Completition %d percent, average error: %d, min: %d, max: %d, %s %n", (int) percentCompletition, (int) Math.floor(Utils.calculateIntAverage(batchErrors)), minError, maxError, batchErrors.toString());

                batchErrors.clear();
            }

            if (batchError < minError) {
                minError = batchError;
            }
            if (batchError > maxError) {
                maxError = batchError;
            }
//
//            if (batchError < 40) {
//                ArrayList<Integer> finalResults = new ArrayList<>();
//                for (int i = 0; i < 1000; i++){
//                    int error = 0;
//                    for (int j = 0; j < 50; j++) {
//                        error += neuralNetwork.burst(true, false);
//                        error += neuralNetwork.burst(false, true);
//                    }
//                    finalResults.add(error);
//                }
//                double finalError = Utils.calculateIntAverage(finalResults);
//                System.out.printf("Final error table: %s %n", finalResults.toString());
//                System.out.printf("Final error: %d percent. %n", (int) finalError);
//                k = macroBatchQuantity;
//            }

            k++;
        }
        System.out.printf("%d batches of %d in %d seconds.%n",batchQuantity * macroBatchQuantity, batchSize * 2, Duration.between(startBurst, ZonedDateTime.now()).getSeconds());
        System.out.printf("Minimum error: %d percent. %n", minError );
        System.out.printf("Maximum error: %d percent. %n", maxError );

    }
}
