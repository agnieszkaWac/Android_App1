package com.example.student.laborka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OcenyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oceny);

        Bundle tobolek = getIntent().getExtras();
        final int liczbaOcen = tobolek.getInt("liczbaOcen");

        ListView lista = (ListView)findViewById(R.id.lista);

        final ArrayList<Integer> dane = new ArrayList<>();
        for(int i = 0; i < liczbaOcen; i++) dane.add(0);
        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, dane);

        lista.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean test = true;
                int suma = 0;
                for(Integer i : dane) {
                    if(i == 0) {
                        Toast.makeText(OcenyActivity.this, "Musisz podać wszystkie oceny!!", Toast.LENGTH_SHORT).show();
                        test = false;
                    } else {
                        suma += i;
                    }
                }

                if(test) {
                    float srednia = (float)suma/liczbaOcen;
                    Intent myIntent2 = new Intent(OcenyActivity.this, MainActivity.class);

                    myIntent2.putExtra("wynik", srednia);
                    setResult(Activity.RESULT_OK, myIntent2);
                    finish();
                }
            }
        });

    }
}
