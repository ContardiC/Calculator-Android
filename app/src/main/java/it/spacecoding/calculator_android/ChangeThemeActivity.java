package it.spacecoding.calculator_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import it.spacecoding.calculator_android.databinding.ActivityChangeThemeBinding;

public class ChangeThemeActivity extends AppCompatActivity {
    ActivityChangeThemeBinding switchBinding;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switchBinding = ActivityChangeThemeBinding.inflate(getLayoutInflater());
        setContentView(switchBinding.getRoot());

        switchBinding.toolbarSettings.setNavigationOnClickListener(v-> {
            finish();
        });
        switchBinding.themeSwitch.setOnCheckedChangeListener((buttonView, isChecked)->{
            sharedPreferences = this.getSharedPreferences("it.spacecoding.calculator_android", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("darkMode", true);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("darkMode", false);
            }
            editor.apply();
        });

    }
    protected void onResume() {
        super.onResume();
        sharedPreferences = this.getSharedPreferences("it.spacecoding.calculator_android", Context.MODE_PRIVATE);
        switchBinding.themeSwitch.setChecked(sharedPreferences.getBoolean("darkMode", false));
    }
}