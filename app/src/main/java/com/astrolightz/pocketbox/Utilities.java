package com.astrolightz.pocketbox;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.TypedValue;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

/**
 * A collection of utility methods
 */
public class Utilities
{
    // Default date format
    public static final String DEFAULT_DATE_FORMAT = "MMMM dd, yyyy";

    // Large number error
    public static final String LARGE_NUMBER_ERROR = "nan";

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

    /**
     * Calculate the number of days between two dates
     * @param startDate The start date
     * @param endDate  The end date
     * @param dateFormat The format of the dates. Leave blank for default: "MMMM dd, yyyy"
     * @return The number of days between the two dates
     */
    public static long calculateDaysApart(String startDate, String endDate, String dateFormat)
    {
        // Set default format if none provided
        if (dateFormat.isEmpty())
        {
            // January 01, 1970
            dateFormat = DEFAULT_DATE_FORMAT;
        }

        // Get dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        // Calculate days apart
        return ChronoUnit.DAYS.between(start, end);
    }


    public static void displayError(View view, String message)
    {
        // Display a toast in a Material style using Snackbar
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .show();
    }

    // =============================================================================================
    //                                      NUMBER NAME
    // =============================================================================================

    private static final NavigableMap<BigDecimal, String> numberNames = new TreeMap<>(Collections.reverseOrder());

    // Setup number names
    static
    {
        numberNames.put(new BigDecimal("1000"), "Thousand");
        numberNames.put(new BigDecimal("1000000"), "Million");
        numberNames.put(new BigDecimal("1000000000"), "Billion");
        numberNames.put(new BigDecimal("1000000000000"), "Trillion");
        numberNames.put(new BigDecimal("1000000000000000"), "Quadrillion");
        numberNames.put(new BigDecimal("1000000000000000000"), "Quintillion");
        numberNames.put(new BigDecimal("1000000000000000000000"), "Sextillion");
        numberNames.put(new BigDecimal("1000000000000000000000000"), "Septillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000"), "Octillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000"), "Nonillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000"), "Decillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000"), "Undecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000"), "Duodecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000"), "Tredecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000"), "Quattuordecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000"), "Quindecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000"), "Sexdecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000"), "Septendecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000"), "Octodecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000"), "Novemdecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000"), "Vigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000"), "Unvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000"), "Duovigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000"), "Trevigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000"), "Quattuorvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Quinvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Sexvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Septenvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Octovigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Novemvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Trigintillion");
    }

    /**
     * Adjusts the font size of a text view based on the length of the text
     * @param text     The text to adjust by
     * @param textView The text view to adjust
     */
    public static void adjustFontSize(String text, MaterialTextView textView)
    {
        // Size constraints
        final float MAX_SIZE = 40f;
        final float MIN_SIZE = 14f;

        // Scaling factor
        final float SCALE_FACTOR = 0.5f;

        // Adjust based on length
        float fontSize = MAX_SIZE - (text.length() * SCALE_FACTOR);
        if (fontSize < MIN_SIZE)
        {
            // Prevent too small
            fontSize = MIN_SIZE;
        }

        // Set size
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
    }

    /**
     * Converts a number to a name
     * @param number The number to convert
     * @return       The name of the number
     */
    public static String getNumberName(BigDecimal number)
    {
        for (Map.Entry<BigDecimal, String> entry : numberNames.entrySet())
        {
            BigDecimal divisor = entry.getKey();
            String name = entry.getValue();

            // Handle too big number
            if (number.compareTo(numberNames.firstKey()) >= 0)
            {
                return LARGE_NUMBER_ERROR;
            }

            // Get name
            if (number.compareTo(divisor) >= 0)
            {
                // Greater than divisor
                BigDecimal value = number.divide(divisor, 2, RoundingMode.HALF_UP);
                return value.stripTrailingZeros().toPlainString() + " " + name;
            }

        }

        // No matches, return raw number
        return number.toPlainString();
    }



    // =============================================================================================
    //                                      LICENSES SETUP
    // =============================================================================================

    public static final List<String> partyUrls = List.of(
            "https://github.com/material-components/material-components-android",
            "https://github.com/androidx/androidx",
            "https://pictogrammers.com/library/mdi/"
    );

    public static final List<License> licenses = List.of(
            new License("Material Components for Android", "Google, Inc. under ALv2", License.ALv2(), partyUrls.get(0)),
            new License("AndroidX", "Android Open Source Project under ALv2", License.ALv2(), partyUrls.get(1)),
            new License("Material Design Icons", "Pictogrammers under ALv2", License.ALv2(), partyUrls.get(2))
    );

    /**
     * Show full license for a third-party
     * @param context      Context
     * @param licenseIndex Index of the license in the licenses list. Use -1 to show app license
     */
    public static void showFullLicense(Context context, int licenseIndex)
    {
        // Show app license if index is -1
        if (licenseIndex == -1)
        {
            new MaterialAlertDialogBuilder(context)
                    .setTitle("PocketBox")
                    .setMessage(License.GPLv3())
                    .setPositiveButton("DONE", null)
                    .show();
        }
        else
        {
            new MaterialAlertDialogBuilder(context)
                    .setTitle(licenses.get(licenseIndex).getName())
                    .setMessage(licenses.get(licenseIndex).getLicense())
                    .setPositiveButton("DONE", null)
                    .setNegativeButton("OPEN WEBSITE", (dialog, which) -> {

                        // Visit page in browser
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(licenses.get(licenseIndex).getUrl()));
                        context.startActivity(browserIntent);
                    })
                    .show();
        }
    }
}
