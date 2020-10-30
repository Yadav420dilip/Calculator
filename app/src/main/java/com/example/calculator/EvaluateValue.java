package com.example.calculator;

import android.annotation.SuppressLint;

import com.evgenii.jsevaluator.JsEvaluator;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Parameters;

import java.util.Iterator;

@SuppressLint("Registered")
public class EvaluateValue {
    // The function has one argument and its name is "sqrt"
    private final Function sqrt = new Function("sqrt", 1);
    private final Function factorial = new Function("!", 1);
    private final Function cuberoot = new Function("crt", 1);
    private final Function sinh = new Function("sinh", 1);
    private final Function cosh = new Function("cosh", 1);
    private final Function tanh = new Function("tanh", 1);
    private final Function asinh = new Function("asinh", 1);
    private final Function acosh = new Function("acosh", 1);
    private final Function atanh = new Function("atanh", 1);
    private final Function sin = new Function("sin", 1);
    private final Function cos = new Function("cos", 1);
    private final Function tan = new Function("tan", 1);
    private final Function asin = new Function("asin", 1);
    private final Function acos = new Function("acos", 1);
    private final Function atan= new Function("atan", 1);




    private Parameters params;
    private DoubleEvaluator evaluator;
    private double previousSum = 0;
    private double currentSum = 0;
    private String currentDisplay = "";
    private static String var="";
    //private String expressionUsedForParsing ="";
    static boolean isRadians = true;

    JsEvaluator jsEvaluator;


    public EvaluateValue() {
        addFunctions();

        //Adds the functions to the evaluator
        evaluator = new DoubleEvaluator(params) {
            @Override
            protected Double evaluate(Function function, Iterator arguments, Object evaluationContext) {

                if (function == sqrt)
                    return Math.sqrt((Double) arguments.next());
                else if(function == cuberoot){

                    return Math.cbrt((Double) arguments.next());
                }
                else if (function == factorial) {
                    double result = 1;
                    double num = (Double) arguments.next();
                    for (int i = 2; i <= num; i++) {
                        result = result * i;
                    }
                    return result;
                }

                else if(function == sinh)
                {
                    return Double.parseDouble(String.valueOf(Math.sinh(convertToRadians((Double) arguments.next()))));
                }
                else if(function == cosh)
                {
                    return Double.parseDouble(String.valueOf(Math.cosh(convertToRadians((Double) arguments.next()))));
                }
                else if(function == tanh)
                {
                    return Double.parseDouble(String.valueOf(Math.tanh(convertToRadians((Double) arguments.next()))));
                }

                else if(function == asinh)
                {

                    double para=(Double)arguments.next();
                    return Double.parseDouble(String.valueOf(inverse_red_deg(Math.log(para + Math.sqrt(para * para + 1)))));
                }

                else if (function==acosh)
                {
                    double para=(Double)arguments.next();
                    return Double.parseDouble(String.valueOf(inverse_red_deg(Math.log(para + Math.sqrt(para * para - 1)))));
                }

                else if(function == atanh)
                {
                    double para=(Double)arguments.next();
                    return Double.parseDouble(String.valueOf(inverse_red_deg(Math.log((1 / para + 1) / (1 / para - 1)) / 2)));
                }
                else if(function == sin)
                {
                    return Double.parseDouble(String.valueOf(Math.sin(convertToRadians((Double) arguments.next()))));
                }
                else if(function == cos)
                {
                    return Double.parseDouble(String.valueOf(Math.cos(convertToRadians((Double) arguments.next()))));
                }
                else if(function == tan)
                {
                    return Double.parseDouble(String.valueOf(Math.tan(convertToRadians((Double) arguments.next()))));
                }
                else if(function == asin)
                {
                    return Double.parseDouble(String.valueOf(inverse_red_deg(Math.asin((Double) arguments.next()))));
                }
                else if(function == acos)
                {
                    return Double.parseDouble(String.valueOf(inverse_red_deg(Math.acos((Double) arguments.next()))));
                }
                else if(function == atan)
                {
                    return Double.parseDouble(String.valueOf(inverse_red_deg(Math.atan((Double) arguments.next()))));
                }

                else
                    return super.evaluate(function, arguments, evaluationContext);
            }
        };
    }


    private void addFunctions() {
        params = DoubleEvaluator.getDefaultParameters();
        params.add(sqrt);
        params.add(factorial);

        params.add(asinh);
        params.add(acosh);
        params.add(atanh);
        params.add(sinh);
        params.add(cosh);
        params.add(tanh);

        params.add(sin);
        params.add(cos);
        params.add(tan);
        params.add(asin);
        params.add(acos);
        params.add(atan);


    }
    public String getResult(String expressionUsedForParsing) {
        //Tries to parse the information as it is entered, if the parser can't handle it, the word error is shown on screen
        String currentDisplay;
        try {

            currentSum = evaluator.evaluate(fixExpression(expressionUsedForParsing));
            System.out.println("dada"+currentSum);
            //currentSum = convertToRadians(currentSum);
            currentDisplay = String.valueOf(currentSum);
            //previousSum = currentSum;
        } catch (Exception e) {
            currentDisplay = "Error";
        }

        return currentDisplay;
    }
    private double convertToRadians(double sum){
        double newSum = sum;
        System.out.println(newSum);
        if(isRadians)
            return newSum;

        return Math.toRadians(newSum);
    }

    private double inverse_red_deg(double sum)
    {
        double newsum=sum;
        if (isRadians)
        {
            return newsum;
        }
        return Math.toDegrees(newsum);
    }
    //Used to show display to user
    public String getCurrentDisplay() {
        return currentDisplay;
    }
    //Handles fixing the expression before parsing. Adding parens, making sure parens can multiply with each other,
    private String fixExpression(String exp) {
        int openParens = 0;
        int closeParens = 0;
        char openP = '(';
        char closeP = ')';
        String expr = exp;
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == openP)
                openParens++;
            else if (exp.charAt(i) == closeP)
                closeParens++;
        }
        while (openParens > 0) {
            expr += closeP;
            openParens--;
        }
        while (closeParens > 0) {
            expr = openP + expr;
            closeParens--;
        }
        expr = multiplicationForParens(expr);
        return expr;
    }
    //Used to fix multiplication between parentheses
    private String multiplicationForParens(String s) {
        String fixed = "";
        for (int position = 0; position < s.length(); position++) {
            fixed += s.charAt(position);
            if (position == s.length() - 1)
                continue;
            if (s.charAt(position) == ')' && s.charAt(position + 1) == '(')
                fixed += '*';
            if (s.charAt(position) == '(' && s.charAt(position + 1) == ')')
                fixed += '1';
        }
        return fixed;
    }




}
