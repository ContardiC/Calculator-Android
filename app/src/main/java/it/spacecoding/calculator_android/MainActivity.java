package it.spacecoding.calculator_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mariuszgromada.math.mxparser.Expression;

import it.spacecoding.calculator_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    String number = null;
    int countOpenPar = 0;
    int countClosePar = 0;
    boolean operator = false;
    boolean dotControl = false;
    String result = "";
    boolean buttonEqualsControl = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.tvResult.setText("0");
        mainBinding.btn0.setOnClickListener(v -> {
            onNumberClicked("0");
        });
        mainBinding.btn1.setOnClickListener(v -> {
            onNumberClicked("1");
        });
        mainBinding.btn2.setOnClickListener(v -> {
            onNumberClicked("2");
        });
        mainBinding.btn3.setOnClickListener(v -> {
            onNumberClicked("3");
        });
        mainBinding.btn4.setOnClickListener(v -> {
            onNumberClicked("4");
        });
        mainBinding.btn5.setOnClickListener(v -> {
            onNumberClicked("5");
        });
        mainBinding.btn6.setOnClickListener(v -> {
            onNumberClicked("6");
        });
        mainBinding.btn7.setOnClickListener(v -> {
            onNumberClicked("7");
        });
        mainBinding.btn8.setOnClickListener(v -> {
            onNumberClicked("8");
        });
        mainBinding.btn9.setOnClickListener(v -> {
            onNumberClicked("9");
        });
        mainBinding.btnOpenPar.setOnClickListener(v -> {
            onParClicked("(");
            countOpenPar++;
        });
        mainBinding.btnClosePar.setOnClickListener(v -> {
            if(countOpenPar > countClosePar) {
                onParClicked(")");
                countClosePar++;
            }
        });
        mainBinding.btnPlus.setOnClickListener(v -> {
            if(!operator && !dotControl){
                if(number == null){
                    number = "0+";
                }else if(buttonEqualsControl){
                    number = result + "+";
                } else{
                    number += "+";
                }
                mainBinding.tvResult.setText(number);
                operator = true;
                dotControl = true;
                buttonEqualsControl = false;
            }
        });
        mainBinding.btnMinus.setOnClickListener(v -> {
            if(!operator && !dotControl){
                if(number == null){
                    number = "0+";
                }else if(buttonEqualsControl){
                    number = result + "-";
                } else{
                    number += "-";
                }
                mainBinding.tvResult.setText(number);
                operator = true;
                dotControl = true;
                buttonEqualsControl = false;
            }
        });
        mainBinding.btnDivide.setOnClickListener(v -> {
            if(!operator && !dotControl){
                if(number == null){
                    number = "0+";
                }else if(buttonEqualsControl){
                    number = result + "/";
                } else{
                    number += "/";
                }
                mainBinding.tvResult.setText(number);
                operator = true;
                dotControl = true;
                buttonEqualsControl = false;
            }
        });
        mainBinding.btnMulti.setOnClickListener(v -> {
            if(!operator && !dotControl){
                if(number == null){
                    number = "0+";
                }else if(buttonEqualsControl){
                    number = result + "*";
                } else{
                    number += "*";
                }
                mainBinding.tvResult.setText(number);
                operator = true;
                buttonEqualsControl = false;
            }
        });
        mainBinding.btnDot.setOnClickListener(v -> {
            if(buttonEqualsControl){
                if(!result.contains(".")){
                    number = result + ".";
                    mainBinding.tvResult.setText(number);
                    dotControl = true;
                    buttonEqualsControl = false;
                }

            }else{

            }



            if(!dotControl && !operator){
                if(number == null){
                    number = "0.";
                    mainBinding.tvResult.setText(number);
                    dotControl = true;
                    operator = true;
                }else{
                    String expressionAfterLast = "";
                    String lastCharacter;
                    // I insert a label that I need to end the loop
                    dotLoop: for(int i = number.length() - 1; i >=0;i --){
                        lastCharacter = String.valueOf(number.charAt(i));
                        switch (lastCharacter){
                            case "+": case "-": case "/": case "*":
                                break dotLoop;
                            default:
                                expressionAfterLast = lastCharacter.concat(expressionAfterLast);
                                break;
                        }
                    }
                    if(!expressionAfterLast.contains(".")){
                        number += ".";
                        mainBinding.tvResult.setText(number);
                        dotControl = true;
                        operator = true;
                    }
                }

            }
        });
        mainBinding.btnAC.setOnClickListener(v->{
            onButtonACClicked();
        });
        mainBinding.btnDel.setOnClickListener(v->{
            if(number == null || number.length()==1){
                onButtonACClicked();
            }else{
                String lastChar = String.valueOf(number.charAt(number.length()-1));
                switch (lastChar){
                    case "+": case "-": case "/": case "*": case ".":
                        operator = false;
                        dotControl = false;
                        break;
                    case "(":
                        countOpenPar--;
                        break;
                    case ")":
                        countClosePar--;
                        break;
                }
                number = number.substring(0,number.length()-1);
                mainBinding.tvResult.setText(number);

                lastChar = String.valueOf(number.charAt(number.length()-1));
                switch (lastChar){
                    case "+": case "-": case "/": case "*": case ".":
                        operator = true;
                        dotControl = true;
                        break;
                }
            }
        });
        mainBinding.btnEquals.setOnClickListener(v->{

            String expressionForCalculate = mainBinding.tvResult.getText().toString();

            int difference = countOpenPar - countClosePar;
            for(int i = 0; i < difference; i++){
                expressionForCalculate = expressionForCalculate.concat(")");
            }
            Expression expression = new Expression(expressionForCalculate);
            result = String.valueOf(expression.calculate());

            if (result.equals("NaN")){
                checkDivisor(expressionForCalculate);
            }else{
                int indexOfDot = result.indexOf(".");
                String expressionAfterDot = result.substring(indexOfDot+1);
                if(expressionAfterDot.equals("0")){
                    result = result.substring(0,indexOfDot);
                }
                mainBinding.tvHistory.setText(result);
                mainBinding.tvHistory.setText(expressionForCalculate.concat(" = ").concat(result));
                buttonEqualsControl = true;
                operator = false;
                dotControl = false;
                countOpenPar = 0;
                countClosePar = 0;
            }

        });
        mainBinding.toolbar.setOnMenuItemClickListener(item->{
            if(item.getItemId() == R.id.settingsItem){
                // intent
                Intent intent = new Intent(MainActivity.this, ChangeThemeActivity.class);
                startActivity(intent);
                return true;
            }else{
                return false;
            }
        });
    }

    public void onNumberClicked(String clickedNumber){
        if(number == null || buttonEqualsControl){
            number = clickedNumber;
        }else{
            number += clickedNumber;
        }
        mainBinding.tvResult.setText(number);
        operator = false;
        dotControl = false;
        buttonEqualsControl = false;
    }
    public void onParClicked(String par){
        if(number == null || buttonEqualsControl){
            number = par;
        }else{
            number += par;
        }
        mainBinding.tvResult.setText(number);
        buttonEqualsControl = false;
    }
    public void onButtonACClicked(){
        number = null;
        mainBinding.tvResult.setText("0");
        mainBinding.tvHistory.setText("");
        countOpenPar = 0;
        countClosePar = 0;
        operator = false;
        dotControl = false;
        buttonEqualsControl = false;
        result = "";
    }
    public void checkDivisor(String expressionForCalculate){
        if(expressionForCalculate.contains("/")){
            int indexOfSlash = expressionForCalculate.indexOf("/");
            String expressionAfterSlash = expressionForCalculate.substring(indexOfSlash+1);
            if(expressionAfterSlash.contains(")")){
                int closingPar = 0;
                int openingPar = 0;
                for(int i = 0; i < expressionAfterSlash.length(); i++){
                    String isPar = String.valueOf(expressionAfterSlash.charAt(i));
                    if(isPar.equals("(")){
                        openingPar++;
                    }else if(isPar.equals(")")){
                        closingPar++;
                    }
                }
                int difference =  closingPar - openingPar;
                if(difference > 0){
                    for(int i = 0; i < difference; i++){
                        expressionAfterSlash = "(".concat(expressionAfterSlash);
                    }
                }
            }
            Expression expression = new Expression(expressionAfterSlash);
            String newResult = String.valueOf(expression.calculate());
            if(newResult.equals("0.0")){
                mainBinding.tvHistory.setText("The divisor cannot be zero");
            }else{
                checkDivisor(expressionAfterSlash);
            }
        }else{
            mainBinding.tvResult.setText("Syntax error");
        }
    }

}