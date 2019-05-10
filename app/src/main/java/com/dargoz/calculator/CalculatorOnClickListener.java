package com.dargoz.calculator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/*
Author : Davin Reinaldo Gozali
Date : 29 January 2019
 */

public class CalculatorOnClickListener implements View.OnClickListener, View.OnTouchListener {
    private MainActivity parentActivity;
    private EditText amountText;
    private TextView historyText;

    CalculatorOnClickListener(Activity activity){
        parentActivity = (MainActivity) activity;
        amountText = parentActivity.findViewById(R.id.amountText);
        historyText = parentActivity.findViewById(R.id.historyAmount);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zero:
                setText("0");
                break;
            case R.id.triple_zero:
                setText("000");
                break;
            case R.id.one:
                setText("1");
                break;
            case R.id.two:
                setText("2");
                break;
            case R.id.three:
                setText("3");
                break;
            case R.id.four:
                setText("4");
                break;
            case R.id.five:
                setText("5");
                break;
            case R.id.six:
                setText("6");
                break;
            case R.id.seven:
                setText("7");
                break;
            case R.id.eight:
                setText("8");
                break;
            case R.id.nine:
                setText("9");
                break;
            case R.id.minus:
                setText("-");
                break;
            case R.id.plus:
                setText("+");
                break;
            case R.id.divide:
                setText("÷");

                break;
            case R.id.multiply:
                setText("x");
                break;
            case R.id.backspace:
                backspace();
                break;
                default:
                    break;

        }
    }

    private void setText(String inputString){
        if(parentActivity.isContainHistory())
            setHistoryText(inputString);
        else
            setAmountText(inputString);
    }

    private void setAmountText(String inputString){
        String amountTextString = amountText.getText().toString();
        int amountLength = amountTextString.length();
        if(amountLength == 0){
            if(!CheckAmountText.isOperator(inputString) && !inputString.equals("0") && !inputString.equals("000")) {
                Log.d("DRG","not operator");
                //amountText.append("Rp ");
                amountText.append(inputString);
            }
        }
        else if(inputString.equals("000")) {
            if(amountLength < 30)
                amountText.append(inputString);
        }else if(amountLength < 30) {
            if (!CheckAmountText.isOperator(amountTextString.charAt(amountLength - 1)) && CheckAmountText.isOperator(inputString)) {
                Log.d("DRG", "is number : " + amountTextString.charAt(amountLength - 1));
                amountText.append(inputString);
            }else if (!CheckAmountText.isOperator(inputString)) {
                amountText.append(inputString);
            }
        }else{
            Log.d("DRG","input string : " + inputString);
        }
    }

    private  void setHistoryText(String inputString){

        int newLength = amountText.getText().toString().length();
        if(CheckAmountText.isOperator(inputString)){
            String getLastAmount = amountText.getText().toString().substring(0,newLength);
            int lastAmountLength = getLastAmount.length();
            if(CheckAmountText.isOperator(getLastAmount.charAt(lastAmountLength-1)))
                getLastAmount = getLastAmount.substring(0,lastAmountLength-1);

            historyText.setText(String.format("%s%c",getLastAmount,inputString.charAt(0)));
        }else
            historyText.append(inputString);


    }

    private void backspace(){
        if(parentActivity.isContainHistory())
            backspaceHistory();
        else
            backspaceAmount();
    }

    private void backspaceAmount(){
        String amountTextString = amountText.getText().toString();
        int amountLength = amountTextString.length();
        if(amountLength > 0){
            if(!Character.isDigit(amountTextString.charAt(amountLength-1)))
                amountText.setText("");
            else
                amountText.setText(amountTextString.substring(0,amountLength-1));

        }
    }

    private void backspaceHistory(){
        String historyTextString = historyText.getText().toString();
        int historyLength = historyTextString.length();
        if(historyLength > 0){
            try {
                if(CheckAmountText.isOperator(historyTextString.charAt(historyLength-1))){
                    historyText.setText("");
                    parentActivity.setContainHistory(false);
                }
                else
                    historyText.setText(historyTextString.substring(0,historyLength-1));
            }catch (StringIndexOutOfBoundsException e){
                Log.d("DRG","StringIndexOutOfBoundsException :: " + e + " CalculatorOnClick Listener::167");
            }

        }

    }


    @Override
    public boolean onTouch(final View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN :{
                MainActivity.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        switch (view.getId()){
                            case R.id.divide:
                                view.setBackgroundResource(R.drawable.btn_bg);
                                ((Button)view).setTextColor(Color.WHITE);
                                break;
                            case R.id.multiply:
                                view.setBackgroundResource(R.drawable.btn_bg);
                                ((Button)view).setTextColor(Color.WHITE);
                                break;
                            case R.id.minus:
                                view.setBackgroundResource(R.drawable.btn_bg);
                                ((Button)view).setTextColor(Color.WHITE);
                                break;
                            case R.id.plus:
                                view.setBackgroundResource(R.drawable.btn_bg);
                                ((Button)view).setTextColor(Color.WHITE);
                                break;
                            default:
                                view.setBackgroundResource(R.drawable.button_bg_filled);
                                break;
                        }
                    }
                });
                return true;
            }
            case MotionEvent.ACTION_UP:{
                MainActivity.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        switch (view.getId()){
                            case R.id.divide:
                                view.setBackgroundResource(R.drawable.btn_bg_grey);
                                ((Button)view).setTextColor(parentActivity.getResources().getColor(R.color.bcaBlueIconColor));
                                break;
                            case R.id.multiply:
                                view.setBackgroundResource(R.drawable.btn_bg_grey);
                                ((Button)view).setTextColor(parentActivity.getResources().getColor(R.color.bcaBlueIconColor));
                                break;
                            case R.id.minus:
                                view.setBackgroundResource(R.drawable.btn_bg_grey);
                                ((Button)view).setTextColor(parentActivity.getResources().getColor(R.color.bcaBlueIconColor));
                                break;
                            case R.id.plus:
                                view.setBackgroundResource(R.drawable.btn_bg_grey);
                                ((Button)view).setTextColor(parentActivity.getResources().getColor(R.color.bcaBlueIconColor));
                                break;
                                default:
                                    view.setBackgroundResource(R.drawable.button_bg);
                                    break;
                        }

                        view.performClick();
                    }
                });
                return true;
            }

        }
        return false;
    }

}
