package com.bergamin.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Guilherme on 25/03/2016.
 */
public class CalculatorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        final TextView resultTxt = (TextView) findViewById(R.id.resultTxt);
        final TextView historyTxt = (TextView) findViewById(R.id.historyTxt);

        Button zeroBtn = (Button) findViewById(R.id.zeroBtn);
        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.equals("0")) resultTxt.setText(currentText + "0");
            }
        });

        Button oneBtn = (Button) findViewById(R.id.oneBtn);
        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.equals("0")) resultTxt.setText(currentText + "1");
                else resultTxt.setText("1");
            }
        });

        Button twoBtn = (Button) findViewById(R.id.twoBtn);
        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.equals("0")) resultTxt.setText(currentText + "2");
                else resultTxt.setText("2");
            }
        });

        Button threeBtn = (Button) findViewById(R.id.threeBtn);
        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.equals("0")) resultTxt.setText(currentText + "3");
                else resultTxt.setText("3");
            }
        });

        Button fourBtn = (Button) findViewById(R.id.fourBtn);
        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.equals("0")) resultTxt.setText(currentText + "4");
                else resultTxt.setText("4");
            }
        });

        Button fiveBtn = (Button) findViewById(R.id.fiveBtn);
        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.equals("0")) resultTxt.setText(currentText + "5");
                else resultTxt.setText("5");
            }
        });

        Button sixBtn = (Button) findViewById(R.id.sixBtn);
        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.equals("0")) resultTxt.setText(currentText + "6");
                else resultTxt.setText("6");
            }
        });

        Button sevenBtn = (Button) findViewById(R.id.sevenBtn);
        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.equals("0")) resultTxt.setText(currentText + "7");
                else resultTxt.setText("7");
            }
        });

        Button eightBtn = (Button) findViewById(R.id.eightBtn);
        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.equals("0")) resultTxt.setText(currentText + "8");
                else resultTxt.setText("8");
            }
        });

        Button nineBtn = (Button) findViewById(R.id.nineBtn);
        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if (!currentText.equals("0")) resultTxt.setText(currentText + "9");
                else resultTxt.setText("9");
            }
        });

        Button pointBtn = (Button) findViewById(R.id.pointBtn);
        pointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.contains(".") && isValid()) resultTxt.setText(currentText + ".");
            }
        });

        Button divisionBtn = (Button) findViewById(R.id.divisionBtn);
        divisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) resultTxt.setText(resultTxt.getText().toString() + "÷");
            }
        });

        Button multiplicationBtn = (Button) findViewById(R.id.multiplicationBtn);
        multiplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) resultTxt.setText(resultTxt.getText().toString() + "×");
            }
        });

        Button plusBtn = (Button) findViewById(R.id.plusBtn);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) resultTxt.setText(resultTxt.getText().toString() + "+");
            }
        });

        Button minusBtn = (Button) findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                if(!currentText.endsWith("+")
                        && !currentText.endsWith("-")
                        && !currentText.endsWith("."))
                    resultTxt.setText(currentText + "-");
            }
        });

        Button equalsBtn = (Button) findViewById(R.id.equalsBtn);
        equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Expression expression = new Expression();
                    String result = String.valueOf(expression.calculate(resultTxt.getText().toString()));

                    if (result.equals("0.0"))
                        result = "0";

                    historyTxt.setText(resultTxt.getText().toString());
                    resultTxt.setText(result);

                }catch(Exception e){
                    Toast.makeText(CalculatorActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button ceBtn = (Button) findViewById(R.id.ceBtn);
        ceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTxt.setText("0");
                historyTxt.setText("");
            }
        });

        Button cBtn = (Button) findViewById(R.id.cBtn);
        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (historyTxt.getText().toString().equals("")) {
                    resultTxt.setText("0");
                } else {
                    resultTxt.setText(historyTxt.getText().toString());
                    historyTxt.setText("");
                }
            }
        });

        Button bkspBtn = (Button) findViewById(R.id.bkspBtn);
        bkspBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = resultTxt.getText().toString();

                if (text.length() > 1)
                    text = text.substring(0, text.length() - 1);
                else
                    text = "0";

                resultTxt.setText(text);
            }
        });
        //will be changed for being added as many as needed in the formula
        Button powerBtn = (Button) findViewById(R.id.powerBtn);
        powerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                Expression expression = new Expression();

                historyTxt.setText("(" + currentText + ")²");
                resultTxt.setText(Double.toString(Math.pow(expression.calculate(currentText), 2)));
            }
        });
        //will be changed for being added as many as needed in the formula
        Button sqrtBtn = (Button) findViewById(R.id.sqrtBtn);
        sqrtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTxt.getText().toString();
                Expression expression = new Expression();

                historyTxt.setText("√("+currentText+")");
                resultTxt.setText(Double.toString(Math.sqrt(expression.calculate(currentText))));
            }
        });
    }

    public boolean isValid(){
        TextView resultTxt = (TextView) findViewById(R.id.resultTxt);
        String currentText = resultTxt.getText().toString();

        return !(currentText.endsWith("+")
                || currentText.endsWith("-")
                || currentText.endsWith("×")
                || currentText.endsWith("÷")
                || currentText.endsWith("."));
    }
}
