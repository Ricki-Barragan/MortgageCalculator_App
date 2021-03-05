package edu.sjsu.android.mortgagecalculator;

import java.text.NumberFormat;

public class MortgageUtil {
    final static double PERCENT = .001;
    final static byte MONTHS_IN_YEAR = 12;

    public static float monthlyInterest(float annualInterest) {
        return annualInterest / 1200;
    }

    public static String calculateMortgage(float principal,
                                           float annualInterest,
                                           float years,
                                           boolean tax) {

        float monthlyInterest = monthlyInterest(annualInterest);
        float totalMonths = years * MONTHS_IN_YEAR;
        float t; // taxes and insurance
        if(tax) {
            t = (float) (principal * PERCENT);
        } else {
            t = 0;
        }

        double monthlyPayment = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, totalMonths))
                / (Math.pow(1 + monthlyInterest, totalMonths) - 1);

        double mortgage = monthlyPayment + t;
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);

        return mortgageFormatted;
    }
}
