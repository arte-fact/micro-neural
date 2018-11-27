package com.artefact.app;

import java.util.ArrayList;

public class Utils {
    public static byte randomByte(int max) {
        return (byte) Math.floor(Math.random() * max);
    }


    public static double calculateAverage(ArrayList<Byte> marks) {
        Integer sum = 0;
        if (!marks.isEmpty()) {
            for (Byte mark : marks) {
                sum += mark;
            }
            return sum.doubleValue() / marks.size();
        }
        return sum;
    }
}
