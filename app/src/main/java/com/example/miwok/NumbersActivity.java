package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", "wahid",R.drawable.number_one));
        words.add(new Word("two", "isnan",R.drawable.number_two));
        words.add(new Word("three", "salasa",R.drawable.number_three));
        words.add(new Word("four", "arbaa",R.drawable.number_four));
        words.add(new Word("five", "khamsa",R.drawable.number_five));
        words.add(new Word("six", "sittha",R.drawable.number_six));
        words.add(new Word("seven", "sabaa",R.drawable.number_seven));
        words.add(new Word("eight","samania",R.drawable.number_eight));
        words.add(new Word("nine","tis aa",R.drawable.number_nine));
        words.add(new Word("ten", "ashraa",R.drawable.number_ten));
// Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
       WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.category_numbers);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView =  findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

    }
}