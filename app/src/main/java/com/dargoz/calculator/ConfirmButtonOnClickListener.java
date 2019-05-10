package com.dargoz.calculator;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ConfirmButtonOnClickListener implements View.OnClickListener {
    private MainActivity parentActivity;
    private EditText amountText;

    ConfirmButtonOnClickListener(Activity activity){
        this.parentActivity = (MainActivity) activity;
        amountText = parentActivity.findViewById(R.id.amountText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirmBtn:
                validateInputAmount();
                break;
                default:
                    break;
        }
    }

    private void validateInputAmount(){
        Log.i("DRG","OK Button Click");
        //parentActivity.finish();
    }
}
