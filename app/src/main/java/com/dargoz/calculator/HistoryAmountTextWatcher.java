package com.dargoz.calculator;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/*
Author : Davin Reinaldo Gozali
Date : 29 January 2019
 */

public class HistoryAmountTextWatcher implements TextWatcher {
    private MainActivity parentActivity;
    private EditText amountText;
    private TextView historyText;
    private boolean checkChanges = false;


    HistoryAmountTextWatcher(Activity activity){
        parentActivity = (MainActivity) activity;
        this.amountText =  parentActivity.findViewById(R.id.amountText);
        this.historyText = parentActivity.findViewById(R.id.historyAmount);

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        String text = historyText.getText().toString();
        String textHistory = text;
        int newLength = text.length();
        Log.d("DRG","History amount : " + text + " - l:" + newLength);
        if (newLength > 0){
            if(!CheckAmountText.isOperator(text.charAt(newLength-1))) {
                text = CheckAmountText.cleanDotFromString(text);
                text = processOperator(text);
                amountText.setText(String.format("%s",text));

                textHistory = CheckAmountText.cleanDotFromString(textHistory);
                char tempOperator = getOperator(textHistory);
                String[] historyParts = splitOperator(textHistory);
                assert historyParts != null;
                textHistory = CheckAmountText.formatStringForAmount(historyParts[0]) + tempOperator + CheckAmountText.formatStringForAmount(historyParts[1]);


                if(!checkChanges) {
                    checkChanges = true;
                    historyText.setText(String.format("%s",textHistory));
                }
                else
                    checkChanges = false;
            }else{
                if(!checkChanges) {
                    try {
                        text = CheckAmountText.cleanDotFromString(text);
                        amountText.setText(String.format("%s", text.substring(0, newLength - 1)));
                        checkChanges = true;
                    }catch (StringIndexOutOfBoundsException e){
                        String[] temp = e.getMessage().split(";");
                        temp = temp[0].split("=");
                        Log.d("DRG","" + e.getMessage() + " :: HistoryAmountTextWatcher,java:65");
                        Log.d("DRG","text = " + temp[1] + " :: HistoryAmountTextWatcher,java:65");
                        int len = Integer.parseInt(temp[1]);
                        amountText.setText(String.format("%s", text.substring(0, len - 1)));
                        checkChanges = true;

                    }

                }else{
                    checkChanges = false;
                }

            }


        }



    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private String processOperator(String inputString){
        char operator = getOperator(inputString);
        String[] parts = splitOperator(inputString);
        assert parts != null;
        long sumAmount;
        try{
            switch (operator){

                case '+':
                    //Log.d("DRG","parts : " + parts[0]+ "& " + parts[1]);
                    inputString = (sumAmount =(Long.parseLong(parts[0]) + Long.parseLong(parts[1]))) >= 0 ? Long.toString(sumAmount) : "0" ;
                    break;
                case '-':
                    inputString = (sumAmount =(Long.parseLong(parts[0]) - Long.parseLong(parts[1]))) >= 0 ? Long.toString(sumAmount) : "0" ;
                    break;
                case 'x':
                    inputString = (sumAmount =(Long.parseLong(parts[0]) * Long.parseLong(parts[1]))) >= 0 ? Long.toString(sumAmount) : "0" ;
                    break;
                case '÷':
                    inputString = (sumAmount =(Long.parseLong(parts[0]) / Long.parseLong(parts[1]))) >= 0 ? Long.toString(sumAmount) : "0" ;;
                    break;
                default:
                    break;
            }
        }catch(NumberFormatException e){
            Log.d("DRG", e + " :: HistoryAmountTextWatcher :: line - 121");
        }

        Log.d("DRG","partsSum : " + inputString);
        return inputString;
    }

    private String[] splitOperator(String inputString){
        if(inputString.contains("+"))
            return inputString.split("[+]");
        else if(inputString.contains("-"))
            return inputString.split("[-]");
        else if(inputString.contains("x"))
            return inputString.split("[x]");
        else if(inputString.contains("÷"))
            return inputString.split("[÷]");
        else return null;
    }

    private char getOperator(String inputString){
        if(inputString.contains("+"))
            return '+';
        else if(inputString.contains("-"))
            return '-';
        else if(inputString.contains("x"))
            return 'x';
        else if(inputString.contains("÷"))
            return '÷';
        else return '!';

    }

}
