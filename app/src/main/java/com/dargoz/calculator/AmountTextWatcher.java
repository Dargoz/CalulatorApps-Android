package com.dargoz.calculator;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/*
Author : Davin Reinaldo Gozali
Created Date :
Last Modified Date : 07 February 2019
 */

public class AmountTextWatcher implements TextWatcher {
    private MainActivity parentActivity;
    private EditText amountText;
    private TextView historyText;
    private boolean checkChanges = false;
    private boolean checkChangesToHistory = false;


    AmountTextWatcher(Activity activity){
        parentActivity = (MainActivity) activity;
        this.amountText =  parentActivity.findViewById(R.id.amountText);
        this.historyText = parentActivity.findViewById(R.id.historyAmount);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        int newLength = start+count;
        if(newLength > 0){
            //String text = amountText.getText().toString().substring(3,newLength);
            String text = amountText.getText().toString();

            text = CheckAmountText.cleanDotFromString(text);
            text = processingOperatorSemanticOf(text);
            text = CheckAmountText.formatStringForAmount(text);
            Logger.isDebugEnabled(false);
            if(!checkChanges) {
                checkChanges = true;
                if(CheckAmountText.isOperator(text.charAt(text.length()-1))) {
                    Log.i("DRG","Logger :: text contain operator pre:: " + text);
                    text = text.substring(0, text.length() - 1);
                    Log.i("DRG","Logger :: text contain operator post:: " + text);
                }
                Logger.i("DRG","text not contain operator pre:: " + text);
                amountText.setText(String.format("%s", text));
            }
            else
                checkChanges = false;

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {
        try{
            if(amountText.length() > 0 )
                amountText.setSelection(amountText.length());
        }catch (IndexOutOfBoundsException e){
            Log.d("DRG","IndexOutOfBoundException :: in Length of : " + amountText.length());
        }

    }



    private String processingOperatorSemanticOf(String text){
        int textLength = text.length();
        if(!checkChangesToHistory && !parentActivity.isContainHistory()){
            if(CheckAmountText.isOperator(text.charAt(textLength-1))){
                checkChangesToHistory = true;
                historyText.append(text);
                text = text.substring(0,textLength-1);
                parentActivity.setContainHistory(true);
            }
        }else{
            checkChangesToHistory = false;
        }

        return text;
    }
}
