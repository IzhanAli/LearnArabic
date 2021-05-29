package com.example.miwok;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Locale;

public class ColorsFragment extends Fragment {
    TextToSpeech tts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int r = tts.setLanguage(new Locale("ar-XA"));
                    if (r == TextToSpeech.LANG_MISSING_DATA || r == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getActivity(), "Language not supported", Toast.LENGTH_SHORT).show();


                    } else {
                        Log.i("TTS", "lang ok");
                    }
                } else {
                    Toast.makeText(getActivity(), "Init fail", Toast.LENGTH_SHORT).show();
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
        WordAdapter itemsAdapter = new WordAdapter(getActivity(),words, R.color.category_colors, tts);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView =  rootView.findViewById(R.id.global_list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tts.speak(words.get(position).getMivokword(),TextToSpeech.QUEUE_FLUSH,null,String.valueOf(position));
            }
        });
        return rootView;

    }
    @Override
    public void onDestroy() {

        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
