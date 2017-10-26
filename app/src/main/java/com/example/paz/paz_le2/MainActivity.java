package com.example.paz.paz_le2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {
    EditText etUser,etPassword;
    Button saveInternal, savePreferences, next;
    FileOutputStream fos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUser = (EditText)findViewById(R.id.etUser);
        etPassword = (EditText)findViewById(R.id.etPassword);
        saveInternal = (Button)findViewById(R.id.saveInternalStorage);
        savePreferences = (Button)findViewById(R.id.savePreference);
        next = (Button)findViewById(R.id.next);

    }

    public void SavePreferences(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("etUser", etUser.getText().toString());
        editor.putString("etPassword", etPassword.getText().toString());
        editor.commit();
        Toast.makeText(this, "SAVED IN SHARED PREFERENCES!!!", Toast.LENGTH_LONG).show();
    }

    public void SaveStorage(View view){
        String user = etUser.getText().toString();
        String password = etPassword.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write("User:".getBytes());
            fos.write(user.getBytes());
            fos.write("\nPassword:".getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "SAVED IN LOCAL STORAGE!!", Toast.LENGTH_LONG).show();
    }

    public void next(View view){
        Intent sd=new Intent(this,SecondActivity.class);
        startActivity(sd);
    }


}
