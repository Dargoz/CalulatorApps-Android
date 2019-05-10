package com.dargoz.calculator;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.zip.Inflater;

/*
Author : Davin Reinaldo Gozali
Date : 29 January 2019
 */

public class MainActivity extends AppCompatActivity {
    public static Handler handler;
    private boolean containHistory = false;
    public boolean isContainHistory(){
        return  containHistory;
    }

    public void setContainHistory(boolean bool){
        containHistory = bool;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mergeNumPadToView();
        buildViewFunctionality();
    }

    private void mergeNumPadToView(){
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout mainLayout = findViewById(R.id.mainConstraint);
        ImageView line = findViewById(R.id.imageView);
        View numPad = getLayoutInflater().inflate(R.layout.numpad,null);

        mainLayout.addView(numPad);
        numPad.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,ConstraintLayout.LayoutParams.WRAP_CONTENT));
        set.clone(mainLayout);
        set.connect(numPad.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
        set.connect(numPad.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT,0);
        set.connect(numPad.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM,0);
        set.connect(line.getId(),ConstraintSet.BOTTOM,numPad.getId(),ConstraintSet.TOP,0);
        set.applyTo(mainLayout);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void buildViewFunctionality(){
        handler = new Handler();
        ArrayList<Button> calculatorButtons = new ArrayList<>();

        calculatorButtons = addButtonListTo(calculatorButtons);
        ImageButton backspaceButton = findViewById(R.id.backspace);

        for(Button button : calculatorButtons){
            button.setOnClickListener(new CalculatorOnClickListener(this));
            button.setOnTouchListener(new CalculatorOnClickListener(this));

        }
        backspaceButton.setOnClickListener(new CalculatorOnClickListener(this));
        backspaceButton.setOnTouchListener(new CalculatorOnClickListener(this));

        final EditText amountText = findViewById(R.id.amountText);
        amountText.setInputType(InputType.TYPE_NULL); // disable soft input
        amountText.addTextChangedListener(new AmountTextWatcher(this));

        final TextView historyText = findViewById(R.id.historyAmount);
        historyText.addTextChangedListener(new HistoryAmountTextWatcher(this));

        final Button confirmButton = findViewById(R.id.confirmBtn);
        confirmButton.setOnClickListener(new ConfirmButtonOnClickListener(this));
    }


    private ArrayList<Button> addButtonListTo(ArrayList<Button> calculatorButtons){
        calculatorButtons.add((Button)findViewById(R.id.one));
        calculatorButtons.add((Button)findViewById(R.id.two));
        calculatorButtons.add((Button)findViewById(R.id.three));
        calculatorButtons.add((Button)findViewById(R.id.four));
        calculatorButtons.add((Button)findViewById(R.id.five));
        calculatorButtons.add((Button)findViewById(R.id.six));
        calculatorButtons.add((Button)findViewById(R.id.seven));
        calculatorButtons.add((Button)findViewById(R.id.eight));
        calculatorButtons.add((Button)findViewById(R.id.nine));
        calculatorButtons.add((Button)findViewById(R.id.zero));
        calculatorButtons.add((Button)findViewById(R.id.triple_zero));
        calculatorButtons.add((Button)findViewById(R.id.divide));
        calculatorButtons.add((Button)findViewById(R.id.multiply));
        calculatorButtons.add((Button)findViewById(R.id.minus));
        calculatorButtons.add((Button)findViewById(R.id.plus));

        return  calculatorButtons;
    }
}
