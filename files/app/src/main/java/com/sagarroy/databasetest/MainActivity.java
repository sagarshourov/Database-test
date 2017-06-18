package com.sagarroy.databasetest;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAdd;
    private Button btnAdd;
    private Button btnView;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDatabase();
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAdd = (EditText) findViewById(R.id.editTextAddress);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                insertIntoDB();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showPeoples();
            }
        });
    }


    protected void createDatabase(){
        db=openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS persons(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR,address VARCHAR);");
    }

    protected void insertIntoDB(){
        String name = editTextName.getText().toString().trim();
        String add = editTextAdd.getText().toString().trim();
        if(name.equals("") || add.equals("")){
            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        String query = "INSERT INTO persons (name,address) VALUES('"+name+"', '"+add+"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_LONG).show();
    }
    private void showPeoples(){
        Intent intent = new Intent(this,ViewPepole.class);
        startActivity(intent);
        finish();
    }




}
