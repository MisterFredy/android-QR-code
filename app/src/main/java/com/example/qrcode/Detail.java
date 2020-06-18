package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    TextView  name, type, qrNumber, lastmaintenance;
    protected Cursor cursor;
    DataHelper database;
    Button  btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        database = new DataHelper(this);
        name = (TextView) findViewById(R.id.textView1);
        type = (TextView) findViewById(R.id.textView2);
        qrNumber = (TextView) findViewById(R.id.textView3);
        lastmaintenance = (TextView) findViewById(R.id.textView4);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM Machine WHERE name = '" +
                getIntent().getStringExtra("Name") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            name.setText(cursor.getString(1).toString());
            type.setText(cursor.getString(2).toString());
            qrNumber.setText(cursor.getString(3).toString());
            lastmaintenance.setText(cursor.getString(4).toString());

        }
        btnBack = (Button) findViewById(R.id.button1);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
