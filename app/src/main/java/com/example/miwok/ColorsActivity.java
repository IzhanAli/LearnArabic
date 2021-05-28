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

public class ColorsActivity extends AppCompatActivity {
TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int r = tts.setLanguage(Locale.US);
                    if(r==TextToSpeech.LANG_MISSING_DATA||r==TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(ColorsActivity.this, "Language not supported", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        Log.i("TTS","lang ok");
                    }
                }else{
                    Toast.makeText(ColorsActivity.this, "Init fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("White", "abyäd",R.drawable.color_white));
        words.add(new Word("Black", "aswäd",R.drawable.color_black));
        words.add(new Word("Green", "akhdar",R.drawable.color_green));
        words.add(new Word("Yellow", "asfar",R.drawable.color_mustard_yellow));
        words.add(new Word("Red", "ahmar",R.drawable.color_red));
        words.add(new Word("Gold","dhahabi",R.drawable.color_dusty_yellow));
        words.add(new Word("Gray","rasasi",R.drawable.color_gray));
// Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter = new WordAdapter(this,words, R.color.category_colors, tts);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView =  findViewById(R.id.colors_list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tts.speak(words.get(position).getMivokword(),TextToSpeech.QUEUE_FLUSH,null,String.valueOf(position));
            }
        });
    }
    @Override
    protected void onDestroy() {

        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}