package com.example.paz.paz_le2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    Button loadPreference, loadStorage, clear, next;
    EditText displayET;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        loadPreference = (Button)findViewById(R.id.loadPreference);
        loadStorage = (Button)findViewById(R.id.loadStorage);
        clear = (Button)findViewById(R.id.clear);
        next = (Button)findViewById(R.id.next);
        displayET = (EditText)findViewById(R.id.displayET);
    }

    public void LoadPreferences(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String  user = sharedPreferences.getString("etUser", "08:00") ;
        String  password = sharedPreferences.getString("etPassword", "08:00") ;
        displayET.setText("User:"+ user + " \npassword:" +password);
    }

    public void LoadStorage (View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while((read =fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayET.setText(buffer.toString());
    }
    public void clear(View view){
        displayET.setText("");
    }

    public void back(View view){
        Intent sd=new Intent(this,MainActivity.class);
        startActivity(sd);
    }
}
