package com.gurav.samaj.surat.Widgets;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class NumberToCurrencyExamples
{
   public static String getAmount(Double amount)
   {
//      //This is the amount which we want to format
//      Double currencyAmount = new Double(amount);
//
//      //Get current locale information
//      Locale currentLocale = Locale.getDefault();
//
//      //Get currency instance from locale; This will have all currency related information
//      Currency currentCurrency = Currency.getInstance(currentLocale);
//
//      //Currency Formatter specific to locale
//      NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);


      double number = amount;
      String str = NumberFormat.getNumberInstance(Locale.US).format(number);

      return str;
   }
}
 