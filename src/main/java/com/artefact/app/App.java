package com.artefact.app;


import java.time.Duration;
import java.time.LocalTime;

public class App {

    public static void main(String[] args) {
        int layers = 6;

        LocalTime start = LocalTime.now();
        NeuralNetwork neuralNetwork = new NeuralNetwork(layers, (int) Math.pow(2d, (double) layers), (int) Math.pow(2d, (double) layers));
        LocalTime end = LocalTime.now();
        System.out.println("Generated in " + Duration.between(start, end).getSeconds() + " seconds.");
    }
}
