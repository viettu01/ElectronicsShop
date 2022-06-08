package com.tuplv.electronicsshopapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Format {

    public static String formatPrice(double number) {
        String pattern = "###,###.###";
        DecimalFormat dec = new DecimalFormat(pattern);
        return dec.format(number);
    }

    public static String sortText(String text, int length) {
        String newText;
        if (text.length() > length) {
            newText = text.substring(0, length) + "...";
            return newText;
        }

        return text;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String dateFormat(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy, hh:mm:ss a", Locale.ENGLISH);
        LocalDate result = LocalDate.parse(date, dateTimeFormatter);

        Date date1 = Date.from(result.atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        return result.toString();
    }
}