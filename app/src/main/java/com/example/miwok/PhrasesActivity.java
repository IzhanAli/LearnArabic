package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going", "'iilaa 'ayn tadhhab"));
        words.add(new Word("What is your name", "ma asmuk"));
        words.add(new Word("My name is", "asmi hu..."));
        words.add(new Word("How are you feeling", "kayf tusheru?"));
        words.add(new Word("I’m feeling good.", "asheur bishueur jid."));
        words.add(new Word("Are you coming?", "hal ant qadim"));
        words.add(new Word("Yes, I’m coming.", "naeam , 'ana qadim"));
        words.add(new Word("Let’s go","hayaa bina."));
        words.add(new Word("Come here.","taeal alaa huna"));

// Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.category_phrases);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView =  findViewById(R.id.phrase_list);
        listView.setAdapter(itemsAdapter);
    }
}