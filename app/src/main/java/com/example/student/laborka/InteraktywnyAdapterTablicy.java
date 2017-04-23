package com.example.student.laborka;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InteraktywnyAdapterTablicy extends ArrayAdapter<Integer>
{

    private Activity kontekst;
    ArrayList<Integer> lista;
    public InteraktywnyAdapterTablicy(Activity kontekst, ArrayList<Integer> listaOcen)
    {
        super(kontekst, 0, listaOcen);
        this.kontekst = kontekst;
        lista = listaOcen;
    }
    //tworzenie nowego wiersza
    @Override
    public View getView(int numerWiersza, View widokDoRecyklingu, ViewGroup parent)
    {
        View widok = null;
        TextView etykieta;

        if (widokDoRecyklingu == null)
        {
//utworzenie layout na podstawie pliku XML
            LayoutInflater pompka = kontekst.getLayoutInflater();
            widok = pompka.inflate(R.layout.linia, parent, false);
            etykieta = (TextView)widok.findViewById(R.id.label);
            etykieta.setText("Ocena "+String.valueOf(numerWiersza+1));
//wybranie konkretnego przycisku radiowego musi zmieniać dane w modelu

            RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.wyborOceny);
            grupaOceny.setTag(numerWiersza);
            grupaOceny.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener()
                    {
                        @Override
                        public void onCheckedChanged(RadioGroup group, //referencja do grupy przycisków
                                                     int checkedId) //identyfikator wybranego przycisku
                        {
// 1) odczytanie z etykiety, który obiekt modelu przechowuje dane o
//zmienionej ocenie
// 2) zapisanie zmienionej oceny
                            int wiersz = ((Integer)group.getTag()).intValue();
                            switch (checkedId) {
                                case R.id.radio2: lista.set(wiersz, 2); break;
                                case R.id.radio3: lista.set(wiersz, 3); break;
                                case R.id.radio4: lista.set(wiersz, 4); break;
                                case R.id.radio5: lista.set(wiersz, 5); break;
                                default: lista.set(wiersz, 0); break;
                            }
                        }
                    }
            );
//powiązanie grupy przycisków z obiektem w modelu
        }
//aktualizacja istniejącego wiersza
        else
        {
            widok = widokDoRecyklingu;
            RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.wyborOceny);
//powiązanie grupy przycisków z obiektem w modelu
        }
        TextView etykieta2 = (TextView) widok.findViewById(R.id.label);
//ustawienie tekstu etykiety na podstawie modelu
        RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.wyborOceny);
//zaznaczenie odpowiedniego przycisku na podtawie modelu
//zwrócenie nowego lub zaktualizowanego wiersza listy
        return widok;
    }
}