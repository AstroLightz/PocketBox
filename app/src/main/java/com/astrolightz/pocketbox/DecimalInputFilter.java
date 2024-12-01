package com.astrolightz.pocketbox;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Input filter to constrain input to a decimal number with a specified number of digits before and after the decimal point.
 */
public class DecimalInputFilter implements InputFilter
{
    private int digitsBeforeZero;
    private int digitsAfterZero;
    private Pattern pattern;

    // Defaults
    private static final int DEFAULT_DIGITS_BEFORE_ZERO = 10;
    private static final int DEFAULT_DIGITS_AFTER_ZERO = 10;

    // Constructor
    public DecimalInputFilter(Integer digitsBeforeZero, Integer digitsAfterZero)
    {
        // Set defaults if null
        this.digitsBeforeZero = (digitsBeforeZero != null ? digitsBeforeZero : DEFAULT_DIGITS_BEFORE_ZERO);
        this.digitsAfterZero = (digitsAfterZero != null ? digitsAfterZero : DEFAULT_DIGITS_AFTER_ZERO);

        // Setup Pattern
        pattern = Pattern.compile("-?[0-9]{0," + (digitsBeforeZero) + "}+((\\.[0-9]{0," +
                (digitsAfterZero) + "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd)
    {
        // Get the new value
        String replacement = source.subSequence(start, end).toString();
        String newVal = dest.subSequence(0, dStart).toString() + replacement +
                dest.subSequence(dEnd, dest.length()).toString();

        // Check if the new value is valid
        Matcher matcher = pattern.matcher(newVal);
        if (matcher.matches())
        {
            return null;
        }

        // Check if source is empty
        if (TextUtils.isEmpty(source))
        {
            return dest.subSequence(dStart, dEnd);
        }
        else
        {
            return "";
        }

    }
}
