package com.astrolightz.pocketbox;

/**
 * A collection of utility methods
 */
public class Utilities
{

    /**
     * Rounds a number to a specified number of decimal places
     * @param number         The number to round
     * @param place  The number of decimal places to round to
     * @return               The rounded number
     */
    public static double roundTo(Number number, int place)
    {
        double factor = Math.pow(10, place);
        return Math.round(number.doubleValue() * factor) / factor;
    }

}
