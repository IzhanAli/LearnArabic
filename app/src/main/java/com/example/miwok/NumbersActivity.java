package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class NumbersActivity extends AppCompatActivity {
TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int r = tts.setLanguage(Locale.US);
                    if(r==TextToSpeech.LANG_MISSING_DATA||r==TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(NumbersActivity.this, "Language not supported", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        Log.i("TTS","lang ok");
                    }
                }else{
                    Toast.makeText(NumbersActivity.this, "Init fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
       WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.category_numbers, tts);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.

        ListView listView =  findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tts.speak(words.get(position).getMivokword(),TextToSpeech.QUEUE_FLUSH,null,String.valueOf(position));
            }
        });
    }
}