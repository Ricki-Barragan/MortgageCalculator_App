package edu.sjsu.android.mortgagecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText principal;
    private TextView textView;
    private float progressValue = 10.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.percent);
        SeekBar seekBar = (SeekBar) findViewById((R.id.seekBar));
        principal = (EditText) findViewById(R.id.principalField);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = (float) progress / 100;
                seekBar.setProgress(progress);
                textView.setText(progressValue + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void onClick(View view) {
        if (view.getId() == R.id.calculate) {
            RadioButton button15 = (RadioButton) findViewById(R.id.radioButton15);
            RadioButton button20 = (RadioButton) findViewById(R.id.radioButton20);
            RadioButton button30 = (RadioButton) findViewById(R.id.radioButton30);
            CheckBox taxesBox = (CheckBox) findViewById(R.id.checkBox);
            TextView monthlyPayments = (TextView) findViewById(R.id.solution);// solution text


            if (principal.getText().length() == 0) {
                Toast.makeText(this, "Please enter a valid number",
                        Toast.LENGTH_LONG).show();
                return;
            }

            float inputPrincipal = Float.parseFloat(principal.getText().toString());

            float loanTerm;
            if (button15.isChecked()) {
                button15.setChecked(true);
                button20.setChecked(false);
                button30.setChecked(false);
                loanTerm = 15;
            } else if (button20.isChecked()) {
                button15.setChecked(false);
                button20.setChecked(true);
                button30.setChecked(false);
                loanTerm = 20;
            } else {
                button15.setChecked(false);
                button20.setChecked(false);
                button30.setChecked(true);
                loanTerm = 30;
            }

            boolean tax = taxesBox.isChecked();

            monthlyPayments.setText(MortgageUtil.calculateMortgage(inputPrincipal,
                    progressValue, loanTerm, tax));
        }
    }

}