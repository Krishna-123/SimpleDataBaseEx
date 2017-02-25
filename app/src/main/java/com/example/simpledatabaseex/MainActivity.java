package com.example.simpledatabaseex;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataHelper mydb;
    EditText name, Id, surname, phone;
    ImageButton save, del, view, update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DataHelper(this);

        name = (EditText) findViewById(R.id.Name);
        Id = (EditText) findViewById(R.id.id);
        surname = (EditText)findViewById(R.id.SurName);
        phone = (EditText)findViewById(R.id.Phone_Number);

        save = (ImageButton)findViewById(R.id.save);
        del = (ImageButton)findViewById(R.id.delete);
        view = (ImageButton)findViewById(R.id.view);
        update = (ImageButton)findViewById(R.id.update);
         addData();
        delData();
        viewData();
        updateDa();
    }

    public void addData() {
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isinsert = mydb.insertData(name.getText().toString()
                                , surname.getText().toString(),
                                phone.getText().toString());
                        if (isinsert == true)
                            Toast.makeText(MainActivity.this, "data inserted!!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "data failed!!", Toast.LENGTH_SHORT).show();
                        update.setVisibility(View.VISIBLE);
                        del.setVisibility(View.VISIBLE);
                    }
                }
        );
    }


    public void delData() {
        del.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Id.getText().toString().trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "plz enter the valid 'ID'",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Integer delRow = mydb.deleteData(Id.getText().toString());
                        if (delRow > 0)
                            Toast.makeText(MainActivity.this, "row is deleted!! ",
                                    Toast.LENGTH_SHORT).show();
                        else

                            Toast.makeText(MainActivity.this, "No row deleted!! ",
                                    Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }


    public void viewData() {
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getData();
                        if (res.getCount() == 0) {
                            //show Message
                            showMessage("error!!", "No Data Rows");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("ID : " + res.getString(0) + "\n");
                                buffer.append("Name : " + res.getString(1) + "\n");
                                buffer.append("SurName : " + res.getString(2) + "\n");
                                buffer.append("Phone : " + res.getString(3) + "\n\n");
                                // show all data
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );
    }


    public void showMessage(String title, String mes) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(mes);
        builder.show();
    }

    public void updateDa() {
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Id.getText().toString().trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "plz enter the valid 'ID'",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        boolean isupdate = mydb.updateData(Id.getText().toString()
                                , name.getText().toString()
                                , surname.getText().toString(),
                                phone.getText().toString());
                        if (isupdate == true)
                            Toast.makeText(MainActivity.this, "data updated!!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "data failed!!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

}
