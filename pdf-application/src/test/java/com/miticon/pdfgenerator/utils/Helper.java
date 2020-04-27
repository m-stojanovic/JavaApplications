package com.miticon.pdfgenerator.utils;

import java.util.Random;

public class Helper {

    public static final double ONE_INCH_IN_MM = 25.4;
    public static final int DPI = 72;

    public static int randomInt(int n) {
        Random random = new Random();
        int randomInt = random.nextInt(n);
        return randomInt;
    }

    public static int randomIntInRange(int min, int max) {
        Random random = new Random();
        int randomIntInRange = random.nextInt((max - min) + 1) + min;
        return randomIntInRange;
    }

    public static double randomDouble() {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        return Double.parseDouble(String.format("%.2f", randomDouble));
    }

    public static double randomDoubleInRange(double min, double max) {
        Random random = new Random();
        double randomDoubleInRange = min + (max - min) * random.nextDouble();
        return Double.parseDouble(String.format("%.2f", randomDoubleInRange));
    }

    public static int mmToPointConvertor (double mm) {
        double points = mm * DPI / ONE_INCH_IN_MM;
        return (int)points;
    }

}
