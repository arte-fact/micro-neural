package com.artefact.app;

import java.util.ArrayList;

public class Utils {
    public static byte randomByte(int max) {
        return (byte) Math.floor(Math.random() * max);
    }


    public static double calculateByteAverage(ArrayList<Byte> values) {
        Integer sum = 0;
        if (!values.isEmpty()) {
            for (Byte value : values) {
                sum += value;
            }
            return sum.doubleValue() / values.size();
        }
        return sum;
    }
    public static double calculateIntAverage(ArrayList<Integer> values) {
        Integer sum = 0;
        if (!values.isEmpty()) {
            for (Integer value : values) {
                sum += value;
            }
            return sum.doubleValue() / values.size();
        }
        return sum;
    }
}
