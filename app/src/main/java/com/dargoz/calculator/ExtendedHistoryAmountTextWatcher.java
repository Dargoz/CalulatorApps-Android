package com.dargoz.calculator;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExtendedHistoryAmountTextWatcher implements TextWatcher {
    private static int operatorCount = 0;
    private MainActivity parentActivity;
    private EditText amountText;
    private TextView historyText;
    private boolean checkChanges = false;


    ExtendedHistoryAmountTextWatcher(Activity activity){
        parentActivity = (MainActivity) activity;
        this.amountText =  parentActivity.findViewById(R.id.amountText);
        this.historyText = parentActivity.findViewById(R.id.historyAmount);

    }


    public static void incrementOperatorCount(){
        operatorCount++;
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
                amountText.setText(String.format("Rp %s",text));

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
            }


        }



    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private String processOperator(String inputString){
        ArrayList<Character> operator = getAllOperators(inputString);
        //@Davin TODO : extending history mechanism ( 30-1-2018 )
        String[] parts = splitOperator(inputString);
        assert parts != null;
        int sumAmount;
        /*switch (operator){
            case '+':
                //Log.d("DRG","parts : " + parts[0]+ "& " + parts[1]);
                inputString = (sumAmount =(Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]))) >= 0 ? Integer.toString(sumAmount) : "0" ;
                break;
            case '-':
                inputString = (sumAmount =(Integer.parseInt(parts[0]) - Integer.parseInt(parts[1]))) >= 0 ? Integer.toString(sumAmount) : "0" ;
                break;
            case 'x':
                inputString = (sumAmount =(Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]))) >= 0 ? Integer.toString(sumAmount) : "0" ;
                break;
            case '÷':
                inputString = (sumAmount =(Integer.parseInt(parts[0]) / Integer.parseInt(parts[1]))) >= 0 ? Integer.toString(sumAmount) : "0" ;;
                break;
            default:
                break;
        }*/
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

    private ArrayList<Character> getAllOperators(String inputString){
        ArrayList<Character> operators = new ArrayList<>();
        for(Character symbol : inputString.toCharArray()){
            switch (symbol){
                case '+':
                    //Log.d("DRG","parts : " + parts[0]+ "& " + parts[1]);
                    operators.add(symbol);
                    break;
                case '-':
                    operators.add(symbol);
                    break;
                case 'x':
                    operators.add(symbol);
                    break;
                case '÷':
                    operators.add(symbol);
                    break;
                default:
                    break;
            }

        }

        return operators;
    }

}
