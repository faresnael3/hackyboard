package com.example.hackyboard;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button allowAccessibility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button setting = (Button) findViewById(R.id.setting);
        allowAccessibility = findViewById(R.id.accessibilityButton);
        setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Context ctx= getApplicationContext();
                ctx.startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
            }
        });
        allowAccessibility.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity((new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)));
            }
        });
    }

}