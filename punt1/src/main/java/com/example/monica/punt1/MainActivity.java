package com.example.monica.punt1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eExpo, ePrac, eProy, eNota;
    Button bCalc,Clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        eExpo = (EditText) findViewById(R.id.eExp);
        ePrac = (EditText) findViewById(R.id.ePrac);
        eProy = (EditText) findViewById(R.id.eProy);
        eNota = (EditText) findViewById(R.id.eFinal);
        bCalc = (Button) findViewById(R.id.bCalcular);
        Clear=(Button) findViewById(R.id.Clear);
        Clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                eExpo.setText("");
                ePrac.setText("");
                eProy.setText("");
            }
        });

        bCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nota=0,expo=0,epra=0,eproy=0;
                int flag=0;
                if(eExpo.getText().toString().isEmpty()==true || ePrac.getText().toString().isEmpty()==true || eProy.getText().toString().isEmpty()==true)
                    flag=1;
                else {
                    expo = Double.parseDouble(eExpo.getText().toString());
                    epra = Double.parseDouble(ePrac.getText().toString());
                    eproy = Double.parseDouble(eProy.getText().toString());
                    if (expo < 0 || expo > 5) {
                        Toast.makeText(MainActivity.this, "La nota de la exposicion debe estar entre 0 y 5", Toast.LENGTH_SHORT).show();
                        flag = 1;
                    }
                    if (epra < 0 || epra > 5) {
                        Toast.makeText(MainActivity.this, "La nota de la practica debe estar entre 0 y 5", Toast.LENGTH_SHORT).show();
                        flag = 1;
                    }
                    if (eproy < 0 || eproy > 5) {
                        Toast.makeText(MainActivity.this, "La nota de el proyecto debe estar entre 0 y 5", Toast.LENGTH_SHORT).show();
                        flag = 1;
                    }
                }
                if(flag==0) {
                    nota = expo * Double.parseDouble(eExpo.getHint().toString().substring(0,2)) / 100 +
                            epra * Double.parseDouble(ePrac.getHint().toString().substring(0, 2)) / 100 +
                            eproy * Double.parseDouble(eProy.getHint().toString().substring(0,2)) / 100;

                    eNota.setText(String.valueOf(nota));
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mConfigurar) {
            //Toast.makeText(this, "Presionó configurar", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("pExpo",eExpo.getHint().toString());
            intent.putExtra("pPrac",ePrac.getHint().toString());
            intent.putExtra("pProy",eProy.getHint().toString());
            startActivityForResult(intent, 1234);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String exp, pra, pro;
        if (requestCode == 1234 && resultCode == RESULT_OK){
            exp = data.getExtras().getString("npExp");
            pra = data.getExtras().getString("npPrac");
            pro = data.getExtras().getString("npProy");
            ((EditText) findViewById(R.id.eExp)).setHint(exp+"%");
            ((EditText) findViewById(R.id.ePrac)).setHint(pra+"%");
            ((EditText) findViewById(R.id.eProy)).setHint(pro+"%");
            Toast.makeText(MainActivity.this, "Configuración exitosa", Toast.LENGTH_SHORT).show();
        }
    }
}