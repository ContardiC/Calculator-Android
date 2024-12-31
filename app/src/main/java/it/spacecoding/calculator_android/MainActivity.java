package it.spacecoding.calculator_android;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import it.spacecoding.calculator_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    String number = null;
    int countOpenPar = 0;
    int countClosePar = 0;
    boolean operator = false;
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
            if(!operator){
                if(number == null){
                    number = "0+";
                }else{
                    number += "+";
                }
                mainBinding.tvResult.setText(number);
                operator = true;
            }
        });
        mainBinding.btnMinus.setOnClickListener(v -> {
            if(!operator){
                if(number == null){
                    number = "0+";
                }else{
                    number += "-";
                }
                mainBinding.tvResult.setText(number);
                operator = true;
            }
        });
        mainBinding.btnDivide.setOnClickListener(v -> {
            if(!operator){
                if(number == null){
                    number = "0+";
                }else{
                    number += "/";
                }
                mainBinding.tvResult.setText(number);
                operator = true;
            }
        });
        mainBinding.btnMulti.setOnClickListener(v -> {
            if(!operator){
                if(number == null){
                    number = "0+";
                }else{
                    number += "*";
                }
                mainBinding.tvResult.setText(number);
                operator = true;
            }
        });




    }

    public void onNumberClicked(String clickedNumber){
        if(number == null){
            number = clickedNumber;
        }else{
            number += clickedNumber;
        }
        mainBinding.tvResult.setText(number);
        operator = false;
    }
    public void onParClicked(String par){
        if(number == null){
            number = par;
        }else{
            number += par;
        }
        mainBinding.tvResult.setText(number);
    }

}