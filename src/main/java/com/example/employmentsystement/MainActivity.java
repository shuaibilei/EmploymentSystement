package com.example.employmentsystement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Boolean ifvisible = false;
    private EditText password;
    private EditText count;
    private CheckBox rememberpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView pwswitch = findViewById(R.id.if_visible);
        password = findViewById(R.id.input_password);
        count = findViewById(R.id.input_count);
        rememberpw = findViewById(R.id.if_remember_pw);
        Button btLogin = findViewById(R.id.login);




        pwswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifvisible = !ifvisible;
                if (ifvisible){
                    pwswitch.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else{
                    pwswitch.setImageResource(R.drawable.ic_baseline_visibility_24);
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD |InputType.TYPE_CLASS_TEXT);
                }
            }
        });


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 SharedPreferences shared = getSharedPreferences("strings", MODE_PRIVATE);
                 SharedPreferences.Editor editor = shared.edit();
                 if(count.getText().toString().equals("18003") && password.getText().toString().equals("123456")) {
                     if (rememberpw.isChecked()) {
                         editor.putString("user_count", count.getText().toString());
                         editor.putString("user_password", password.getText().toString());
                         editor.putBoolean("ifRememberPw", true);
                         editor.commit();
                     } else {
                         editor.remove("user_count");
                         editor.remove("user_password");
                         editor.remove("ifRememberPw");
                         editor.commit();
                     }
                     Toast.makeText(MainActivity.this,"登陆成功!",Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(MainActivity.this, Home.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                     startActivity(intent);
                 }
                 else {
                     Toast.makeText(MainActivity.this,"登录失败!",Toast.LENGTH_SHORT).show();
                 }
            }
        });

        SharedPreferences mshared = getSharedPreferences("strings", MODE_PRIVATE);
        String mcount = mshared.getString("user_count",null);
        String mpassword = mshared.getString("user_password", null);
        Boolean mrememberpw = mshared.getBoolean("ifRememberPw", false);

        count.setText(mcount);
        password.setText(mpassword);
        rememberpw.setChecked(mrememberpw);

    }
}