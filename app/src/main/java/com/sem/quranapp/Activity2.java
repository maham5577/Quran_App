package com.sem.quranapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity
{
    ListView listView1;
    Button button;
    EditText search;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> single;
    ArrayList<String> adp;
    qurantext v;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView1 = findViewById(R.id.listView);
        button = findViewById(R.id.button1);
        search = findViewById(R.id.search);

        Intent i = getIntent();

        int index = i.getIntExtra("index", -1);
        int start = i.getIntExtra("start", -1);
        int end = i.getIntExtra("end", -1);
        int count = i.getIntExtra("count", -1);
        System.out.println("\tindex is\t" + index);
        System.out.println("\tstart is\t" + start);
        System.out.println("\tend is\t" + end);

        ArrayList<String> verse;

        v = new qurantext(); // create a new Verse object
        verse = v.GetData(start, end); // call the GetData method
// do not reassign or modify verse after this point



        if (verse == null || verse.isEmpty())
        {
            System.out.println("list is empty\t");
        }
        else
        {
            //The ArrayList  has elements
            System.out.println("verse list not empty\t" + verse.size());
        }

        if (verse != null)
        {

            adp = new ArrayList<>(verse);
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adp);

            listView1.setAdapter(arrayAdapter);
        }
        else
        {
            System.out.println("verse is null so not set");
        }

        single = new ArrayList<String>();

        button.setOnClickListener(new View.OnClickListener()
        {
            private View view;

            @Override
            public void onClick(View view)
            {
                this.view = view;
                int val = Integer.parseInt(search.getText().toString());
                if (val < 0 || val > count)
                {
                    System.out.println("enter valid verse no");
                    Toast.makeText(Activity2.this, "enter valid verse no", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    String s = verse.get(val);
                    single.add(s);


                    arrayAdapter.clear();
                    arrayAdapter.addAll(single);

                    // Notify the adapter that the data has changed
                    arrayAdapter.notifyDataSetChanged();
                    single.clear();


                }


            }
        });
    }
}