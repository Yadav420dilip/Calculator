package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.evgenii.jsevaluator.JsEvaluator;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static MainActivity instance;

    private boolean isInverse = false;

    private String inputToBeDisplay ="";
    private String inputToBeParsed ="";
    private String pev_inputToBeParsed="";
    private String pev_value="";

    private SpannableString sin_inverse;
    private SpannableString cos_inverse;
    private SpannableString tan_inverse;
    private SpannableString sinh_inverse;
    private SpannableString cosh_inverse;
    private SpannableString tanh_inverse;

    DatabaseHandler dbhandler;



    Button btn_zero, btn_one, btn_two, btn_three, btn_four, btn_five, btn_six,
            btn_seven, btn_eight, btn_nine, btn_sign_change, btn_decimal, btn_equal, btn_clear,
            btn_mod, btn_division, btn_multiplication, btn_subtraction, btn_addition,
            btn_inv_fun,btn_open_bracket,btn_close_bracket,btn_rad_deg,btn_sin,
            btn_cos,btn_tan, btn_pow_expo, btn_sinh,btn_cosh, btn_tanh, btn_natural_log,
            btn_pow_2, btn_fact, btn_expo, btn_pow_10, btn_sqr_root, btn_inverse,btn_pi,btn_log;

    ImageButton  btn_edit_text;

    EditText textview;
    JsEvaluator jsEvaluator ;

    EvaluateValue mCalculator;



    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_zero = findViewById(R.id.btn_zero);
        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_seven = findViewById(R.id.btn_seven);
        btn_eight = findViewById(R.id.btn_eight);
        btn_nine = findViewById(R.id.btn_nine);
        btn_sign_change = findViewById(R.id.btn_sign_change);
        btn_decimal = findViewById(R.id.btn_decimal);
        btn_equal = findViewById(R.id.btn_equal);
        btn_clear = findViewById(R.id.btn_clear);
        btn_edit_text = findViewById(R.id.btn_edit_text);
        btn_mod = findViewById(R.id.btn_mod);
        btn_division = findViewById(R.id.btn_division);
        btn_multiplication = findViewById(R.id.btn_multiplication);
        btn_subtraction = findViewById(R.id.btn_subtraction);
        btn_addition = findViewById(R.id.btn_addition);
        btn_inv_fun = findViewById(R.id.btn_inv_fun);
        btn_open_bracket = findViewById(R.id.btn_open_bracket);
        btn_close_bracket = findViewById(R.id.btn_close_bracket);
        btn_rad_deg = findViewById(R.id.btn_rad_deg);
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_tan = findViewById(R.id.btn_tan);
        btn_pow_expo = findViewById(R.id.btn_pow_expo);
        btn_sinh = findViewById(R.id.btn_sinh);
        btn_cosh = findViewById(R.id.btn_cosh);
        btn_tanh = findViewById(R.id.btn_tanh);
        btn_natural_log = findViewById(R.id.btn_natural_log);
        btn_pow_2 = findViewById(R.id.btn_pow_2);
        btn_fact = findViewById(R.id.btn_fact);
        btn_expo = findViewById(R.id.btn_expo);
        btn_pow_10 = findViewById(R.id.btn_pow_10);
        btn_sqr_root = findViewById(R.id.btn_sqr_root);
        btn_inverse = findViewById(R.id.btn_inverse);
        btn_pi = findViewById(R.id.btn_pi);
        btn_log = findViewById(R.id.btn_log);

        textview = findViewById(R.id.textview);
        instance = this;

        jsEvaluator = new JsEvaluator(this);

        if (savedInstanceState != null)
        {
            inputToBeDisplay=savedInstanceState.getString("inputToBeDisplay");
            textview.setText(inputToBeDisplay);
            inputToBeParsed = savedInstanceState.getString("inputToBeParsed");

        }




        sin_inverse =new SpannableString("sin-1");
        sin_inverse.setSpan(new SuperscriptSpan(), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sin_inverse.setSpan(new RelativeSizeSpan(0.75f), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        cos_inverse =new SpannableString("cos-1");
        cos_inverse.setSpan(new SuperscriptSpan(), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cos_inverse.setSpan(new RelativeSizeSpan(0.75f), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tan_inverse =new SpannableString("tan-1");
        tan_inverse.setSpan(new SuperscriptSpan(), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tan_inverse.setSpan(new RelativeSizeSpan(0.75f), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        sinh_inverse =new SpannableString("sinh-1");
        sinh_inverse.setSpan(new SuperscriptSpan(), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sinh_inverse.setSpan(new RelativeSizeSpan(0.75f), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        cosh_inverse =new SpannableString("cosh-1");
        cosh_inverse.setSpan(new SuperscriptSpan(), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cosh_inverse.setSpan(new RelativeSizeSpan(0.75f), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tanh_inverse =new SpannableString("tanh-1");
        tanh_inverse.setSpan(new SuperscriptSpan(), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tanh_inverse.setSpan(new RelativeSizeSpan(0.75f), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mCalculator = new EvaluateValue();

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String value = mCalculator.getResult(inputToBeParsed);

                if (pev_inputToBeParsed.equals(inputToBeParsed) || pev_value.equals(value))
                {

                }
                else
                {
                    pev_inputToBeParsed=inputToBeParsed;
                    pev_value=value;
                    dbhandler = new DatabaseHandler(instance);

                    dbhandler.add_result(inputToBeDisplay,value);
                }



                textview.setText(value);
            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void buttonClick(View v) {

        switch (v.getId()) {
            case R.id.btn_zero:
               inputToBeDisplay+="0";
               inputToBeParsed+="0";
                break;

            case R.id.btn_one:
                inputToBeDisplay+="1";
                inputToBeParsed+="1";
                break;


            case R.id.btn_two:
                inputToBeDisplay+="2";
                inputToBeParsed+="2";
                break;

            case R.id.btn_three:
                inputToBeDisplay+="3";
                inputToBeParsed+="3";
                break;

            case R.id.btn_four:
                inputToBeDisplay+="4";
                inputToBeParsed+="4";
                break;

            case R.id.btn_five:
                inputToBeDisplay+="5";
                inputToBeParsed+="5";
                break;

            case R.id.btn_six:
                inputToBeDisplay+="6";
                inputToBeParsed+="6";
                break;

            case R.id.btn_seven:
                inputToBeDisplay+="7";
                inputToBeParsed+="7";
                break;

            case R.id.btn_eight:
                inputToBeDisplay+="8";
                inputToBeParsed+="8";
                break;

            case R.id.btn_nine:
                inputToBeDisplay+="9";
                inputToBeParsed+="9";
                break;

            case R.id.btn_sign_change:
                String text_view_equation = textview.getText().toString();

                if (text_view_equation.equals(""))
                {
                    break;
                }

                else if (text_view_equation.charAt(0)=='-') {
                    inputToBeDisplay = inputToBeDisplay.substring(1);
                    inputToBeParsed = inputToBeParsed.substring(1);

                    //textview.setText(text_view_equation.substring(1));
                }

                else
                {
                    inputToBeDisplay="-"+inputToBeDisplay;
                    inputToBeParsed="-"+inputToBeParsed;

                    //textview.setText("-" + text_view_equation);
                }
                break;

            case R.id.btn_decimal:
                inputToBeDisplay+=".";
                inputToBeParsed+=".";
                break;

            /*case  R.id.btn_equal:

              int abc = EvaluateString.evaluate(textview.getText().toString());

              textview.setText(Integer.toString(abc));

                break;*/

            case R.id.btn_clear:
                inputToBeDisplay="";
                inputToBeParsed="";
                break;

            case R.id.btn_edit_text:

                if (inputToBeParsed != null && inputToBeParsed.length() > 0){

                    inputToBeParsed = inputToBeParsed.substring(0,inputToBeParsed.length()-1);
                    inputToBeDisplay = inputToBeDisplay.substring(0,inputToBeDisplay.length()-1);

                }
                else
                    {
                        break;
                    }


                break;

            case  R.id.btn_mod:
                inputToBeDisplay+="%";
                inputToBeParsed+="%";
                break;

            case  R.id.btn_division:
                inputToBeDisplay+="/";
                inputToBeParsed+="/";
                break;

            case  R.id.btn_multiplication:
                inputToBeDisplay+="×";
                inputToBeParsed+="*";
                break;

            case  R.id.btn_subtraction:
                inputToBeDisplay+="-";
                inputToBeParsed+="-";
                break;

            case  R.id.btn_addition:
                inputToBeDisplay+="+";
                inputToBeParsed+="+";
                break;

            case  R.id.btn_inv_fun:

                isInverse= !isInverse;
                inverse_trigo_fun();


                break;

            case  R.id.btn_open_bracket:
                inputToBeDisplay+="(";
                inputToBeParsed+="(";
                break;

            case  R.id.btn_close_bracket:
                inputToBeDisplay+=")";
                inputToBeParsed+=")";
                break;

            case  R.id.btn_rad_deg:

                EvaluateValue.isRadians =!EvaluateValue.isRadians;
                break;

            case  R.id.btn_sin:

                if(isInverse){

                    inputToBeDisplay+="asin(";
                    inputToBeParsed+="asin(";

                    Log.d("hello wowrld","hello world");

                }else{

                    inputToBeDisplay+="sin(";
                    inputToBeParsed+="sin(";

                }
                break;

            case  R.id.btn_cos:
                if(isInverse){

                    inputToBeDisplay+="acos(";
                    inputToBeParsed+="acos(";

                }else {

                    inputToBeDisplay+="cos(";
                    inputToBeParsed+="cos(";
                }
                break;

            case  R.id.btn_tan:
                if(isInverse){

                    inputToBeDisplay+="atan(";
                    inputToBeParsed+="atan(";

                }else {

                    inputToBeDisplay+="tan(";
                    inputToBeParsed+="tan(";
                }
                break;

            case  R.id.btn_pow_expo:
                inputToBeDisplay+="e^";
                inputToBeParsed+="e^";
                break;

            case  R.id.btn_sinh:
                if(isInverse){

                    inputToBeDisplay+="asinh(";
                    inputToBeParsed+="asinh(";

                }else {

                    inputToBeDisplay+="sinh(";
                    inputToBeParsed+="sinh(";
                }
                break;

            case  R.id.btn_cosh:
                if(isInverse){

                    inputToBeDisplay+="acosh(";
                    inputToBeParsed+="acosh(";

                }else {

                    inputToBeDisplay+="cosh(";
                    inputToBeParsed+="cosh(";

                }
                break;

            case  R.id.btn_tanh:
                if(isInverse){

                    inputToBeDisplay+="atanh(";
                    inputToBeParsed+="atanh(";

                }else {

                    inputToBeDisplay+="tanh(";
                    inputToBeParsed+="tanh(";
                }
                break;

            case  R.id.btn_natural_log:
                inputToBeDisplay+="ln(";
                inputToBeParsed+="ln(";
                break;


            case  R.id.btn_pow_2:
                if (inputToBeParsed.isEmpty())
                {

                }
                else
                {
                    inputToBeDisplay+="^2";
                    inputToBeParsed+="^2";
                }
                break;

            case  R.id.btn_fact:
                //char[] try1 = inputToBeParsed.toCharArray();
                for (int i = inputToBeParsed.length()-1; i>=0; i--)
                    if (Character.isDigit(inputToBeParsed.charAt(i)))
                    {
                        if(i==0)
                        {
                            inputToBeParsed ="!("+inputToBeParsed+")";
                        }
                        continue;
                    }

                    else
                    {
                        inputToBeParsed = inputToBeParsed.substring(0, i+1) + "!(" + inputToBeParsed.substring(i+1)+")";
                        break;
                    }
                inputToBeDisplay = inputToBeDisplay + "!";
                    break;

            case  R.id.btn_expo:
                inputToBeDisplay+="e";
                inputToBeParsed+="e";
                break;

            case  R.id.btn_pow_10:
                inputToBeDisplay+="10^";
                inputToBeParsed+="10^";
                break;

            case  R.id.btn_sqr_root:
                inputToBeDisplay+="√(";
                inputToBeParsed+="sqrt(";
                break;

            case  R.id.btn_inverse:
                inputToBeDisplay+="1/";
                inputToBeParsed+="1/";
                break;

            case  R.id.btn_pi:
                inputToBeDisplay+="π ";
                inputToBeParsed+="pi";
                break;

            case  R.id.btn_log:
                inputToBeDisplay+="log(";
                inputToBeParsed+="log(";
                break;


        }
        textview.setText(inputToBeDisplay);

    }


    @SuppressLint("SetTextI18n")
    public void inverse_trigo_fun()
    {
        if (isInverse)
        {

            btn_sin.setText(sin_inverse);
            btn_cos.setText(cos_inverse);
            btn_tan.setText(tan_inverse);
            btn_sinh.setText(sinh_inverse);
            btn_cosh.setText(cosh_inverse);
            btn_tanh.setText(tanh_inverse);
        }
        else {
            btn_sin.setText("sin");
            btn_cos.setText("cos");
            btn_tan.setText("tan");
            btn_sinh.setText("sinh");
            btn_cosh.setText("cosh");
            btn_tanh.setText("tanh");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.history_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputToBeDisplay", inputToBeDisplay);
        outState.putString("inputToBeParsed", inputToBeParsed);

    }

    @SuppressLint("ShowToast")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.history) {
            Intent intent = new Intent(this,history.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
