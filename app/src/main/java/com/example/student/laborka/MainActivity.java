package com.example.student.laborka;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    boolean a = false, b = false, c = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText imie_txt = (EditText) findViewById(R.id.imie_editText);
        final Button przycisk = (Button) findViewById(R.id.button);

        imie_txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && imie_txt.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Pole nie może być puste", Toast.LENGTH_SHORT).show();
                    a = false;
                } else a = true;
                if (a == true && b == true && c == true) {
                    przycisk.setVisibility(View.VISIBLE);
                } else przycisk.setVisibility(View.INVISIBLE);
            }
        });

        final EditText nazwisko_txt = (EditText) findViewById(R.id.nazwisko_editText);

        nazwisko_txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && nazwisko_txt.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Pole nie może być puste", Toast.LENGTH_SHORT).show();
                    b = false;
                } else b = true;
                if (a == true && b == true && c == true) {
                    przycisk.setVisibility(View.VISIBLE);
                } else przycisk.setVisibility(View.INVISIBLE);
            }
        });

        final EditText locen_txt = (EditText) findViewById(R.id.locen_editText);

        locen_txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus && locen_txt.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Pole nie może być puste", Toast.LENGTH_SHORT).show();
                    c = false;
                } else if (!hasFocus) {
                    int o = Integer.parseInt(locen_txt.getText().toString());
                    if (o < 5 || o > 15) {
                        Toast.makeText(MainActivity.this, "Wartość musi być z przedziału 5-15", Toast.LENGTH_SHORT).show();
                        c = false;
                    } else c = true;
                }
                if (a == true && b == true && c == true) {
                    przycisk.setVisibility(View.VISIBLE);
                } else przycisk.setVisibility(View.INVISIBLE);
            }
        });


        ////////////////////////////////////////////////////////////////////////////////
        locen_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int o = 0;
                if (!locen_txt.getText().toString().matches("")) {
                    o = Integer.parseInt(locen_txt.getText().toString());
                }
                if (o < 5 || o > 14) {
                    Toast.makeText(MainActivity.this, "Wartość musi być z przedziału 5-14", Toast.LENGTH_SHORT).show();
                    c = false;
                } else c = true;
                if (a == true && b == true && c == true) {
                    przycisk.setVisibility(View.VISIBLE);
                } else przycisk.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        imie_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (imie_txt.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Pole nie może być puste", Toast.LENGTH_SHORT).show();
                    a = false;
                    przycisk.setVisibility(View.INVISIBLE);
                } else if (!imie_txt.getText().toString().matches("")) a = true;
                if (a == true && b == true && c == true) {
                    przycisk.setVisibility(View.VISIBLE);
                } else przycisk.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        nazwisko_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nazwisko_txt.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Pole nie może być puste", Toast.LENGTH_SHORT).show();
                    b = false;
                    przycisk.setVisibility(View.INVISIBLE);
                } else if (!nazwisko_txt.getText().toString().matches("")) b = true;
                if (a == true && b == true && c == true) {
                    przycisk.setVisibility(View.VISIBLE);
                } else przycisk.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////
        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, OcenyActivity.class);

                int liczbaOcen = Integer.parseInt(locen_txt.getText().toString());
                myIntent.putExtra("liczbaOcen", liczbaOcen);
                MainActivity.this.startActivityForResult(myIntent, 1);
            }
        });
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int kodZadania, int kodWyniku, Intent dane) {
        super.onActivityResult(kodZadania, kodWyniku, dane);
        if (kodWyniku==RESULT_OK) {
            EditText it = (EditText)findViewById(R.id.imie_editText);
            it.setEnabled(false);
            EditText nt = (EditText)findViewById(R.id.nazwisko_editText);
            nt.setEnabled(false);
            EditText lt = (EditText)findViewById(R.id.locen_editText);
            lt.setEnabled(false);

            Bundle tobolek=dane.getExtras();
            float srednia =tobolek.getFloat("wynik");
            TextView pasek = (TextView)findViewById(R.id.textView4);
            pasek.setHeight(120);
            pasek.setText("Twoja średnia to: "+String.valueOf(srednia));
            pasek.setVisibility(View.VISIBLE);

            Button btn = (Button)findViewById(R.id.button);
            if(srednia >= 3) {
                btn.setText("Super :)");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        Toast.makeText(MainActivity.this, "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                btn.setText("Tym razem mi nie poszło :(");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        Toast.makeText(MainActivity.this, "Wysyłam podanie o zaliczenie warunkowe.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }

}

