package com.sagarroy.databasetest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewPepole extends AppCompatActivity {



    private static final String SELECT_SQL = "SELECT * FROM persons";

    private SQLiteDatabase db;

    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pepole);
        openDatabase();
        c = db.rawQuery(SELECT_SQL, null);
        c.moveToFirst();
        showRecords();

    }


    protected void openDatabase() {
        db = openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE, null);
    }

    protected void showRecords() {
        String id = c.getString(0);
        String name = c.getString(1);
        String add = c.getString(2);

        Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show();

    }

    private void deleteRecord() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want delete this person?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                      //  String id = editTextId.getText().toString().trim();
                        String id = "2";
                        String sql = "DELETE FROM persons WHERE id=" + id + ";";
                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_LONG).show();
                        c = db.rawQuery(SELECT_SQL,null);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
