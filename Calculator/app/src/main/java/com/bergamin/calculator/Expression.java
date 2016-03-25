package com.bergamin.calculator;

/**
 * Created by Guilherme on 25/03/2016.
 */
public class Expression {

    public double calculate(String expression){
        String[] sums = expression.replace("-","+-").split("\\+");
        double result = 0d;

        if(sums[0].equals("")) sums[0] = "0";

        result = multiplication(sums[0]);
        for(int i=1 ; i < sums.length ; i++){
            result = result + multiplication(sums[i]);
        }
        return result;
    }
    private double multiplication(String expression){
        String[] multiplications = expression.replace("/","*1/").split("×");
        double result = 0d;

        result = division(multiplications[0]);
        for(int i=1 ; i < multiplications.length ; i++){
            result = result * division(multiplications[i]);
        }
        return result;
    }
    private double division(String expression){
        String[] divisions = expression.split("÷");
        double result = 0d;

        result = Double.parseDouble(divisions[0]);
        for(int i=1 ; i < divisions.length ; i++){
            result = result / Double.parseDouble(divisions[i]);
        }
        return result;
    }
}
