package com.dargoz.calculator;
import android.util.Log;

/*
Author : Davin Reinaldo Gozali
Date : 29 January 2019
 */

public class CheckAmountText {
    public static boolean isOperator(String inputString){
        Log.d("DRG","String operator");
        return inputString.equals("+") || inputString.equals("-") || inputString.equals("x") || inputString.equals("÷");
    }

    public static boolean isOperator(char character){
        Log.d("DRG","Char operator : " + character);
        return !Character.isDigit(character);
    }

    public static String cleanDotFromString(String text){
        if(text.contains(".")){
            String[] parts = text.split("[.]");
            text = "";
            for (int i=0;i<parts.length;i++){
                text = text.concat(parts[i]);
            }
        }
        return text;
    }

    public static String formatStringForAmount(String text){
        if(CheckAmountText.isOperator(text.charAt(text.length()-1))) {
            Log.d("DRG","text contain operator pre:: " + text);
            text = text.substring(0, text.length() - 1);
            Log.d("DRG","text contain operator post:: " + text);
        }

        int pos = 1;
        int length = text.length();
        if(length > 3){
            for(int i=length-1;i>=0;i--){
                if(pos++ % 3 == 0 && Character.isDigit(text.charAt(i)) && i != 0){
                    text = text.substring(0,i) + "." + text.substring(i,text.length());

                }
            }
        }
        return text;
    }
}
