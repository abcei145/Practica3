package com.example.monica.punt1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    EditText epExpo, epPrac,epProy;
    Button bGuardar,Clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        epExpo = (EditText) findViewById(R.id.pExp);
        epPrac = (EditText) findViewById(R.id.pPrac);
        epProy = (EditText) findViewById(R.id.pProy);
        bGuardar = (Button) findViewById(R.id.bGuardar);
        Clear=(Button) findViewById(R.id.bclear);
        Clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                epExpo.setText("");
                epPrac.setText("");
                epProy.setText("");
            }
        });
        Bundle extras = getIntent().getExtras();
        epExpo.setText(extras.getString("pExpo").substring(0,2));
        epPrac.setText(extras.getString("pPrac").substring(0,2));
        epProy.setText(extras.getString("pProy").substring(0,2));

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag=0;

                if(epExpo.getText().toString().isEmpty()==true || epPrac.getText().toString().isEmpty()==true || epProy.getText().toString().isEmpty()==true)
                    flag=1;
                else {
                    String exp, pra, pro;
                    exp = epExpo.getText().toString();
                    pra = epPrac.getText().toString();
                    pro = epProy.getText().toString();
                    if (Integer.valueOf(exp) + Integer.valueOf(pra) + Integer.valueOf(pro) != 100) {
                        flag = 1;
                        Toast.makeText(SettingsActivity.this, "Los porcentajes no suman 100%, vuelva a configurar", Toast.LENGTH_SHORT).show();
                    }
                }
                if(flag==0) {
                    Intent intent = new Intent();
                    intent.putExtra("npExp", epExpo.getText().toString());
                    intent.putExtra("npPrac", epPrac.getText().toString());
                    intent.putExtra("npProy", epProy.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }


            }
        });

    }
}