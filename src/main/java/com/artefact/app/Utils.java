package com.artefact.app;

public class Utils {
    public static byte randomByte(int max) {
        return (byte) Math.floor(Math.random() * max);
    }
}
