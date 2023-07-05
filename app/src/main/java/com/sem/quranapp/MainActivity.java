package com.sem.quranapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.lang.NullPointerException;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity

{
    ListView listView;
    ArrayList<String> surah;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("working");
         surah.add("one");
             surah.add("two");
        surah.add("three");

        listView = findViewById(R.id.list2);
         surahInfo s = new surahInfo();
        System.out.println("\td is not null"+s);

        ArrayList<String> l;
                l=s.GetSurahNames();
                if(l!=null)
                {
                    System.out.println("l is not null its size is \t"+l.size());

                }


        surah = s.GetSurahNames();

        if (surah != null) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, surah);
            listView.setAdapter(arrayAdapter);
        } else {
            System.out.println("null");
            return;
        }

        listView.setOnItemClickListener((adapterView, view, i, l1) -> {
            System.out.println("you clicked on \t" + surah.get(i));


            //getting starting and ending index
            int start = s.getSurahStart(i);
            int count = s.getSurahVerses(i);

            System.out.println("\tstart in main is\t" + start);
            System.out.println("\tend in main is\t" + count + start);


            Intent intent = new Intent(MainActivity.this, Activity2.class);
            intent.putExtra("start", start - 1);
            intent.putExtra("count", count - 1);
            intent.putExtra("end", (count + start - 1));

            //  intent.putExtra("list",surah);
            intent.putExtra("index", i);
            startActivity(intent);
            //finish();
        });

        System.out.println("correct");


    }

}