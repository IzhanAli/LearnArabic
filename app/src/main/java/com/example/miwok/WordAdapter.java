package com.example.miwok;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter {
    private int mColorRes;
   private TextToSpeech textToSpeech;
    public WordAdapter(@NonNull Context context, ArrayList<Word> wordArrayList, int colorRes, TextToSpeech tts) {
        super(context,0,wordArrayList);
        mColorRes = colorRes;
        textToSpeech = tts;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.material_list_item, parent, false);
        }


        Word word = (Word) getItem(position);
        TextView eng = listItemView.findViewById(R.id.text1);
        eng.setText(word.getDefaultword());
        TextView miv = listItemView.findViewById(R.id.mtrl_list_item_secondary_text);
        miv.setText(word.getMivokword());
        ImageView img = listItemView.findViewById(R.id.mtrl_list_item_icon);
        if (word.hasImg()) {
            img.setImageResource(word.getImageId());
            img.setVisibility(View.VISIBLE);
        } else {

            img.setVisibility(View.GONE);

        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.list_item_container);
        // Find the color that the resource ID maps to

        textContainer.setBackgroundColor(ContextCompat.getColor(getContext(), mColorRes));

        ImageView play = listItemView.findViewById(R.id.listplay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textToSpeech.speak(word.getMivokword(), TextToSpeech.QUEUE_FLUSH,null,String.valueOf(position));
            }
        });
//        tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//
//            }
//        });

        return listItemView;


    }




}
