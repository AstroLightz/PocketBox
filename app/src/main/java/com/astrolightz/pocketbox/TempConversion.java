package com.astrolightz.pocketbox;

/**
 * A collection of temperature conversion methods
 */
public class TempConversion
{
    // Fahrenheit

    /**
     * Converts a temperature in Fahrenheit to Celsius
     * @param fahrenheit The temperature in Fahrenheit
     * @return The temperature in Celsius
     */
    public static double fahrenheitToCelsius(double fahrenheit)
    {
        return (fahrenheit - 32) * 5 / 9;
    }

    /**
     * Converts a temperature in Fahrenheit to Kelvin
     * @param fahrenheit The temperature in Fahrenheit
     * @return The temperature in Kelvin
     */
    public static double fahrenheitToKelvin(double fahrenheit)
    {
        return (fahrenheit - 32) * 5 / 9 + 273.15;
    }

    // Celsius

    /**
     * Converts a temperature in Celsius to Fahrenheit
     * @param celsius The temperature in Celsius
     * @return The temperature in Fahrenheit
     */
    public static double celsiusToFahrenheit(double celsius)
    {
        return celsius * 9 / 5 + 32;
    }

    /**
     * Converts a temperature in Celsius to Kelvin
     * @param celsius The temperature in Celsius
     * @return The temperature in Kelvin
     */
    public static double celsiusToKelvin(double celsius)
    {
        return celsius + 273.15;
    }

    // Kelvin

    /**
     * Converts a temperature in Kelvin to Fahrenheit
     * @param kelvin The temperature in Kelvin
     * @return The temperature in Fahrenheit
     */
    public static double kelvinToFahrenheit(double kelvin)
    {
        return (kelvin - 273.15) * 9.0 / 5.0 + 32;
    }

    /**
     * Converts a temperature in Kelvin to Celsius
     * @param kelvin The temperature in Kelvin
     * @return The temperature in Celsius
     */
    public static double kelvinToCelsius(double kelvin)
    {
        return kelvin - 273.15;
    }


    /**
     * Performs a temperature conversion
     * @param tempFrom The temperature type to convert from
     * @param tempTo The temperature type to convert to
     * @param temp The temperature to convert
     * @return The converted temperature
     */
    public static double performConversion(String tempFrom, String tempTo, double temp)
    {
        // Determine which conversion to perform
        double result = 0;

        switch (tempFrom)
        {
            case "Fahrenheit":
                switch (tempTo)
                {
                    case "Celsius":
                        result = fahrenheitToCelsius(temp);
                        break;
                    case "Kelvin":
                        result = fahrenheitToKelvin(temp);
                        break;
                }
                break;
            case "Celsius":
                switch (tempTo)
                {
                    case "Fahrenheit":
                        result = celsiusToFahrenheit(temp);
                        break;
                    case "Kelvin":
                        result = celsiusToKelvin(temp);
                        break;
                }
                break;
            case "Kelvin":
                switch (tempTo)
                {
                    case "Fahrenheit":
                        result = kelvinToFahrenheit(temp);
                        break;
                    case "Celsius":
                        result = kelvinToCelsius(temp);
                        break;
                }
                break;
        }

        // Round
        result = Utilities.roundTo(result, 2);

        return result;
    }
}
