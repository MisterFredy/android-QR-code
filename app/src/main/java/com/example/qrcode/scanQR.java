package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scanQR extends AppCompatActivity {
    TextView name, type, qrNumber, lastmaintenance;
    protected Cursor cursor;
    DataHelper database;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);


        name = (TextView) findViewById(R.id.tv_name_qr);
        type = (TextView) findViewById(R.id.tv_type_qr);
        qrNumber = (TextView) findViewById(R.id.tv_code_qr);
        lastmaintenance = (TextView) findViewById(R.id.tv_lastmaintenace_qr);

        new IntentIntegrator(this).initiateScan();
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                database = new DataHelper(this);
                SQLiteDatabase db = database.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM Machine WHERE QR_code_number = '" +
                        result.getContents() + "'",null);
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
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
