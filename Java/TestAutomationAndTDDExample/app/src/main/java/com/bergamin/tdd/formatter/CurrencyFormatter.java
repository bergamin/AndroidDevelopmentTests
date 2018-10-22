package com.bergamin.tdd.formatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CurrencyFormatter {
    public String format(double value) {
        NumberFormat formatter = DecimalFormat.getCurrencyInstance();
        return formatter.format(value);
    }
}
