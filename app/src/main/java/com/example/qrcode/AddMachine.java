package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMachine extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper database;
    Button btnSave, btnBack;
    EditText name, type, qrNumber, lastmaintenance;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine);
        database = new DataHelper(this);
        name = (EditText) findViewById(R.id.etName);
        type = (EditText) findViewById(R.id.etType);
        qrNumber = (EditText) findViewById(R.id.etQRcode);
        lastmaintenance = (EditText) findViewById(R.id.etMaintenance);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        lastmaintenance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddMachine.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnSave = (Button) findViewById(R.id.button1);
        btnBack = (Button) findViewById(R.id.button2);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("insert into Machine( name, type, QR_code_number, Last_maintenance_date) values('" +
                        name.getText().toString() + "','" +
                        type.getText().toString() + "','" +
                        qrNumber.getText().toString() + "','" +
                        lastmaintenance.getText().toString()  +
                          "')");
                Toast.makeText(getApplicationContext(), "saved data success", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        lastmaintenance.setText(sdf.format(myCalendar.getTime()));
    }
}
