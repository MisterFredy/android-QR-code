package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateMachine extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper database;
    Button btnSave, btnBack;
    TextView id;
    EditText name, type, qrNumber, lastmaintenance;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_machine);
        database = new DataHelper(this);
        id = (TextView) findViewById(R.id.tv_e_id);
        name = (EditText) findViewById(R.id.et_e_Name);
        type = (EditText) findViewById(R.id.et_e_Type);
        qrNumber = (EditText) findViewById(R.id.et_e_QRcode);
        lastmaintenance = (EditText) findViewById(R.id.et_e_Maintenance);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM Machine WHERE name = '" +
                getIntent().getStringExtra("Name") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            id.setText(cursor.getString(0).toString());
            name.setText(cursor.getString(1).toString());
            type.setText(cursor.getString(2).toString());
            qrNumber.setText(cursor.getString(3).toString());
            lastmaintenance.setText(cursor.getString(4).toString());
        }
        btnSave = (Button) findViewById(R.id.e_button1);
        btnBack = (Button) findViewById(R.id.e_button2);
        // daftarkan even onClick pada btnSimpan
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("update Machine set name='"+
                        name.getText().toString() +"', type='" +
                        type.getText().toString()+"', QR_code_number='"+
                        qrNumber.getText().toString() +"', Last_maintenance_date='" +
                        lastmaintenance.getText().toString() + "' where id='" +
                        id.getText()+"'");
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
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
}
